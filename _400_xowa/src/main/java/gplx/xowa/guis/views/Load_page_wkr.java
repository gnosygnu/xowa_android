package gplx.xowa.guis.views; import gplx.*; import gplx.xowa.*; import gplx.xowa.guis.*;
import gplx.core.threads.*; import gplx.core.envs.*;
public class Load_page_wkr implements Gfo_thread_wkr {
	private final    Xog_tab_itm tab;
	public Load_page_wkr(Xog_tab_itm tab, Xowe_wiki wiki, Xoa_url url, Xoa_ttl ttl) {this.tab = tab; this.wiki = wiki; this.url = url; this.ttl = ttl;}
	public String				Thread__name()		{return "xowa.load_page_wkr";}
	public boolean				Thread__resume()	{return false;}
	public Xowe_wiki			Wiki()				{return wiki;}			private final    Xowe_wiki wiki;
	public Xoa_url				Url()				{return url;}			private final    Xoa_url url;
	public Xoa_ttl				Ttl()				{return ttl;}			private final    Xoa_ttl ttl;
	public Xoae_page			Page()				{return page;}			private Xoae_page page;
	public Exception		Exec_err()			{return exec_err;}		private Exception exec_err;
	public void Thread__exec() {
		try {
			Running_(true);

			// wait_for_popups; free mem check;
			Xoa_app_.Usr_dlg().Log_many("", "", "page.load: url=~{0}", url.To_str());
			Wait_for_popups();
			Xowe_wiki_.Rls_mem_if_needed(wiki);

			// load page meta; wait_for_popups
			this.page = wiki.Data_mgr().Load_page_and_parse(url, ttl, wiki.Lang(), tab, false);
			boolean hdump_exists = page.Revision_data().Html_db_id() != -1 && wiki.Appe().Api_root().Wiki().Hdump().Read_preferred();
			page.Html_data().Hdump_exists_(hdump_exists);
			Wait_for_popups();

			// load page text
			if (hdump_exists)
				wiki.Html__hdump_mgr().Load_mgr().Load_by_edit(page);
			else
				wiki.Parser_mgr().Parse(page, false);

			// launch thread to show page
			Gfo_invk_.Invk_by_val(tab.Cmd_sync(), Xog_tab_itm.Invk_show_url_loaded_swt, this);
		}
		catch (Exception e) {
			this.exec_err = e;
			Gfo_invk_.Invk_by_val(tab.Cmd_sync(), Xog_tab_itm.Invk_show_url_failed_swt, this);
		}
		finally {
			Running_(false);
		}
	}
	private static void Wait_for_popups() {// HACK: wait for popups to finish, else thread errors due to popups and loader mutating cached items
		int wait_count = 0;
		while (gplx.xowa.htmls.modules.popups.Xow_popup_mgr.Running() && ++wait_count < 100)
			Thread_adp_.Sleep(10);
	}
	private static final    Object thread_lock = new Object(); private static boolean running = false;
	public static boolean Running() {
		boolean rv = false;
		synchronized (thread_lock) {
			rv = running;
		}
		return rv;
	}
	private static void Running_(boolean v) {
		synchronized (thread_lock) {
			running = v;
		}
	}
}
