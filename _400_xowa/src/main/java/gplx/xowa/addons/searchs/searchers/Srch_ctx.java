package gplx.xowa.addons.searchs.searchers; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.xowa.langs.cases.*;
import gplx.xowa.wikis.data.*; import gplx.xowa.wikis.data.tbls.*; import gplx.xowa.addons.searchs.dbs.*;
import gplx.xowa.addons.searchs.searchers.crts.*; import gplx.xowa.addons.searchs.searchers.rslts.*; import gplx.dbs.percentiles.*; import gplx.xowa.addons.searchs.searchers.wkrs.*;
import gplx.xowa.addons.searchs.parsers.*;
public class Srch_ctx {
	public Srch_ctx(Cancelable cxl, Xow_wiki wiki, Srch_search_addon addon, Srch_rslt_list cache__page, Hash_adp_bry cache__word_counts, Srch_qry qry, Srch_crt_node search_crt, Srch_rslt_list rslts_list) {
		this.Cxl  = cxl;
		this.Wiki = wiki;
		this.Wiki_domain = wiki.Domain_bry();
		this.Case_mgr = wiki.Case_mgr();
		this.Addon = addon;
		this.Cache__page = cache__page;
		this.Cache__word_counts = cache__word_counts;
		this.Qry = qry;
		this.Db__core = wiki.Data__core_mgr().Db__core();
		this.Tbl__page = Db__core.Tbl__page();
		this.Tbl__word = addon.Db_mgr().Tbl__word();
		this.Tbl__link__ary = addon.Db_mgr().Tbl__link__ary();
		long page_count = wiki.Stats().Num_pages();
		this.Score_rng.Init(page_count, addon.Db_mgr().Cfg().Link_score_max());
		this.Search_crt = search_crt;
		this.Rslts_list = rslts_list;
		this.Rslts_needed = qry.Slab_end - rslts_list.Len();
		this.Highlight_mgr = new Srch_highlight_mgr(this.Case_mgr).Search_(qry.Search_raw);
	}
	public final Cancelable					Cxl;
	public final byte[]						Wiki_domain;
	public final Xow_wiki					Wiki;
	public final Srch_search_addon			Addon;
	public final Xol_case_mgr				Case_mgr;
	public final Srch_rslt_list				Cache__page;
	public final Hash_adp_bry				Cache__word_counts;
	public final Xowd_db_file				Db__core;
	public final Xowd_page_tbl				Tbl__page;
	public final Srch_word_tbl				Tbl__word;
	public final Srch_link_tbl[]				Tbl__link__ary;
	public final Srch_qry					Qry;
	public final Srch_rslt_list				Rslts_list;
	public final int							Rslts_needed;
	public final Percentile_rng				Score_rng = new Percentile_rng();
	public final Srch_crt_node				Search_crt;
	public final Srch_highlight_mgr			Highlight_mgr;
	public final Bry_bfr						Tmp_bfr = Bry_bfr.new_();
}
