package gplx.xowa.wikis.data; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.core.lists.*; /*ComparerAble*/ import gplx.xowa.bldrs.cmds.ctgs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.vnts.*;
import gplx.xowa.wikis.nss.*;
import gplx.xowa.guis.views.*;
import gplx.xowa.wikis.dbs.*; import gplx.xowa.wikis.*; import gplx.xowa.langs.msgs.*;
import gplx.xowa.parsers.utils.*;
import gplx.xowa.wikis.data.tbls.*;
public class Xow_data_mgr implements GfoInvkAble {
	private Xop_redirect_mgr redirect_mgr;
	private Xoa_url tmp_url = Xoa_url.blank();
	public Xow_data_mgr(Xowe_wiki wiki) {this.wiki = wiki; this.redirect_mgr = wiki.Redirect_mgr();}
	public Xowe_wiki Wiki() {return wiki;} private Xowe_wiki wiki;
	public boolean Version_is_1() {return Bool_.Y;}
	public Xoae_page Get_page(Xoa_ttl ttl, boolean called_from_tmpl) {tmp_url = wiki.Utl__url_parser().Parse(ttl.Raw()); return Get_page(tmp_url, ttl, called_from_tmpl, false);}
	public Xoae_page Get_page_from_msg(Xoa_ttl ttl) {tmp_url = wiki.Utl__url_parser().Parse(ttl.Raw()); return Get_page(tmp_url, ttl, false, true);}
	public Xoae_page Get_page(Xoa_url url, Xoa_ttl ttl, boolean called_from_tmpl, boolean called_from_msg) {
		Xoae_page rv = Xoae_page.New(wiki, ttl);
		return Get_page(rv, url, ttl, called_from_tmpl, called_from_msg);
	}
	public Xoae_page Get_page(Xoae_page rv, Xoa_url url, Xoa_ttl ttl, boolean called_from_tmpl, boolean called_from_msg) {
		rv.Url_(url);	// NOTE: must update page.Url(); should combine with Xoae_page.New()
		Xow_ns ns = ttl.Ns();
		switch (ns.Id()) {
			case Xow_ns_.Tid__special:
				wiki.Special_mgr().Special_gen(wiki, rv, url, ttl);
				return rv;
			case Xow_ns_.Tid__mediawiki:
				if (	!called_from_msg	// if called from msg, fall through to actual data retrieval below, else infinite loop; DATE:2014-05-09
					&&	Xow_page_tid.Identify_by_ttl(ttl.Page_db()) == Xow_page_tid.Tid_wikitext // skip ".js" and ".css" pages in MediaWiki; DATE:2014-06-13
					) {		
					Xol_lang_itm lang = wiki.Lang();
					byte[] msg_key = ttl.Page_db();
					Bry_bfr tmp_bfr = wiki.Utl__bfr_mkr().Get_b512();
					msg_key = lang.Case_mgr().Case_build_1st_lower(tmp_bfr, msg_key, 0, msg_key.length);
					byte[] msg_val = Xol_msg_mgr_.Get_msg_itm(tmp_bfr, wiki, wiki.Lang(), msg_key).Val();	// NOTE: do not change to Get_msg_val; Get_msg_val, also replaces $1 with values, and $1 needs to be preserved for callers;
					rv.Data_raw_(msg_val);
					tmp_bfr.Mkr_rls();
					return rv;
				}
				break;
		}
		return Get_page(rv, ns, ttl, called_from_tmpl, url.Qargs_mgr().Match(Xoa_url_.Qarg__redirect, Xoa_url_.Qarg__redirect__no));
	}
	public Xoae_page Get_page(Xoae_page rv, Xow_ns ns, Xoa_ttl ttl, boolean called_from_tmpl, boolean redirect_force) {
		int redirects = 0;
		Xowd_page_itm db_page = Xowd_page_itm.new_tmp();
		while (true) {
			boolean exists = wiki.Db_mgr().Load_mgr().Load_by_ttl(db_page, ns, ttl.Page_db());
			if (!exists) return rv.Missing_();
			if (wiki.App().Mode().Tid_is_gui())	// NOTE: must check if gui, else will write during mass build; DATE:2014-05-03
				wiki.Appe().Usr_dlg().Prog_many(GRP_KEY, "file_load", "loading page for ~{0}", String_.new_u8(ttl.Raw()));
			wiki.Db_mgr().Load_mgr().Load_page(db_page, ns, !called_from_tmpl);
			byte[] bry = db_page.Text();
			rv.Data_raw_(bry).Revision_data().Modified_on_(db_page.Modified_on()).Id_(db_page.Id()).Html_db_id_(db_page.Html_db_id());
			if (redirect_force) return rv;
			Xoa_ttl redirect_ttl = redirect_mgr.Extract_redirect(bry);
			if  (	redirect_ttl == null				// not a redirect
				||	redirects++ > 4)					// too many redirects; something went wrong
				break;				
			rv.Redirected_ttls().Add(ttl.Full_url());	// NOTE: must be url_encoded; EX: "en.wikipedia.org/?!" should generate link of "en.wikipedia.org/%3F!?redirect=no"
			if (rv.Redirected_src() == null) rv.Redirected_src_(bry);	// only add src for first redirect; DATE:2014-07-11
			rv.Ttl_(redirect_ttl);
			ns = redirect_ttl.Ns();
			ttl = redirect_ttl;
		}
		return rv;
	}
	public Xoae_page Load_page_by_ttl(Xoa_url url, Xoa_ttl ttl)						{return Load_page_by_ttl(url, ttl, wiki.Lang(), wiki.Appe().Gui_mgr().Browser_win().Active_tab(), true);}
	public Xoae_page Load_page_by_ttl(Xoa_url url, Xoa_ttl ttl, Xog_tab_itm tab)	{return Load_page_by_ttl(url, ttl, wiki.Lang(), tab, true);}
	public Xoae_page Load_page_by_ttl(Xoa_url url, Xoa_ttl ttl, Xol_lang_itm lang, Xog_tab_itm tab, boolean parse_page) {
		wiki.Init_assert();
		Xoae_page page = Xoae_page.New(wiki, ttl); page.Tab_data().Tab_(tab);
		this.Get_page(page, url, ttl, false, false);						// get page from data_mgr
		if (page.Missing()) {													// page doesn't exist
			boolean vnt_missing = true;
			Xol_vnt_mgr vnt_mgr = lang.Vnt_mgr();
			if (vnt_mgr.Enabled()) {	// if vnt enabled, then try to load by vnt form; DATE:2015-09-15
				gplx.xowa.wikis.data.tbls.Xowd_page_itm page_itm = vnt_mgr.Convert_mgr().Convert_ttl(wiki, ttl);
				if (page_itm != null && page_itm.Exists()) {
					Xoa_ttl vnt_ttl = Xoa_ttl.parse(wiki, ttl.Ns().Id(), page_itm.Ttl_page_db());
					page = this.Get_page(vnt_ttl, false);
					vnt_missing = page.Missing();
				}
			}
			if (vnt_missing) {
				if (ttl.Ns().Id_is_file()) {
					Xowe_wiki commons_wiki = (Xowe_wiki)wiki.Appe().Wiki_mgr().Get_by_or_null(wiki.Commons_wiki_key());
					if (commons_wiki != null) {										// commons exists
						if (!Bry_.Eq(wiki.Domain_bry(), commons_wiki.Domain_bry())) {		// !Bry_.Eq is recursion guard
							Xoae_page rv = commons_wiki.Data_mgr().Load_page_by_ttl(url, ttl, wiki.Lang(), tab, true);
							if (rv.Exists()) {
								rv.Commons_mgr().Source_wiki_(wiki);
								return rv;
							}
							else {
								page.Missing_(false);
								page.Commons_mgr().Xowa_mockup_(true);
								return page;
							}
						}
					}
				}
				else
					return page.Missing_();
			}
		}
		if (page.Missing()) return page;										// NOTE: commons can return null page
		page.Tab_data().Tab_(tab);
		page.Lang_(lang);
		if (parse_page)
			wiki.Parser_mgr().Parse(page, false);	// NOTE: do not clear page b/c reused for search
		return page;
	}
	public Xoae_page Redirect(Xoae_page page, byte[] page_bry) {
		Xoa_ttl trg_ttl = Xoa_ttl.parse(wiki, page_bry);
		Xoa_url trg_url = Xoa_url.new_(wiki.Domain_bry(), page_bry);
		page.Ttl_(trg_ttl).Url_(trg_url).Redirected_(true);
		return wiki.Data_mgr().Get_page(page, trg_ttl.Ns(), trg_ttl, false, trg_url.Qargs_mgr().Match(Xoa_url_.Qarg__redirect, Xoa_url_.Qarg__redirect__no));
	}
	public static final int File_idx_unknown = -1;
	static final String GRP_KEY = "xowa.wiki.data";
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_create_enabled_))				wiki.Db_mgr().Save_mgr().Create_enabled_(m.ReadYn("v"));
		else if	(ctx.Match(k, Invk_update_modified_on_enabled_))	wiki.Db_mgr().Save_mgr().Update_modified_on_enabled_(m.ReadYn("v"));
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}	private static final String Invk_create_enabled_ = "create_enabled_", Invk_update_modified_on_enabled_ = "update_modified_on_enabled_";
}
