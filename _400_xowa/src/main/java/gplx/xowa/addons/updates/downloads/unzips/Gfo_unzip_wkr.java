package gplx.xowa.addons.updates.downloads.unzips; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.updates.*; import gplx.xowa.addons.updates.downloads.*;
public interface Gfo_unzip_wkr {
	void Unzip__bgn(Gfo_unzip_cbk cbk, Gfo_unzip_itm itm);
}
class Gfo_unzip_wkr__noop implements Gfo_unzip_wkr {
	public void Unzip__bgn(Gfo_unzip_cbk cbk, Gfo_unzip_itm itm) {cbk.Unzip__end_itm(itm);}
	public static final    Gfo_unzip_wkr__noop Instance = new Gfo_unzip_wkr__noop(); Gfo_unzip_wkr__noop() {}
}
