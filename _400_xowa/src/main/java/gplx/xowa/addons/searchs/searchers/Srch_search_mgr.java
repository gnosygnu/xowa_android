package gplx.xowa.addons.searchs.searchers; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.xowa.langs.cases.*;	
import gplx.xowa.addons.searchs.searchers.itms.*; import gplx.xowa.addons.searchs.searchers.wkrs.*; import gplx.xowa.addons.searchs.searchers.crts.*; 
public class Srch_search_mgr {
	private final Srch_search_addon addon;
	private Srch2_crt_parser crt_parser;
	private final Srch2_page_wkr page_wkr = new Srch2_page_wkr();
	private final Srch2_word_wkr word_wkr = new Srch2_word_wkr();
	private final Srch2_page_cache page_hash = new Srch2_page_cache();
	private final Srch2_word_cache word_cache = new Srch2_word_cache();
	private final Srch2_wildcard_cache wildcard_cache = new Srch2_wildcard_cache();
	public Srch_search_mgr(Srch_search_addon addon) {this.addon = addon;}
	public Srch2_rslt_hash Search(Cancelable cxl, Srch2_rslt_cbk rslt_cbk, Xow_wiki wiki, Xol_case_mgr case_mgr, Srch2_qry qry) {
		this.Rls_mem();
		if (crt_parser == null) this.crt_parser = new Srch2_crt_parser(addon.Parsers__ttl_parser());

		qry.Crt = crt_parser.Parse(qry.Raw);
		Srch2_ctx ctx = new Srch2_ctx(cxl, wiki, case_mgr, addon, word_cache, page_hash, wildcard_cache, qry);
		Srch2_crt_node_visitor__last visitor = Srch2_crt_node_visitor__last.Instance;
		visitor.Find_last_uid(ctx.Qry.Crt);
		ctx.Score_rng.Init_for_search(qry.Request_len, visitor.node_count, visitor.last_node.raw.length);

		Srch2_rslt_hash rslts = new Srch2_rslt_hash(30);
		page_wkr.Search(cxl, rslts, rslt_cbk, wiki, ctx, qry);
		word_wkr.Search(rslts, rslt_cbk, ctx, qry.Crt);
		return rslts;
	}
	public void Rls_mem() {
		word_cache.Clear(); page_hash.Clear(); wildcard_cache.Clear();
	}
}
