package gplx.xowa.addons.updates.downloads.unzips; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.updates.*; import gplx.xowa.addons.updates.downloads.*;
import gplx.core.ios.zips.*;

class Gfo_unzip_wkr__jre implements Gfo_unzip_wkr {
	public void Unzip__bgn(Gfo_unzip_cbk cbk, Gfo_unzip_itm itm) {			
		Io_zip_mgr_base.Instance.Unzip_to_dir(itm.Unzip__src_fil(), itm.Unzip__trg_dir());
		cbk.Unzip__end_itm(itm);
	}
	public static final    Gfo_unzip_wkr__jre Instance = new Gfo_unzip_wkr__jre(); Gfo_unzip_wkr__jre() {}
}
