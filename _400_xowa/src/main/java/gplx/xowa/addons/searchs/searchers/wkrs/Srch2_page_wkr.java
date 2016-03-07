package gplx.xowa.addons.searchs.searchers.wkrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.addons.searchs.searchers.itms.*;
public class Srch2_page_wkr {
	private final List_adp page_list = List_adp_.new_();
	public void Search(Cancelable cxl, Srch2_rslt_hash rslts, Srch2_rslt_cbk rslt_cbk, Xow_wiki wiki, Srch2_ctx ctx, Srch2_qry qry) {
		int max_results = 10;		// get 10 results
		int browse_len = 1000;		// out of 1000
		int min_page_len = 10000;	// with min score at least of 10000
		rslts.Stage_id = Srch2_rslt_stage.Tid__title_bgn;
		wiki.Data__core_mgr().Tbl__page().Select_for_search_suggest(cxl, page_list, wiki.Ns_mgr().Ns_main(), qry.Raw, max_results, min_page_len, browse_len, Bool_.Y, Bool_.N);

		int len = page_list.Count(); if (len == 0) return;
		byte[] wiki_domain = wiki.Domain_bry();
		for (int i = 0; i < len; ++i) {
			if (ctx.Cxl.Canceled()) {cxl.Cancel_ackd_(); return;}
			Xowd_page_itm page_row = (Xowd_page_itm)page_list.Get_at(i);
			int page_id = page_row.Id();
			byte[] rslt_key = ctx.Bld_key(wiki_domain, page_id);
			Srch2_rslt_row rslt_row = new Srch2_rslt_row(rslt_key, wiki_domain, page_row.Ns_id(), page_row.Ttl_page_db(), page_id, page_row.Text_len(), page_row.Rank(), rslts.Stage_id);
			rslts.Add(rslt_row);
			rslt_cbk.On_rslt_found(rslt_row);
		}
		page_list.Clear();
	}
}
