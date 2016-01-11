package gplx.xowa.apps.site_cfgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*;
import gplx.dbs.cfgs.*;
class Xoa_site_cfg_loader__db implements Xoa_site_cfg_loader {
	private Db_cfg_tbl cfg_tbl;
	public int Tid() {return Xoa_site_cfg_loader_.Tid__cfg;}
	public void Load_csv__bgn(Xoa_site_cfg_mgr mgr, Xow_wiki wiki) {
		this.cfg_tbl = wiki.Data__core_mgr().Tbl__cfg();
		cfg_tbl.Select_as_hash_bry(mgr.Data_hash(), Grp__xowa_wm_api);
	}
	public byte[] Load_csv(Xoa_site_cfg_mgr mgr, Xow_wiki wiki, Xoa_site_cfg_itm__base itm) {
		byte[] rv = (byte[])mgr.Data_hash().Get_by_bry(itm.Key_bry()); if (rv == null) return null;
		int meta_end = Bry_find_.Find_fwd(rv, Byte_ascii.Nl);
		if (meta_end == Bry_find_.Not_found) {// fallback will only log one line; ignore; EX: //#xowa|fallback
			return null;
		}
		return Bry_.Mid(rv, meta_end + 1);
	}
	public void Save_bry(int loader_tid, String db_key, byte[] val) {
		byte[] meta = Bry_.new_a7(Bld_meta(loader_tid));
		byte[] data = Bry_.Len_eq_0(val) ? meta : Bry_.Add(meta, Byte_ascii.Nl_bry, val);
		cfg_tbl.Assert_bry(Grp__xowa_wm_api, db_key, data);
	}
	public static String Bld_meta(int loader_tid) {
		return String_.Format("//#xowa|{0}|{1}|{2}", Xoa_app_.Version, Xoa_site_cfg_loader_.Get_key(loader_tid), DateAdp_.Now().XtoUtc().XtoStr_fmt_yyyyMMdd_HHmmss());
	}
	public static final String Grp__xowa_wm_api = "xowa.site_cfg";
}
