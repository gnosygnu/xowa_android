package gplx.xowa.bldrs.wms; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
import gplx.core.net.*;
import gplx.xowa.bldrs.wms.apis.*; import gplx.xowa.files.downloads.*;
public class Xowmf_mgr {
	public Xowmf_mgr() {
		download_wkr.Download_xrg().User_agent_(Xoa_app_.User_agent);
	}
	public void Init_by_app(Xoa_app app) {
		download_wkr.Download_xrg().Prog_dlg_(Xoa_app_.Usr_dlg());
	}
	public Xowmf_api_mgr		Api_mgr() {return api_mgr;} private Xowmf_api_mgr api_mgr = new Xowmf_api_mgr();
	public Xof_download_wkr		Download_wkr() {return download_wkr;} private final Xof_download_wkr download_wkr = new Xof_download_wkr_io();
}
