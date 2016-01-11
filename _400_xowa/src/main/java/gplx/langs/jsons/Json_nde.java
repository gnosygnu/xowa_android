package gplx.langs.jsons; import gplx.*; import gplx.langs.*;
public class Json_nde extends Json_itm_base implements Json_grp {		
	private Json_itm[] subs = Json_itm_.Ary_empty; private int subs_len = 0, subs_max = 0;
	public Json_nde(Json_doc jdoc, int src_bgn) {this.jdoc = jdoc; this.Ctor(src_bgn, -1);}
	@Override public byte Tid() {return Json_itm_.Tid__nde;}
	public Json_doc Doc() {return jdoc;} private final Json_doc jdoc;
	public void Src_end_(int v) {this.src_end = v;}
	@Override public Object Data() {return null;}
	@Override public byte[] Data_bry() {return null;}
	public int Len() {return subs_len;}
	public Json_kv Get_at_as_kv(int i) {
		Json_itm rv_itm = Get_at(i);
		Json_kv rv = Json_kv.cast(rv_itm); if (rv == null) throw Err_.new_("json", "sub is not kv", "i", i, "src", Bry_.Mid(jdoc.Src(), this.Src_bgn(), src_end));
		return rv;
	}
	public Json_itm Get_at(int i) {return subs[i];}
	public Json_kv Get_kv(byte[] key) {return Json_kv.cast(Get_itm(key));}
	public Json_nde Get(String key) {return Get(Bry_.new_u8(key));}
	public Json_nde Get(byte[] key) {
		Json_kv kv = Json_kv.cast(this.Get_itm(key)); if (kv == null) throw Err_.new_("json", "kv not found", "key", key);
		Json_nde rv = Json_nde.cast(kv.Val()); if (rv == null) throw Err_.new_("json", "nde not found", "key", key);
		return rv;
	}
	public Json_itm Get_itm(byte[] key) {
		for (int i = 0; i < subs_len; i++) {
			Json_itm itm = subs[i];
			if (itm.Tid() == Json_itm_.Tid__kv) {
				Json_kv itm_as_kv = (Json_kv)itm;
				if (Bry_.Eq(key, itm_as_kv.Key().Data_bry()))
					return itm;
			}
		}
		return null;
	}
	public boolean Has(byte[] key) {return Get_bry(key, null) != null;}
	public byte[] Get_bry(byte[] key) {
		byte[] rv = Get_bry(key, null); if (rv == null) throw Err_.new_("json", "key missing", "key", key);
		return rv;
	}
	public byte[] Get_bry_or_null(String key) {return Get_bry(Bry_.new_u8(key), null);}
	public byte[] Get_bry_or_null(byte[] key) {return Get_bry(key, null);}
	public byte[] Get_bry(byte[] key, byte[] or) {
		Json_itm kv_obj = Get_itm(key);
		if (kv_obj == null) return or;	// key not found;
		if (kv_obj.Tid() != Json_itm_.Tid__kv) return or; // key is not a key_val
		Json_kv kv = (Json_kv)kv_obj;
		Json_itm val = kv.Val();
		return (val == null) ? or : val.Data_bry();
	}
	public Json_nde Add_many(Json_itm... ary) {
		int len = ary.length;
		for (int i = 0; i < len; i++)
			Add(ary[i]);
		return this;
	}
	public void Add(Json_itm itm) {
		int new_len = subs_len + 1;
		if (new_len > subs_max) {	// ary too small >>> expand
			subs_max = new_len * 2;
			Json_itm[] new_subs = new Json_itm[subs_max];
			Array_.Copy_to(subs, 0, new_subs, 0, subs_len);
			subs = new_subs;
		}
		subs[subs_len] = (Json_itm)itm;
		subs_len = new_len;
	}
	@Override public void Print_as_json(Bry_bfr bfr, int depth) {
		if (bfr.Len() != 0)
			bfr.Add_byte_nl();
		Json_grp_.Print_indent(bfr, depth);
		bfr.Add_byte(Byte_ascii.Curly_bgn).Add_byte(Byte_ascii.Space);
		for (int i = 0; i < subs_len; i++) {
			if (i != 0) {
				Json_grp_.Print_nl(bfr); Json_grp_.Print_indent(bfr, depth);
				bfr.Add_byte(Byte_ascii.Comma).Add_byte(Byte_ascii.Space);
			}
			subs[i].Print_as_json(bfr, depth + 1);
		}
		Json_grp_.Print_nl(bfr); Json_grp_.Print_indent(bfr, depth);
		bfr.Add_byte(Byte_ascii.Curly_end).Add_byte_nl();
	}
	public static Json_nde cast(Json_itm v) {return v == null || v.Tid() != Json_itm_.Tid__nde ? null : (Json_nde)v;}
}
