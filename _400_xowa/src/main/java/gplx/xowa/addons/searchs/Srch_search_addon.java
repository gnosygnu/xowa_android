package gplx.xowa.addons.searchs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*;
import gplx.xowa.addons.searchs.dbs.*; import gplx.xowa.addons.searchs.searchers.*; import gplx.xowa.addons.searchs.parsers.*;
import gplx.xowa.langs.cases.*;
public class Srch_search_addon implements Xoax_addon_itm {
	private Xol_case_mgr case_mgr;
	private final Xow_wiki wiki;
	public Srch_search_addon(Xow_wiki wiki) {
		this.wiki = wiki;
		this.db_mgr = new Srch_db_mgr(wiki).Init();
		this.search_mgr = new Srch_search_mgr(this);
	}
	public byte[]				Addon__key() {return Key_const;} public static final byte[] Key_const = Bry_.new_a7("xowa.search");
	public Srch_db_mgr			Db_mgr() {return db_mgr;} private final Srch_db_mgr db_mgr;
	public Srch_search_mgr		Search_mgr() {return search_mgr;} private final Srch_search_mgr search_mgr;
	public void Case_mgr_(Xol_case_mgr v) {this.case_mgr = v;}
	public Srch2_text_parser	Parsers__ttl_parser() {
		if (ttl_parser == null) {
			ttl_parser = new Srch2_text_parser();
			ttl_parser.Init_for_ttl(case_mgr == null ? wiki.Lang().Case_mgr() : case_mgr);
		}
		return ttl_parser;
	}	private Srch2_text_parser ttl_parser;		

	public static Srch_search_addon Get(Xow_wiki wiki) {
		Srch_search_addon rv = (Srch_search_addon)wiki.Addon_mgr().Itms__get_or_null(Key_const);
		if (rv == null) {
			rv = new Srch_search_addon(wiki);
			wiki.Addon_mgr().Itms__add(rv);
		}
		return rv;
	}
}
