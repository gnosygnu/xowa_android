package gplx.xowa.apps.site_cfgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*;
class Xoa_site_cfg_loader__fallback implements Xoa_site_cfg_loader {
	public int			Tid() {return Xoa_site_cfg_loader_.Tid__fallback;}
	public void			Load_csv__bgn(Xoa_site_cfg_mgr mgr, Xow_wiki wiki) {}
	public byte[]		Load_csv(Xoa_site_cfg_mgr mgr, Xow_wiki wiki, Xoa_site_cfg_itm__base itm) {return Bry_.Empty;}	// return non-null so "data != null" check works
}
