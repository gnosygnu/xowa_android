package gplx.xowa.addons.wikis.searchs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*;
import gplx.xowa.addons.wikis.searchs.searchers.*;
import gplx.xowa.addons.wikis.searchs.searchers.rslts.*;
public interface Srch_search_addon_api {
	void Search(Srch_search_qry qry, Srch_rslt_cbk cbk);
}
