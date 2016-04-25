package gplx.xowa.addons.updates.downloads.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.updates.*; import gplx.xowa.addons.updates.downloads.*;
public interface Gfo_download_itm {
	String Download__src();
	Io_url Download__trg();
	void Download__trg_(Io_url v);
}
