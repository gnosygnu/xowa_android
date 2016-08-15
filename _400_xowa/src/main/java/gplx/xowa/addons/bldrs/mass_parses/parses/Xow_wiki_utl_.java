package gplx.xowa.addons.bldrs.mass_parses.parses; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.mass_parses.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.cases.*;
import gplx.xowa.files.*;
public class Xow_wiki_utl_ {
	public static Xowe_wiki Clone_wiki(Xowe_wiki wiki, Io_url wiki_dir) {
		Xol_lang_itm lang = new Xol_lang_itm(wiki.App().Lang_mgr(), wiki.Lang().Key_bry());
		Xol_lang_itm_.Lang_init(lang);
		Xowe_wiki rv = new Xowe_wiki(wiki.Appe(), lang, gplx.xowa.wikis.nss.Xow_ns_mgr_.default_(lang.Case_mgr()), wiki.Domain_itm(), wiki_dir);
		rv.Init_by_wiki();
		rv.File_mgr().Repo_mgr().Clone(wiki.File_mgr().Repo_mgr());
		rv.File__fsdb_mode().Tid__v2__bld__y_();

		// force all repos to be lnx; will not convert characters like *,",? to _; also force long titles
		Clone_repos(wiki);

		return rv;
	}
	public static void Clone_repos(Xowe_wiki wiki) {
		// force all repos to be lnx; will not convert characters like *,",? to _; also force long titles
		Xoa_repo_mgr repo_mgr = wiki.Appe().File_mgr().Repo_mgr();
		int len = repo_mgr.Count();
		for (int i = 0; i < len; ++i)
			repo_mgr.Get_at(i).Fsys_is_wnt_(Bool_.N).Shorten_ttl_(Bool_.N);

	}
}
