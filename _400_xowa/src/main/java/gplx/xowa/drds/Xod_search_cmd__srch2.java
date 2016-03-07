package gplx.xowa.drds; import gplx.*; import gplx.xowa.*;
import gplx.xowa.langs.cases.*;
import gplx.xowa.addons.searchs.v1s.*;
import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*; import gplx.xowa.addons.searchs.searchers.itms.*;
class Xod_search_cmd__srch2 implements Xod_search_cmd {
	private Xol_case_mgr case_mgr = Xol_case_mgr_.U8();
	private final Srch_ns_mgr ns_mgr = new Srch_ns_mgr();
	public void Search(Cancelable cxl, Srch2_rslt_cbk rslt_cbk, Xow_wiki wiki, String search) {
//			while (!cxl.Canceled()) {
//				gplx.search_mgr.threads.Thread_adp_.Sleep(100);
//			}
		ns_mgr.Add_main_if_empty();
		Srch_search_addon addon = Srch_search_addon.Get(wiki);
		addon.Case_mgr_(case_mgr);
		Srch_search_mgr search_mgr = addon.Search_mgr();
		Srch2_rslt_hash hash = search_mgr.Search(cxl, rslt_cbk, wiki, case_mgr, new Srch2_qry(ns_mgr, case_mgr.Case_build_lower(Bry_.new_u8(search)), 0, 50));
//			int len = hash.Len();
//			for (int i = 0; i < len; ++i) {
//				Srch2_rslt_row row = hash.Get_at(i);
//				Srch_rslt_itm search_itm = new Srch_rslt_itm(row.Wiki_bry, wiki.Ttl_parse(row.Page_ttl_wo_ns), row.Page_id, row.Page_len, row.Page_score);
//				rslt_cbk.Notify_rslt_found(search_itm);
//			}
	}
	public static final Xod_search_cmd__srch2 Instance = new Xod_search_cmd__srch2(); Xod_search_cmd__srch2() {}
}
