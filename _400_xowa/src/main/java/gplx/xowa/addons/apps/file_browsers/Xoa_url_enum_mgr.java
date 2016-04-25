package gplx.xowa.addons.apps.file_browsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*;
public class Xoa_url_enum_mgr {
	private final    Hash_adp_bry hash = Hash_adp_bry.cs();
	public Xoa_url_enum_mgr(Xoa_url_enum_itm... ary) {
		int len = ary.length;
		for (int i = 0; i < len; ++i) {
			Xoa_url_enum_itm itm = ary[i];
			hash.Add_bry_obj(itm.Key(), itm);
		}
	}
	public Xoa_url_enum_itm Get(byte[] key) {return (Xoa_url_enum_itm)hash.Get_by_bry(key);}
}
