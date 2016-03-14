package gplx.xowa.addons.searchs.searchers; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.xowa.langs.cases.*;	
import gplx.xowa.addons.searchs.searchers.rslts.*; import gplx.xowa.addons.searchs.searchers.wkrs.*; import gplx.xowa.addons.searchs.searchers.crts.*; import gplx.xowa.addons.searchs.parsers.*;
public class Srch_search_mgr {
	private final Srch_search_addon		addon;
	private final Srch_link_wkr			link_wkr = new Srch_link_wkr();
	private final Srch_rslt_list			cache__page = new Srch_rslt_list();
	private final Hash_adp_bry			cache__word_counts = Hash_adp_bry.cs();
	private final Srch_page_tbl_searcher page_tbl_searcher = new Srch_page_tbl_searcher();
	private final Srch_crt_parser		crt_parser;
	private int search_count;
	public Srch_search_mgr(Srch_search_addon addon, Srch_text_parser ttl_parser) {
		this.addon = addon;
		this.crt_parser = new Srch_crt_parser(ttl_parser);
	}
	public void Search(Srch_rslt_list rslts_list, Cancelable cxl, Srch_rslt_cbk rslt_cbk, Xow_wiki wiki, Srch_qry qry) {
		if (++search_count > 32) {	// lazy way of clearing memory
			cache__page.Clear();
			cache__word_counts.Clear();
			search_count = 0;
		}

		Srch_ctx ctx = new Srch_ctx(cxl, wiki, addon, cache__page, cache__word_counts, qry, crt_parser.Parse(qry.Search_raw), rslts_list);
		Srch_crt_visitor__last visitor = Srch_crt_visitor__last.Instance;
		visitor.Find_last_uid(ctx.Search_crt);
		ctx.Score_rng.Select_init(ctx.Rslts_needed, Srch_link_wkr.Percentile_rng__calc_adj(visitor.last_node.raw.length));

		if (!page_tbl_searcher.Search(ctx, rslt_cbk)) return;
		link_wkr.Search(rslts_list, rslt_cbk, ctx);
	}
	public static byte[] Remove_wildcard(byte[] search_raw) {
		return Bry_.Has_at_end(search_raw, Byte_ascii.Star_bry) ? Bry_.Mid(search_raw, 0, search_raw.length - 1) : search_raw;
	}
}
