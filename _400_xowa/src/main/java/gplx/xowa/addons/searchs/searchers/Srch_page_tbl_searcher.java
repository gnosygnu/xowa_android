package gplx.xowa.addons.searchs.searchers; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.dbs.*;
import gplx.xowa.wikis.nss.*; import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.addons.searchs.searchers.rslts.*;
class Srch_page_tbl_searcher {
	public boolean Search(Srch_ctx ctx, Srch_rslt_cbk rslt_cbk) {
		byte[] search_raw = ctx.Qry.Search_raw;
		search_raw = Srch_search_mgr.Remove_wildcard(search_raw);
		Xoa_ttl ttl = ctx.Wiki.Ttl_parse(search_raw); if (ttl == null) return true;
		if (ctx.Cxl.Canceled()) return false;
		Xowd_page_tbl page_tbl = ctx.Tbl__page;
		if (page_tbl.Select_by_ttl(tmp_page_row, ttl.Ns(), ttl.Page_db())) {
			if (ctx.Cxl.Canceled()) return false;
			Srch_rslt_row row = Srch_rslt_row.New(ctx.Wiki_domain, ttl, tmp_page_row.Id(), tmp_page_row.Text_len(), ctx.Addon.Db_mgr().Cfg().Link_score_max() * 3, tmp_page_row.Redirect_id(), Srch_rslt_tid_.Tid__title__exact);
			if (!ctx.Rslts_list.Has(row.Key)) {
				Srch_rslt_row_.Format_ttl(ctx, page_tbl, tmp_page_row, row);
				Srch_rslt_row_.Redirect_handle(ctx.Rslts_list, row);
				ctx.Rslts_list.Add(row);
				rslt_cbk.On_rslt_found(row);
				rslt_cbk.On_rdr_done(false);
			}
		}
		return true;
	}	private final Xowd_page_itm tmp_page_row = new Xowd_page_itm();
}
