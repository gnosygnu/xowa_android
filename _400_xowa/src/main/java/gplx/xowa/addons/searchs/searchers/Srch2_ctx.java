package gplx.xowa.addons.searchs.searchers; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.xowa.langs.cases.*;
import gplx.xowa.wikis.data.*; import gplx.xowa.wikis.data.tbls.*; import gplx.xowa.addons.searchs.dbs.*;
import gplx.xowa.addons.searchs.searchers.crts.*; import gplx.xowa.addons.searchs.searchers.itms.*; import gplx.xowa.addons.searchs.searchers.wkrs.*;
public class Srch2_ctx {
	private final Bry_bfr tmp_bfr = Bry_bfr.new_();
	public Srch2_ctx(Cancelable cxl, Xow_wiki wiki, Xol_case_mgr case_mgr, Srch_search_addon addon, Srch2_word_cache word_cache, Srch2_page_cache page_cache, Srch2_wildcard_cache wildcard_cache, Srch2_qry qry) {
		this.Cxl  = cxl;
		this.Wiki = wiki;
		this.Wiki_domain = wiki.Domain_bry();
		this.Addon = addon;
		this.Case_mgr = case_mgr;
		this.Word_cache = word_cache;
		this.Page_cache = page_cache;
		this.Wildcard_cache = wildcard_cache;
		this.Qry = qry;
		this.Db__core = wiki.Data__core_mgr().Db__core();
		this.Tbl__page = Db__core.Tbl__page();
		this.Tbl__word = addon.Db_mgr().Tbl__word();
		this.Tbl__link__ary = addon.Db_mgr().Tbl__link__ary();
		this.Score_rng.Init_for_wiki(16000000, 100000);
	}
	public final Cancelable					Cxl;
	public final byte[]						Wiki_domain;
	public final Xow_wiki					Wiki;
	public final Srch_search_addon			Addon;
	public final Xol_case_mgr				Case_mgr;
	public final Srch2_word_cache			Word_cache;
	public final Srch2_page_cache			Page_cache;
	public final Srch2_wildcard_cache		Wildcard_cache;
	public final Xowd_db_file				Db__core;
	public final Xowd_page_tbl				Tbl__page;
	public final Srch_word_tbl				Tbl__word;
	public final Srch_link_tbl[]				Tbl__link__ary;
	public final Srch2_qry					Qry;
	public final Srch2_score_rng				Score_rng = new Srch2_score_rng();
	public byte[] Bld_key(byte[] wiki_domain, int page_id) {return tmp_bfr.Add(wiki_domain).Add_byte_pipe().Add_int_variable(page_id).To_bry_and_clear();}
}
