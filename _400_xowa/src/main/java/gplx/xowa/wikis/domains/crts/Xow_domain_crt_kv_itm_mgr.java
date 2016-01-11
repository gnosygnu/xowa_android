package gplx.xowa.wikis.domains.crts; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.domains.*;
public class Xow_domain_crt_kv_itm_mgr {
	private final List_adp list = List_adp_.new_();
	public void Clear() {list.Clear();}
	@gplx.Internal protected void Add(Xow_domain_crt_kv_itm itm) {list.Add(itm);}
	public boolean Parse_as_itms(byte[] raw) {
		this.Clear();
		Xow_domain_crt_kv_itm[] ary = Xow_domain_crt_itm_parser.Instance.Parse_as_kv_itms_or_null(raw);
		if (ary == null) return false; // invalid parse; leave current value as is and exit;
		int len = ary.length;
		for (int i = 0; i < len; ++i)
			this.Add(ary[i]);
		return true;
	}
	public boolean Parse_as_arys(byte[] raw) {
		this.Clear();
		Xow_domain_crt_kv_ary[] ary = Xow_domain_crt_itm_parser.Instance.Parse_as_kv_arys_or_null(raw);
		if (ary == null) return false; // invalid parse; leave current value as is and exit;
		int len = ary.length;
		for (int i = 0; i < len; ++i)
			list.Add(ary[i]);
		return true;
	}
	public Xow_domain_crt_itm Find_itm(Xow_domain_itm cur, Xow_domain_itm comp) {
		int len = list.Count();
		for (int i = 0; i < len; ++i) {
			Xow_domain_crt_kv_itm kv = (Xow_domain_crt_kv_itm)list.Get_at(i);
			if (kv.Key().Matches(cur, comp)) return kv.Val();
		}
		return Xow_domain_crt_itm__none.Instance;
	}
	public Xow_domain_crt_itm[] Find_ary(Xow_domain_itm cur, Xow_domain_itm comp) {
		int len = list.Count();
		for (int i = 0; i < len; ++i) {
			Xow_domain_crt_kv_ary kv = (Xow_domain_crt_kv_ary)list.Get_at(i);
			if (kv.Key().Matches(cur, comp)) return kv.Val();
		}
		return null;
	}
}
class Xow_domain_crt_kv_itm {
	public Xow_domain_crt_kv_itm(Xow_domain_crt_itm key, Xow_domain_crt_itm val) {this.key = key; this.val = val;}
	public Xow_domain_crt_itm Key() {return key;} private final Xow_domain_crt_itm key;
	public Xow_domain_crt_itm Val() {return val;} private final Xow_domain_crt_itm val;
}
class Xow_domain_crt_kv_ary {
	public Xow_domain_crt_kv_ary(Xow_domain_crt_itm key, Xow_domain_crt_itm[] val) {this.key = key; this.val = val;}
	public Xow_domain_crt_itm Key() {return key;} private final Xow_domain_crt_itm key;
	public Xow_domain_crt_itm[] Val() {return val;} private final Xow_domain_crt_itm[] val;
}
