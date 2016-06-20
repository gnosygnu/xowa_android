package gplx.xowa.wikis.pages; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.xowa.guis.views.*;
public class Xowe_page_mgr {
	private final    Xowe_wiki wiki;
	public Xowe_page_mgr(Xowe_wiki wiki) {this.wiki = wiki;}
	public Xoae_page Load_page(Xoa_url url, Xoa_ttl ttl, Xog_tab_itm tab) {
		Xoa_app_.Usr_dlg().Log_many("", "", "page.load: url=~{0}", url.To_str());
		Wait_for_popups();
		Xowe_wiki_.Rls_mem_if_needed(wiki);

		// load page meta; wait_for_popups
		Xoae_page page = wiki.Data_mgr().Load_page_and_parse(url, ttl, wiki.Lang(), tab, false);
		boolean hdump_exists = page.Revision_data().Html_db_id() != -1 && wiki.Appe().Api_root().Wiki().Hdump().Read_preferred();
		page.Html_data().Hdump_exists_(hdump_exists);
		Wait_for_popups();

		// load page text
		if (hdump_exists)
			wiki.Html__hdump_mgr().Load_mgr().Load_by_edit(page);
		else
			wiki.Parser_mgr().Parse(page, false);
		return page;
	}
	private static void Wait_for_popups() {// HACK: wait for popups to finish, else thread errors due to popups and loader mutating cached items
		int wait_count = 0;
		while (gplx.xowa.htmls.modules.popups.Xow_popup_mgr.Running() && ++wait_count < 100)
			gplx.core.threads.Thread_adp_.Sleep(10);
	}
}