package gplx.core.btries; import gplx.*; import gplx.core.*;
import gplx.core.primitives.*; import gplx.core.threads.poolables.*;
public class Btrie_slim_mgr implements Btrie_mgr {
	private static Gfo_poolable_mgr pool_rv = Gfo_poolable_mgr.New_rlsable(new Btrie_rv(), Object_.Ary_empty, 0, 1024);
	Btrie_slim_mgr(boolean case_match) {root = new Btrie_slim_itm(Byte_.Zero, null, !case_match);}	private Btrie_slim_itm root;
	public int Count() {return count;} private int count;
	public int Match_pos() {return match_pos;} private int match_pos;

	public Btrie_rv Match_at(byte[] src, int bgn, int end) {return bgn < end  ? Match_at_w_b0(src[bgn], src, bgn, end) : null;} // handle out of bounds gracefully; EX: Match_bgn("abc", 3, 3) should return null not fail
	public Btrie_rv Match_at_w_b0(byte b, byte[] src, int bgn_pos, int src_end) {
		Object rv_obj = null; 
		int rv_pos = bgn_pos;
		int cur_pos = bgn_pos;
		Btrie_slim_itm cur = root;
		while (true) {
			Btrie_slim_itm nxt = cur.Ary_find(b);
			if (nxt == null) 
				return ((Btrie_rv)pool_rv.Get_safe()).Init(cur_pos, rv_obj);			// nxt does not hav b; return rv_obj;
			++cur_pos;
			if (nxt.Ary_is_empty()) {
				return ((Btrie_rv)pool_rv.Get_safe()).Init(cur_pos, nxt.Val());			// nxt is leaf; return nxt.Val() (which should be non-null)
			}
			Object nxt_val = nxt.Val();
			if (nxt_val != null) {rv_pos = cur_pos; rv_obj = nxt_val;}					// nxt is node; cache rv_obj (in case of false match)
			if (cur_pos == src_end)
				return ((Btrie_rv)pool_rv.Get_safe()).Init(rv_pos, rv_obj);				// increment cur_pos and exit if src_end
			b = src[cur_pos];
			cur = nxt;
		}
	}
	public Object Match_exact(byte[] src) {return src == null ? null : Match_exact(src, 0, src.length);}
	public Object Match_exact(byte[] src, int bgn_pos, int end_pos) {
		if (bgn_pos == end_pos) return null;	// NOTE:handle empty String; DATE:2016-04-21
		Object rv = Match_bgn_w_byte(src[bgn_pos], src, bgn_pos, end_pos);
		return rv == null ? null : match_pos - bgn_pos == end_pos - bgn_pos ? rv : null;
	}
	public Object Match_bgn(byte[] src, int bgn_pos, int end_pos) {return bgn_pos < end_pos  ? Match_bgn_w_byte(src[bgn_pos], src, bgn_pos, end_pos) : null;} // handle out of bounds gracefully; EX: Match_bgn("abc", 3, 3) should return null not fail
	public Object Match_bgn_w_byte(byte b, byte[] src, int bgn_pos, int src_end) {
		Object rv = null; int cur_pos = match_pos = bgn_pos;
		Btrie_slim_itm cur = root;
		while (true) {
			Btrie_slim_itm nxt = cur.Ary_find(b); if (nxt == null) return rv;	// nxt does not hav b; return rv;
			++cur_pos;
			if (nxt.Ary_is_empty()) {match_pos = cur_pos; return nxt.Val();}	// nxt is leaf; return nxt.Val() (which should be non-null)
			Object nxt_val = nxt.Val();
			if (nxt_val != null) {match_pos = cur_pos; rv = nxt_val;}			// nxt is node; cache rv (in case of false match)
			if (cur_pos == src_end) return rv;									// increment cur_pos and exit if src_end
			b = src[cur_pos];
			cur = nxt;
		}
	}
	public byte Match_byte_or(byte b, byte[] src, int bgn, int end, byte or) {
		Object rv_obj = Match_bgn_w_byte(b, src, bgn, end);
		return rv_obj == null ? or : ((Byte_obj_val)rv_obj).Val();
	}
	public byte Match_byte_or(byte[] src, int bgn, int end, byte or) {
		Object rv_obj = Match_bgn(src, bgn, end);
		return rv_obj == null ? or : ((Byte_obj_val)rv_obj).Val();
	}
	public Btrie_slim_mgr Add_bry_tid(byte[] bry, byte tid)			{return (Btrie_slim_mgr)Add_obj(bry, Byte_obj_val.new_(tid));}
	public Btrie_slim_mgr Add_bry_int(byte[] key, int val)			{return (Btrie_slim_mgr)Add_obj(key, Int_obj_val.new_(val));}
	public Btrie_slim_mgr Add_str_byte(String key, byte val)		{return (Btrie_slim_mgr)Add_obj(Bry_.new_u8(key), Byte_obj_val.new_(val));}
	public Btrie_slim_mgr Add_str_int(String key, int val)			{return (Btrie_slim_mgr)Add_obj(Bry_.new_u8(key), Int_obj_val.new_(val));}
	public Btrie_slim_mgr Add_bry(String key, String val)			{return (Btrie_slim_mgr)Add_obj(Bry_.new_u8(key), Bry_.new_u8(val));}
	public Btrie_slim_mgr Add_bry(String key, byte[] val)			{return (Btrie_slim_mgr)Add_obj(Bry_.new_u8(key), val);}
	public Btrie_slim_mgr Add_bry(byte[] v)							{return (Btrie_slim_mgr)Add_obj(v, v);}
	public Btrie_slim_mgr Add_str_str(String key, String val)		{return (Btrie_slim_mgr)Add_obj(Bry_.new_u8(key), Bry_.new_u8(val));}
	public Btrie_slim_mgr Add_bry_bry(byte[] key, byte[] val)		{return (Btrie_slim_mgr)Add_obj(key, val);}
	public Btrie_slim_mgr Add_bry_byte(byte b, byte val)			{return (Btrie_slim_mgr)Add_obj(new byte[] {b}, Byte_obj_val.new_(val));}
	public Btrie_slim_mgr Add_bry_byte(byte[] bry, byte val)		{return (Btrie_slim_mgr)Add_obj(bry, Byte_obj_val.new_(val));}
	public Btrie_slim_mgr Add_str_byte__many(byte val, String... ary) {
		int ary_len = ary.length;
		Byte_obj_val bval = Byte_obj_val.new_(val);
		for (int i = 0; i < ary_len; i++)
			Add_obj(Bry_.new_u8(ary[i]), bval);
		return this;
	}
	public Btrie_slim_mgr Add_many_int(int val, String... ary) {return Add_many_int(val, Bry_.Ary(ary));}
	public Btrie_slim_mgr Add_many_int(int val, byte[]... ary) {
		int len = ary.length;
		Int_obj_val obj = Int_obj_val.new_(val);
		for (int i = 0; i < len; i++)
			Add_obj(ary[i], obj);
		return this;
	}
	public Btrie_slim_mgr Add_ary_byte(byte... ary) {
		int len = ary.length;
		for (int i = 0; i < len; ++i) {
			byte b = ary[i];
			Byte_obj_val bval = Byte_obj_val.new_(b);
			Add_obj(Bry_.New_by_byte(b), bval);
		}
		return this;
	}
	public Btrie_slim_mgr Add_replace_many(String trg_str, String... src_ary) {return Add_replace_many(Bry_.new_u8(trg_str), src_ary);}
	public Btrie_slim_mgr Add_replace_many(byte[] trg_bry, String... src_ary) {
		int len = src_ary.length;
		for (int i = 0; i < len; i++)
			Add_obj(Bry_.new_u8(src_ary[i]), trg_bry);
		return this;
	}
	public Btrie_slim_mgr Add_stub(String key, byte val)		{byte[] bry = Bry_.new_u8(key); return (Btrie_slim_mgr)Add_obj(bry, new Btrie_itm_stub(val, bry));}
	public Btrie_slim_mgr Add_stubs(byte[][] ary)				{return Add_stubs(ary, ary.length);}
	public Btrie_slim_mgr Add_stubs(byte[][] ary, int ary_len) {
		for (byte i = 0; i < ary_len; i++) {
			byte[] bry = ary[i];
			Add_obj(bry, new Btrie_itm_stub(i, bry));
		}
		return this;
	}
	public Btrie_mgr Add_obj(String key, Object val) {return Add_obj(Bry_.new_u8(key), val);}
	public Btrie_mgr Add_obj(byte[] key, Object val) {
		if (val == null) throw Err_.new_wo_type("null objects cannot be registered", "key", String_.new_u8(key));
		int key_len = key.length; int key_end = key_len - 1;
		Btrie_slim_itm cur = root;
		for (int i = 0; i < key_len; i++) {
			byte b = key[i];
			if (root.Case_any() && (b > 64 && b < 91)) b += 32;
			Btrie_slim_itm nxt = cur.Ary_find(b);
			if (nxt == null)
				nxt = cur.Ary_add(b, null);
			if (i == key_end)
				nxt.Val_set(val);
			cur = nxt;
		}
		count++; // FUTURE: do not increment if replacing value
		return this;
	}
	public void Del(byte[] key) {
		int key_len = key.length;
		Btrie_slim_itm cur = root;
		for (int i = 0; i < key_len; i++) {
			byte b = key[i];
			Btrie_slim_itm nxt = cur.Ary_find(b);
			if (nxt == null) break;
			Object nxt_val = nxt.Val();
			if (nxt_val == null)	// cur is end of chain; remove entry; EX: Abc and at c
				cur.Ary_del(b);
			else					// cur is mid of chain; null out entry
				nxt.Val_set(null);
			cur = nxt;
		}
		count--; // FUTURE: do not decrement if not found
	}
	public byte[] Replace(Bry_bfr tmp_bfr, byte[] src, int bgn, int end) {
		int pos = bgn;
		boolean dirty = false;
		while (pos < end) {
			byte b = src[pos];
			Object o = this.Match_bgn_w_byte(b, src, pos, end);
			if (o == null) {
				if (dirty)
					tmp_bfr.Add_byte(b);
				pos++;
			}
			else {
				if (!dirty) {
					tmp_bfr.Add_mid(src, bgn, pos);
					dirty = true;
				}
				tmp_bfr.Add((byte[])o);
				pos = match_pos;
			}
		}
		return dirty ? tmp_bfr.To_bry_and_clear() : src;
	}
	public void Clear() {root.Clear(); count = 0;}
	public static Btrie_slim_mgr cs()				{return new Btrie_slim_mgr(Bool_.Y);}
	public static Btrie_slim_mgr ci_a7()			{return new Btrie_slim_mgr(Bool_.N);}
	public static Btrie_slim_mgr ci_u8()			{return new Btrie_slim_mgr(Bool_.N);}
	public static Btrie_slim_mgr new_(boolean v)		{return new Btrie_slim_mgr(v);}
}
