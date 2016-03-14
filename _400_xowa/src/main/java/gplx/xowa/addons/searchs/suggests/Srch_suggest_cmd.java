package gplx.xowa.addons.searchs.suggests; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.xowa.addons.searchs.searchers.*; import gplx.xowa.addons.searchs.searchers.rslts.*;
class Srch_suggest_cmd implements Cancelable, GfoInvkAble {
	private final Srch_search_addon addon;
	private final Xow_wiki wiki;
	private final Srch_rslt_cbk rslt_cbk;
	private final int rslts_max;
	public final byte[] Search_raw;
	public Srch_suggest_cmd(Srch_search_addon addon, Xow_wiki wiki, Srch_rslt_cbk rslt_cbk, byte[] search_raw, int rslts_max) {
		this.addon = addon; this.wiki = wiki; this.rslt_cbk = rslt_cbk; this.Search_raw = search_raw; this.rslts_max = rslts_max;
	}
	public boolean Canceled() {return canceled;} private boolean canceled;
	public void Cancel() {canceled = true;}
	private void Search() {
		try {	// NOTE: must handle any errors in async mode
			Srch_ns_mgr ns_mgr = new Srch_ns_mgr();
			ns_mgr.Add_main_if_empty();
			Srch_qry qry = new Srch_qry(ns_mgr, Search_raw, 0, 0, rslts_max, false, gplx.xowa.wikis.domains.Xow_domain_itm_.Ary_empty);
			addon.Search_mgr().Search(new Srch_rslt_list(), this, rslt_cbk, wiki, qry);				
		} 
		catch(Exception e) {
			Xoa_app_.Usr_dlg().Prog_many("", "", "error during search: err=~{0}", Err_.Message_gplx_full(e));
		}
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__search))	Search();
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	public static final String Invk__search = "search";
}
//		private boolean Search_by_all_pages_v2() {
//			rslts_2.Clear();
//			Xoa_ttl search_ttl = Xoa_ttl.parse(wiki, search_bry); if (search_ttl == null) return false;
//			byte[] search_ttl_bry = search_ttl.Page_db();
//			wiki.Db_mgr().Load_mgr().Load_ttls_for_search_suggest(this, rslts_2, search_ttl.Ns(), search_ttl_bry, max_results, all_pages_min, all_pages_extend, true, false);
//			return true;
//		}
//		private boolean Search_by_all_pages_v1() {
//			rslts_2.Clear();
//			Xowd_page_itm rslt_nxt = new Xowd_page_itm();
//			Xowd_page_itm rslt_prv = new Xowd_page_itm();
//			Xoa_ttl search_ttl = Xoa_ttl.parse(wiki, search_bry); if (search_ttl == null) return false;
//			byte[] search_ttl_bry = search_ttl.Page_db();
//			List_adp page_list = List_adp_.new_();
//			wiki.Db_mgr().Load_mgr().Load_ttls_for_all_pages(this, page_list, rslt_nxt, rslt_prv, Int_obj_ref.zero_(), wiki.Ns_mgr().Ns_main(), search_ttl_bry, max_results, all_pages_min, all_pages_extend, true, false);
//			Xowd_page_itm[] page_ary = (Xowd_page_itm[])page_list.To_ary_and_clear(typeof(Xowd_page_itm));
//			int idx = 0, page_ary_len = page_ary.length;
//			for (int i = 0; i < page_ary_len; i++) {
//				Xowd_page_itm page = page_ary[i];
//				if (page != null) {
//					if (!Bry_.Has_at_bgn(page.Ttl_page_db(), search_ttl_bry)) continue;	// look-ahead may return other titles that don't begin with search; ignore
//					if (page.Text_len() > all_pages_min) {
//						rslts_2.Add(page);
//						idx++;
//					}
//				}
//				if (idx == max_results) break;
//			}
//			return true;
//		}
