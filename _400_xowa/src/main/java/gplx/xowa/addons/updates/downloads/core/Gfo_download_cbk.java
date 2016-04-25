package gplx.xowa.addons.updates.downloads.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.updates.*; import gplx.xowa.addons.updates.downloads.*;
public interface Gfo_download_cbk {
	void Download__end_itm(Gfo_download_itm itm);
	void Download__end_all(Gfo_download_itm[] itms);
}
