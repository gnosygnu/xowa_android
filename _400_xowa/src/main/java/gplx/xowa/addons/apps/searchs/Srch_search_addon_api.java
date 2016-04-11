package gplx.xowa.addons.apps.searchs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*;
import gplx.xowa.addons.apps.searchs.searchers.*;
import gplx.xowa.addons.apps.searchs.searchers.rslts.*;
public interface Srch_search_addon_api {
	void Search(Srch_search_qry qry, Srch_rslt_cbk cbk);
}
