package gplx.xowa.addons.updates.downloads; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.updates.*;
import gplx.xowa.addons.updates.downloads.core.*;
import gplx.xowa.addons.updates.downloads.itms.*;
class Xodl_download_mgr implements Gfo_download_cbk {
	public void Download(Gfo_download_wkr download_wkr, Xodl_itm_pack[] packs) {
		download_wkr.Download__bgn(this, packs);
	}
	public void Download__end_itm(Gfo_download_itm itm) {
		// unzip; start
	}
	public void Download__end_all(Gfo_download_itm[] itms) {}
	public void Unzip__end(Gfo_download_itm[] itms) {
		// register
	}
}
