package gplx.xowa.apps.site_cfgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*;
import gplx.xowa.apps.fsys.*;
class Xoa_site_cfg_loader__fsys implements Xoa_site_cfg_loader {
	private final Io_url site_cfg_dir;
	public Xoa_site_cfg_loader__fsys(Io_url site_cfg_dir) {this.site_cfg_dir = site_cfg_dir;}
	public int Tid() {return Xoa_site_cfg_loader_.Tid__fsys;}
	public void Load_csv__bgn(Xoa_site_cfg_mgr mgr, Xow_wiki wiki) {}
	public byte[] Load_csv(Xoa_site_cfg_mgr mgr, Xow_wiki wiki, Xoa_site_cfg_itm__base itm) {
		return Io_mgr.Instance.LoadFilBryOrNull(Make_load_url(wiki.Domain_str(), itm.Key_str()));
	}
	public Io_url Make_load_url(String wiki, String key) {return site_cfg_dir.GenSubFil_nest(key, key + "-" + wiki + ".csv");}
	public static Xoa_site_cfg_loader__fsys new_(Xoa_app app) {return new Xoa_site_cfg_loader__fsys(app.Fsys_mgr().Bin_xowa_dir().GenSubDir_nest("cfg", "wiki", "api"));}
}
