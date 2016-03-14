package gplx.xowa.addons.searchs.specials; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.core.primitives.*; import gplx.xowa.apps.apis.xowa.specials.*;
import gplx.xowa.wikis.domains.*; import gplx.xowa.wikis.domains.crts.*;
import gplx.xowa.addons.searchs.suggests.*;
import gplx.xowa.specials.*; import gplx.xowa.addons.searchs.searchers.*;
public class Srch_special_page implements Xows_page, GfoInvkAble, GfoEvObj {
	private final Xoae_app app; private final Xow_domain_itm wiki_domain; private final Xoapi_search search_api;		
	private final Srch_special_searcher search_mgr; private final Srch_qarg_mgr qargs_mgr = new Srch_qarg_mgr();
	private Xow_domain_itm[] search_domain_ary;
	public Srch_special_page(Xowe_wiki wiki) {
		this.ev_mgr = GfoEvMgr.new_(this);
		this.app = wiki.Appe();
		this.wiki_domain = wiki.Domain_itm();
		this.search_mgr = new Srch_special_searcher(wiki.Appe().Wiki_mgr());
		this.search_api = wiki.Appe().Api_root().Special().Search();
		GfoEvMgr_.SubSame_many(search_api, this, Xoapi_search.Evt_multi_wikis_changed, Xoapi_search.Evt_multi_wikis_changed);
	}
	public GfoEvMgr					EvMgr()					{return ev_mgr;} private final GfoEvMgr ev_mgr;
	public Xows_special_meta		Special_meta()			{return Xows_special_meta_.Itm__search;}
	public void Special_gen(Xowe_wiki wiki, Xoae_page page, Xoa_url url, Xoa_ttl ttl) {
		if (wiki.Domain_tid() == Xow_domain_tid_.Int__home) return;	// do not allow search in home wiki; will throw null ref error b/c no search_ttl dirs
		if (search_domain_ary == null) Multi_wikis_changed();

		// get args from urls while applying defaults from search_suggest_mgr
		Srch_suggest_cfg search_suggest_mgr = wiki.Appe().Gui_mgr().Search_suggest_mgr();
		qargs_mgr.Clear();
		qargs_mgr.Parse(search_suggest_mgr.Args_default());
		qargs_mgr.Parse(url.Qargs_ary());
		qargs_mgr.Ns_mgr().Add_main_if_empty();

		// get search_raw
		byte[] search_raw = qargs_mgr.Search_raw();
		if (search_raw == null) {					// search is not in qarg; EX:Special:Search?search=Earth
			search_raw = ttl.Leaf_txt_wo_qarg();	// assume search is in leaf; EX: Special:Search/Earth
			qargs_mgr.Search_raw_(search_raw);
		}
		if (Bry_.Len_eq_0(search_raw)) return;		// emptry String; exit now, else null ref error; DATE:2015-08-11
		if (	search_suggest_mgr.Auto_wildcard()	// add * automatically if option set
			&&	wiki.Db_mgr().Tid() == gplx.xowa.wikis.dbs.Xodb_mgr_sql.Tid_sql	// only apply to sql
			&&	Bry_find_.Find_fwd(search_raw, Byte_ascii.Star) == -1			// search term does not have asterisk
			)
			search_raw = Bry_.Add(search_raw, Byte_ascii.Star);

		// search wiki
		Xoa_ttl search_ttl = Xoa_ttl.parse(wiki, search_raw); 
		Xoae_page search_page = page;
		if (!Bry_.Eq(search_raw, Xows_special_meta_.Itm__search.Ttl_bry()))	// do not lookup self else stack overflow; happens when going directly to Special:Search (from history)
			search_page = wiki.Data_mgr().Get_page(search_ttl, false);		// try to find page; EX:Special:Search?search=Earth -> en.w:Earth; needed for search suggest

		// page not found, or explicit_search invoked
		if (search_page.Missing() || url.Qargs_mgr().Match(Qarg__fulltext, Qarg__fulltext__y)) {
			if (qargs_mgr.Cancel() != null) {	// cancel any existing searches
				search_mgr.Search__cancel(qargs_mgr.Cancel());
				page.Tab_data().Cancel_show_y_();
				return;
			}
			page.Html_data().Html_restricted_n_();
			page.Html_data().Xtn_search_text_(search_raw);
			int slab_idx = qargs_mgr.Slab_idx();
			int slab_len = search_api.Results_per_page();
			int slab_bgn = slab_idx * slab_len;
			int slab_end = slab_bgn + slab_len;
			search_raw = wiki.Case_mgr().Case_build_lower(search_raw);	// lowercase String
			Srch_qry qry = new Srch_qry(qargs_mgr.Ns_mgr(), search_raw, qargs_mgr.Slab_idx(), slab_bgn, slab_end, search_api.Async_db(), search_domain_ary);
			search_mgr.Search(wiki, page, qry);
		}
		// page found; return it;
		else {
			wiki.Parser_mgr().Parse(search_page, true);
			page.Data_raw_(search_page.Data_raw());
			if (page.Root() != null) // NOTE: null when going from w:Earth -> q:Earth; DATE:2013-03-20
				page.Root().Data_htm_(search_page.Root().Data_htm());
			page.Ttl_(search_ttl).Url_(Xoa_url.new_(wiki.Domain_bry(), search_ttl.Full_txt())).Redirected_(true);
		}
	}
	private void Multi_wikis_changed() {
		Xow_domain_crt_itm crt = search_api.Multi_wikis_crt(wiki_domain);
		this.search_domain_ary = Get_by_crt(app.Usere().Wiki().Xwiki_mgr(), wiki_domain, crt);
		if (search_domain_ary.length == 0) search_domain_ary = new Xow_domain_itm[] {wiki_domain};	// default to current if bad input
		Multi_sorts_changed();
	}
	private void Multi_sorts_changed() {
		Xow_domain_crt_itm[] ary = search_api.Multi_sorts_crt(wiki_domain);
		if (ary == null) return;	// default to no sort if bad input
		Xow_domain_sorter__manual sorter = new Xow_domain_sorter__manual(wiki_domain, ary);
		Xow_domain_sorter__manual.Sort(sorter, search_domain_ary);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Xoapi_search.Evt_multi_wikis_changed))				Multi_wikis_changed();
		else if	(ctx.Match(k, Xoapi_search.Evt_multi_sorts_changed))				Multi_sorts_changed();
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	public static final byte Match_tid_all = 0, Match_tid_bgn = 1;
	public static final byte Version_null = 0, Version_1 = 1, Version_2 = 2;
	private static final byte[] Qarg__fulltext = Bry_.new_a7("fulltext"), Qarg__fulltext__y = Bry_.new_a7("y");
	private static Xow_domain_itm[] Get_by_crt(gplx.xowa.wikis.xwikis.Xow_xwiki_mgr xwiki_mgr, Xow_domain_itm cur, gplx.xowa.wikis.domains.crts.Xow_domain_crt_itm crt) {
		List_adp rv = List_adp_.new_();
		int len = xwiki_mgr.Len();
		for (int i = 0; i < len; ++i) {
			gplx.xowa.wikis.xwikis.Xow_xwiki_itm xwiki = xwiki_mgr.Get_at(i); if (!xwiki.Offline()) continue;
			Xow_domain_itm domain_itm = Xow_domain_itm_.parse(xwiki.Domain_bry());
			if (crt.Matches(cur, domain_itm)) rv.Add(domain_itm);
		}
		return (Xow_domain_itm[])rv.To_ary_and_clear(Xow_domain_itm.class);
	}
}
