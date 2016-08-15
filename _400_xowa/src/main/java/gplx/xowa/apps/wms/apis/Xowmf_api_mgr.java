package gplx.xowa.apps.wms.apis; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.wms.*;
import gplx.xowa.apps.wms.apis.origs.*;
public class Xowmf_api_mgr {
	public Xoapi_orig_base	Api_orig() {return api_orig;} public void Api_orig_(Xoapi_orig_base v) {api_orig = v;} private Xoapi_orig_base api_orig = new Xoapi_orig_wmf();
}
