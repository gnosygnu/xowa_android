package gplx.xowa.drds; import gplx.*; import gplx.xowa.*;
import gplx.xowa.drds.pages.*; import gplx.xowa.drds.files.*;
import gplx.xowa.apps.*; import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.files.gui.*;
import gplx.xowa.specials.search.*;
public class Xod_app {
	private final Xoav_app app;
	private final Xod_page_mgr page_mgr = new Xod_page_mgr();
	private final Xod_file_mgr file_mgr = new Xod_file_mgr();
	public Xod_app(Xoav_app app) {
		this.app = app;
	}
	public int Get_wiki_count() {
		int rv = app.Wiki_mgri().Count();
		return rv - 1;	// ignore home wiki
	}
	public Xow_wiki Get_wiki(String wiki_domain) {
		Xow_wiki rv = app.Wiki_mgri().Get_by_key_or_make_init_y(Bry_.new_u8(wiki_domain));
		if (rv != null && rv.Data__core_mgr() == null) rv.Init_by_wiki();
		return rv;
	}
	public Xod_page_itm Get_page(Xow_wiki wiki, Xoa_url page_url) {
		return page_mgr.Get_page(wiki, page_url);
	}
	public void Load_files(Xow_wiki wiki, Xod_page_itm pg, Xog_js_wkr js_wkr) {
		file_mgr.Load_files(wiki, pg, js_wkr);
	}
	public String[] Search_titles(Cancelable cancelable, Xow_wiki wiki, Xows_ui_async ui_async, String search) {
		Xows_db_wkr search_wkr = new Xows_db_wkr();
		Xows_db_row[] rows = search_wkr.Search_by_drd(cancelable, wiki, ui_async, Bry_.new_u8(search), 50);
		int len = rows.length;
		String[] rv = new String[len];
		for (int i = 0; i < len; ++i) {
			rv[i] = String_.new_u8(rows[i].Page_ttl().Page_txt());
		}
		return rv;
	}
}
