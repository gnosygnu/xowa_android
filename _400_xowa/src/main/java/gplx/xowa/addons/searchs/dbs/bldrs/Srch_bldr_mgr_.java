package gplx.xowa.addons.searchs.dbs.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.dbs.*;
import gplx.xowa.bldrs.*;
import gplx.xowa.addons.searchs.dbs.bldrs.cmds.*;
public class Srch_bldr_mgr_ {
	public static void Setup(Xowe_wiki wiki) {
		Xoae_app app = wiki.Appe();
		Xob_bldr bldr = app.Bldr();

		bldr.Cmd_mgr().Add_many(wiki, Xob_cmd_keys.Key_text_search_cmd);
		int page_rank_iterations = app.Api_root().Bldr().Wiki().Import().Page_rank().Iteration_max();
		boolean page_rank_enabled = page_rank_iterations > 0;
		if (page_rank_enabled) {
			bldr.Cmd_mgr().Add(new gplx.xowa.bldrs.cmds.utils.Xob_download_cmd(bldr, wiki).Dump_type_(gplx.xowa.addons.pagelinks.bldrs.Pglnk_bldr_cmd.Dump_type_key));
			bldr.Cmd_mgr().Add_many(wiki, Xob_cmd_keys.Key_wiki_page_link);
		}
		bldr.Cmd_mgr().Add(new Srch__page__page_score(bldr, wiki).Iteration_max_(page_rank_iterations));
		bldr.Cmd_mgr().Add(new Srch__link__link_score(bldr, wiki).Page_rank_enabled_(page_rank_enabled).Delete_plink_db_(Bool_.Y));
		bldr.Cmd_mgr().Add(new Srch__word__link_count(bldr, wiki));			
		bldr.Cmd_mgr().Add(new gplx.xowa.bldrs.cmds.utils.Xob_delete_cmd(bldr, wiki).Patterns_ary_("*pagelinks.sql", "*pagelinks.sql.gz", "*pagelinks.sqlite3"));
	}
}
