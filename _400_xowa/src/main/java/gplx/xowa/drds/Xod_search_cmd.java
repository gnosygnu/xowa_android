package gplx.xowa.drds; import gplx.*; import gplx.xowa.*;
import gplx.xowa.addons.searchs.v1s.*;
import gplx.xowa.addons.searchs.searchers.itms.*;
public interface Xod_search_cmd {
	void Search(Cancelable cancelable, Srch2_rslt_cbk rslt_cbk, Xow_wiki wiki, String search);
}
