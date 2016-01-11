package gplx.core.btries; import gplx.*; import gplx.core.*;
import gplx.core.intls.*;
public class Btrie_u8_mgr implements Btrie_mgr {
	private Btrie_u8_itm root; private Gfo_case_mgr case_mgr;
	Btrie_u8_mgr(Gfo_case_mgr case_mgr) {
		this.case_mgr = case_mgr;
		this.root = new Btrie_u8_itm(Bry_.Empty, null);
	}
	public int Count() {return count;} private int count;
	public int Match_pos() {return match_pos;} private int match_pos;
	public Object Match_bgn(byte[] src, int bgn_pos, int end_pos) {return Match_bgn_w_byte(src[bgn_pos], src, bgn_pos, end_pos);}
	public Object Match_bgn_w_byte(byte b, byte[] src, int bgn_pos, int end_pos) {
		Object rv = null; int cur_pos = match_pos = bgn_pos;
		Btrie_u8_itm cur = root;
		while (true) {
			int c_len = Utf8_.Len_of_char_by_1st_byte(b);
			int c_end = cur_pos + c_len;
			Btrie_u8_itm nxt = cur.Nxts_find(src, cur_pos, c_end, true); if (nxt == null) return rv;	// nxts does not have key; return rv;
			cur_pos = c_end;
			if (nxt.Nxts_is_empty()) {match_pos = cur_pos; return nxt.Val();}	// nxt is leaf; return nxt.Val() (which should be non-null)
			Object nxt_val = nxt.Val();
			if (nxt_val != null) {match_pos = cur_pos; rv = nxt_val;}			// nxt is node; cache rv (in case of false match)
			if (cur_pos == end_pos) return rv;									// increment cur_pos and exit if end
			b = src[cur_pos];
			cur = nxt;
		}
	}
	public void Clear() {root.Clear(); count = 0;}
	public Btrie_mgr Add_obj(String key, Object val) {return Add_obj(Bry_.new_u8(key), val);}
	public Btrie_mgr Add_obj(byte[] key, Object val) {
		if (val == null) throw Err_.new_wo_type("null objects cannot be registered", "key", String_.new_u8(key));
		int key_len = key.length;
		Btrie_u8_itm cur = root;
		int c_bgn = 0;
		while (c_bgn < key_len) {
			byte c = key[c_bgn];
			int c_len = Utf8_.Len_of_char_by_1st_byte(c);
			int c_end = c_bgn + c_len;
			Btrie_u8_itm nxt = cur.Nxts_find(key, c_bgn, c_end, false);
			if (nxt == null)
				nxt = cur.Nxts_add(case_mgr, Bry_.Mid(key, c_bgn, c_end), null);
			c_bgn = c_end;
			if (c_bgn == key_len)
				nxt.Val_set(val);
			cur = nxt;
		}
		++count;
		return this;
	}
	public static Btrie_u8_mgr new_(Gfo_case_mgr case_mgr) {return new Btrie_u8_mgr(case_mgr);}
}
