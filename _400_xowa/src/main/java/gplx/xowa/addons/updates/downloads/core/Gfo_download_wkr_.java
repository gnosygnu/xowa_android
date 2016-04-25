package gplx.xowa.addons.updates.downloads.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.updates.*; import gplx.xowa.addons.updates.downloads.*;
public class Gfo_download_wkr_ {
	public static final    Gfo_download_wkr Noop = new Gfo_download_wkr__noop();
}
class Gfo_download_wkr__noop implements Gfo_download_wkr {
	public void Download__bgn(Gfo_download_cbk cbk, Gfo_download_itm[] itms) {cbk.Download__end_all(itms);}
}
