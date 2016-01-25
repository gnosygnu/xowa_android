package gplx.xowa.drds; import gplx.*; import gplx.xowa.*;
import gplx.xowa.drds.pages.*; import gplx.xowa.drds.files.*;
import gplx.xowa.apps.*; import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.wikis.nss.*; import gplx.xowa.files.gui.*;
import gplx.xowa.specials.search.*; import gplx.xowa.specials.randoms.*;
import gplx.langs.htmls.encoders.*; import gplx.xowa.htmls.hrefs.*;
public class Xod_app {
	private final Xoav_app app;
	private final Xod_page_mgr page_mgr = new Xod_page_mgr();
	private final Xod_file_mgr file_mgr = new Xod_file_mgr();
	public Xod_app(Xoav_app app) {
		this.app = app;
	}
	public int Wikis__count() {
		int rv = app.Wiki_mgri().Count();
		return rv - 1;	// ignore home wiki
	}
	public Xow_wiki Wikis__get_by_domain(String wiki_domain) {
		Xow_wiki rv = app.Wiki_mgri().Get_by_or_make_init_y(Bry_.new_u8(wiki_domain));
		if (rv != null && rv.Data__core_mgr() == null) rv.Init_by_wiki();
		return rv;
	}
	public Xod_page_itm Wiki__get_by_url(Xow_wiki wiki, Xoa_url page_url) {
		return page_mgr.Get_page(wiki, page_url);
	}
	public Xod_page_itm Wiki__get_random(Xow_wiki wiki, Xow_ns ns) {
		byte[] random_ttl_bry = wiki.Data__core_mgr().Tbl__page().Select_random(ns);
		Xoa_url url = wiki.Utl__url_parser().Parse(random_ttl_bry);
		return Wiki__get_by_url(wiki, url);
	}
	public String[] Wiki__search(Cancelable cancelable, Xow_wiki wiki, Xows_ui_async ui_async, String search) {
		Xows_db_wkr search_wkr = new Xows_db_wkr();
		Xows_db_row[] rows = search_wkr.Search_by_drd(cancelable, wiki, ui_async, Bry_.new_u8(search), 50);
		int len = rows.length;
		String[] rv = new String[len];
		for (int i = 0; i < len; ++i) {
			rv[i] = String_.new_u8(rows[i].Page_ttl().Page_txt());
		}
		return rv;
	}
	public void Wiki__search(Cancelable cancelable, Xow_wiki wiki, Xows_ui_async ui_async, String search, Xod_search_cmd[] cmds) {
		for (Xod_search_cmd cmd : cmds)
			cmd.Search(cancelable, wiki, ui_async, search);
	}
    public void Save__all() {

    }
	public void Page__load_files(Xow_wiki wiki, Xod_page_itm pg, Xog_js_wkr js_wkr) {
		file_mgr.Load_files(wiki, pg, js_wkr);
        app.User().User_db_mgr().Cache_mgr().Db_save();
	}
	public static byte[] To_page_url(Xow_wiki wiki, String canonical_str) {// NOTE: need canonical_url to handle "A:B" where "A:" is not a ns, even though PageTitle treats "A:" as a namespace
		byte[] canonical_bry = Bry_.new_u8(canonical_str);
		int page_bgn = Bry_find_.Move_fwd(canonical_bry, Xoh_href_.Bry__wiki, 0); if (page_bgn == Bry_find_.Not_found) throw Err_.new_("drd", "uknown url format: no '/wiki/'", "url", canonical_bry);
		byte[] page_bry = Bry_.Mid(canonical_bry, page_bgn, canonical_bry.length);	// get bry; EX: https://en.wikipedia.org/wiki/A -> A
		page_bry = Gfo_url_encoder_.Http_url.Decode(page_bry);						// decode %-encoding; convert + to space
		page_bry = Xoa_ttl.Replace_spaces(page_bry);								// convert spaces to unders; canonical-url has spaces
		return page_bry;
	}
}
