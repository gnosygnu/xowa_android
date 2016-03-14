package gplx.xowa.addons.searchs.specials; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.dbs.*;
import gplx.xowa.wikis.*; import gplx.xowa.wikis.domains.*; import gplx.xowa.wikis.data.*;
import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*; import gplx.xowa.addons.searchs.dbs.*; import gplx.xowa.addons.searchs.htmls.*; import gplx.xowa.addons.searchs.searchers.rslts.*;
public class Srch_special_searcher {
	private final Xoae_wiki_mgr wiki_mgr;
	private final Hash_adp_bry rslts_cache = Hash_adp_bry.cs(); private final Hash_adp_bry cmd_hash = Hash_adp_bry.cs();
	private final Srch_html_page_bldr html_page_bldr = new Srch_html_page_bldr();
	public Srch_special_searcher(Xoae_wiki_mgr wiki_mgr) {this.wiki_mgr = wiki_mgr;}
	public void Search(Xow_wiki search_wiki, Xoae_page page, Srch_qry qry) {
		// generate 1 cmd per wiki
		Xow_domain_itm[] domains_ary = qry.Wiki_domains; int domains_len = domains_ary.length;
		for (int i = 0; i < domains_len; ++i) {
			Xow_domain_itm domain = domains_ary[i];
			try {
				Xowe_wiki wiki = wiki_mgr.Get_by_or_make(domain.Domain_bry()); wiki.Init_assert();
				Assert_current_version(wiki);
				byte[] key = gplx.langs.htmls.Gfh_utl.Encode_id_as_bry(Bry_.Add(qry.Key, Byte_ascii.Pipe_bry, wiki.Domain_bry()));
				Srch_rslt_list rslts_list = this.Rslts__get_or_new(key);
				Srch_special_cmd cmd = new Srch_special_cmd(this, qry, wiki, page, page.Tab_data().Close_mgr(), page.Tab_data().Tab().Html_itm(), key, rslts_list);
				qry.Cmds__add(cmd);
			}	catch (Exception e) {Xoa_app_.Usr_dlg().Warn_many("", "", "search:wiki failed; wiki=~{0} err=~{1}", domain.Domain_str(), Err_.Message_lang(e));}	// handle bad wikis, like "en.wikipedia.org-old"; DATE:2015-04-24
		}

		// perform search for each cmd
		html_page_bldr.Init_by_wiki(search_wiki, search_wiki.Lang().Num_mgr(), qry);
		int cmds_len = qry.Cmds__len();
		Bry_bfr tmp_bfr = Bry_bfr.new_();
		for (int i = 0; i < cmds_len; ++i) {
			Srch_special_cmd cmd = qry.Cmds__get_at(i);
			byte[] cmd_key = cmd.key;
			cmd_hash.Add_if_dupe_use_nth(cmd_key, cmd);
			boolean async_db = cmd.Search();	// do search; note if async, will return immediately
			html_page_bldr.Bld_tbl(tmp_bfr, cmd.rslts_list, cmd_key, cmd.wiki.Domain_bry(), async_db, qry.Slab_bgn, qry.Slab_end);
		}

		// generate html; note if async, this will just generate the page header
		page.Data_raw_(html_page_bldr.Bld_page(tmp_bfr.To_bry_and_clear()));
	}
	public void Search__done(Srch_special_cmd cmd) {
		cmd_hash.Del(cmd.key);
	}
	public void Search__cancel(byte[] cmd_key) {
		Srch_special_cmd cmd = (Srch_special_cmd)cmd_hash.Get_by_bry(cmd_key); // if (cmd == null) return;	// ignore false calls to cancel
		cmd.Cancel();
		cmd_hash.Del(cmd.key);
	}
	private Srch_rslt_list Rslts__get_or_new(byte[] key) {
		Srch_rslt_list rv = (Srch_rslt_list)rslts_cache.Get_by_bry(key);
		if (rv == null) {
			rv = new Srch_rslt_list();
			rslts_cache.Add_bry_obj(key, rv);
		}
		return rv;
	}
	private void Assert_current_version(Xowe_wiki wiki) {
		Srch_db_upgrade upgrade_mgr = Srch_search_addon.Get(wiki).Db_mgr().Upgrade_mgr;
		if (upgrade_mgr.Version_check()) return;
		if (upgrade_mgr.Version() == Srch_db_upgrade.Version__link_score || !wiki.App().Mode().Tid_is_gui()) return;
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
