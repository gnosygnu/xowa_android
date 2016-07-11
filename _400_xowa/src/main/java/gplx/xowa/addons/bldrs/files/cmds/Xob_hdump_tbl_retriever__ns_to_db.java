package gplx.xowa.addons.bldrs.files.cmds; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.files.*;
import gplx.xowa.wikis.nss.*; import gplx.xowa.htmls.core.dbs.*; import gplx.xowa.htmls.core.bldrs.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*; import gplx.xowa.wikis.data.*;
class Xob_hdump_tbl_retriever__ns_to_db implements Xob_hdump_tbl_retriever {
	private final    Xob_ns_to_db_mgr ns_to_db_mgr;
	public Xob_hdump_tbl_retriever__ns_to_db(Xowe_wiki wiki) {
		Xow_db_mgr core_data_mgr = wiki.Db_mgr_as_sql().Core_data_mgr();
		this.ns_to_db_mgr = new Xob_ns_to_db_mgr(new Xob_ns_to_db_wkr__html(core_data_mgr.Db__core()), core_data_mgr, wiki.Appe().Api_root().Bldr().Wiki().Import().Html_db_max());
		Xob_ns_file_itm.Init_ns_bldr_data(Xow_db_file_.Tid__html_data, wiki.Ns_mgr(), gplx.xowa.apps.apis.xowa.bldrs.imports.Xoapi_import.Ns_file_map__each);
	}
	public Xowd_html_tbl Get_html_tbl(Xow_ns ns, int prv_row_len) {
		Xow_db_file html_db = ns_to_db_mgr.Get_by_ns(ns.Bldr_data(), prv_row_len);							// get html_db
		return html_db.Tbl__html();
	}
	public void Commit() {ns_to_db_mgr.Commit();}
	public void Rls_all() {ns_to_db_mgr.Rls_all();}
}
