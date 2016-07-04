package gplx.xowa.files.fsdb; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
import gplx.fsdb.*; import gplx.fsdb.data.*; import gplx.fsdb.meta.*;
import gplx.xowa.files.bins.*; import gplx.xowa.files.origs.*; import gplx.xowa.files.caches.*; import gplx.xowa.guis.cbks.js.*;
public interface Xof_fsdb_mgr {
	Xof_bin_mgr Bin_mgr();
	Fsm_mnt_mgr Mnt_mgr();
	void Init_by_wiki(Xow_wiki wiki);
	void Fsdb_search_by_list(List_adp itms, Xow_wiki wiki, Xoa_page page, Xog_js_wkr js_wkr);
	void Rls();
}
