package gplx.xowa.addons.apps.searchs.specials; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*; import gplx.xowa.addons.apps.searchs.*;
import gplx.xowa.wikis.*; import gplx.xowa.wikis.domains.*;
import gplx.xowa.addons.apps.searchs.searchers.*; import gplx.xowa.addons.apps.searchs.specials.htmls.*; import gplx.xowa.addons.apps.searchs.searchers.rslts.*;
public class Srch_special_searcher {
	private final    Xoae_wiki_mgr wiki_mgr;
	private final    Ordered_hash cancel_hash = Ordered_hash_.New_bry();
	private final    Srch_html_page_bldr html_page_bldr = new Srch_html_page_bldr();
	public Srch_special_searcher(Xoae_wiki_mgr wiki_mgr) {this.wiki_mgr = wiki_mgr;}
	public void Search(Xow_wiki search_wiki, Xoae_page page, boolean search_is_async, Xow_domain_itm[] domains_ary, Srch_search_qry qry) {
		Bry_bfr tmp_bfr = Bry_bfr.new_();
		html_page_bldr.Init_by_wiki(search_wiki, search_wiki.Lang().Num_mgr(), qry);
		int domains_len = domains_ary.length;
		for (int i = 0; i < domains_len; ++i) {
			Xow_domain_itm domain = domains_ary[i];
			try {
				Xowe_wiki wiki = wiki_mgr.Get_by_or_make(domain.Domain_bry()); wiki.Init_assert();
				byte[] key = gplx.langs.htmls.Gfh_utl.Encode_id_as_bry(Bry_.Add(qry.Phrase.Orig, Byte_ascii.Pipe_bry, qry.Ns_mgr.To_hash_key(), Byte_ascii.Pipe_bry, wiki.Domain_bry()));
				Srch_special_cmd cmd = new Srch_special_cmd(this, qry, wiki, page.Tab_data().Close_mgr(), page.Tab_data().Tab().Html_itm(), key, search_is_async);
				cancel_hash.Add(key, cmd);
				cmd.Search();	// do search; note if async, will return immediately
				html_page_bldr.Bld_tbl(tmp_bfr, new Srch_rslt_list(), key, cmd.wiki.Domain_bry(), search_is_async, qry.Slab_bgn, qry.Slab_end);
			}	catch (Exception e) {Xoa_app_.Usr_dlg().Warn_many("", "", "search:wiki failed; wiki=~{0} err=~{1}", domain.Domain_str(), Err_.Message_lang(e));}	// handle bad wikis, like "en.wikipedia.org-old"; DATE:2015-04-24
		}

		// generate html; note if async, this will just generate the page header
		page.Data_raw_(html_page_bldr.Bld_page(tmp_bfr.To_bry_and_clear()));
	}
	public void Search__done(Srch_special_cmd cmd) {
		cancel_hash.Del(cmd.key);
	}
	public void Search__cancel(byte[] cmd_key) {
		Srch_special_cmd cmd = (Srch_special_cmd)cancel_hash.Get_by(cmd_key); // if (cmd == null) return;	// ignore false calls to cancel
		cmd.On_cancel();
		cancel_hash.Del(cmd.key);
	}
}
