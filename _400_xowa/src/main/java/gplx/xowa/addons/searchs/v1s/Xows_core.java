package gplx.xowa.addons.searchs.v1s; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.dbs.*;
import gplx.xowa.wikis.*; import gplx.xowa.wikis.domains.*; import gplx.xowa.wikis.data.*;
import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.dbs.*;
public class Xows_core {
	private final Xoae_wiki_mgr wiki_mgr;
	private final Hash_adp_bry cache_hash = Hash_adp_bry.cs(); private final Hash_adp_bry cmd_hash = Hash_adp_bry.cs();
	public Xows_core(Xoae_wiki_mgr wiki_mgr) {this.wiki_mgr = wiki_mgr;}
	private final Xows_html_wkr html_wkr = new Xows_html_wkr();
	public Srch_rslt_reg Get_cache_or_new(byte[] key) {
		Srch_rslt_reg cache = (Srch_rslt_reg)cache_hash.Get_by_bry(key);
		if (cache == null) {
			cache = new Srch_rslt_reg();
			cache_hash.Add_bry_obj(key, cache);
		}
		return cache;
	}
	public void Search(Xow_wiki search_wiki, Xoae_page page, Srch_qry qry) {
		// generate 1 cmd per wiki
		Xow_domain_itm[] domain_ary = qry.wiki_domains; int domain_ary_len = domain_ary.length;
		for (int i = 0; i < domain_ary_len; ++i) {
			Xow_domain_itm domain = domain_ary[i];
			try {
				Xowe_wiki wiki = wiki_mgr.Get_by_or_make(domain.Domain_bry()); wiki.Init_assert();
				Assert_current_version(wiki);
				Xows_ui_cmd cmd = new Xows_ui_cmd(this, qry, wiki, page, page.Tab_data().Close_mgr(), page.Tab_data().Tab().Html_itm(), null, null);	// null b/c args are only used by drd
				qry.Cmds__add(cmd);
			} catch (Exception e) {Xoa_app_.Usr_dlg().Warn_many("", "", "search:wiki failed; wiki=~{0} err=~{1}", domain.Domain_str(), Err_.Message_lang(e));}	// handle bad wikis, like "en.wikipedia.org-old"; DATE:2015-04-24
		}
		qry.page_max = Int_.Max_value;
		// do search and generate html
		html_wkr.Init_by_wiki(search_wiki, search_wiki.Lang().Num_mgr(), qry);
		int cmds_len = qry.Cmds__len();
		Bry_bfr tmp_bfr = Bry_bfr.new_();
		for (int i = 0; i < cmds_len; ++i) {
			Xows_ui_cmd cmd = qry.Cmds__get_at(i); byte[] cmd_key = cmd.Key();
			cmd_hash.Add_if_dupe_use_nth(cmd_key, cmd);
			boolean searching_db = cmd.Search();				
			html_wkr.Gen_tbl(tmp_bfr, cmd.Rslt(), cmd_key, cmd.Wiki().Domain_bry(), searching_db);
		}
		page.Data_raw_(html_wkr.Gen_page(tmp_bfr.To_bry_and_clear()));
	}
	public void Search_end(Xows_ui_cmd cmd) {
		cmd_hash.Del(cmd.Key());
	}
	public void Cancel(byte[] cmd_key) {
		Xows_ui_cmd cmd = (Xows_ui_cmd)cmd_hash.Get_by_bry(cmd_key);
		if (cmd == null) return;
		cmd.Cancel();
		cmd_hash.Del(cmd.Key());
	}
	private void Assert_current_version(Xowe_wiki wiki) {
		Srch_db_upgrade upgrade_mgr = Srch_search_addon.Get(wiki).Db_mgr().Upgrade_mgr;
		if (upgrade_mgr.Version_check()) return;
		if (upgrade_mgr.Version() == Srch_db_upgrade.Version__current || !wiki.App().Mode().Tid_is_gui()) return;
		boolean ok = wiki.Appe().Gui_mgr().Kit().Ask_ok_cancel("", "", String_.Concat_lines_nl_skip_last
		( "XOWA would like to upgrade your search database for " + wiki.Domain_str() + "."
		, "* Press OK to upgrade. This may take a few minutes."
		, "* Press Cancel to skip. You will be able to search, but the results may be slower for multi-word searches (EX: 'The Earth')."
		, "If you cancel, XOWA will ask you to upgrade this wiki again next time you restart the application."
		));
		if (ok)
			upgrade_mgr.Upgrade();
	}
}
