package gplx.xowa.bldrs.cmds.texts.sqls; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*; import gplx.xowa.bldrs.cmds.texts.*;
import gplx.dbs.cfgs.*; import gplx.xowa.wikis.dbs.*; import gplx.xowa.wikis.*;
public class Xob_term_cmd extends Xob_term_base {
	public Xob_term_cmd(Xob_bldr bldr, Xowe_wiki wiki) {this.Ctor(bldr, wiki); this.wiki = wiki;} private Xowe_wiki wiki;
	@Override public String Cmd_key() {return KEY;} public static final    String KEY = "text.term";
	@Override public void Cmd_end_hook() {
		Io_mgr.Instance.DeleteDirDeep(wiki.Fsys_mgr().Tmp_dir());
		Db_cfg_tbl cfg_tbl = wiki.Data__core_mgr().Tbl__cfg();
		cfg_tbl.Insert_bry(Xow_cfg_consts.Grp__wiki_init, Xow_cfg_consts.Key__init__bldr_version, wiki.Props().Bldr_version());
		cfg_tbl.Insert_bry(Xow_cfg_consts.Grp__wiki_init, Xow_cfg_consts.Key__init__main_page, wiki.Props().Main_page());
		cfg_tbl.Insert_bry(Xow_cfg_consts.Grp__wiki_init, "props.siteinfo_misc", wiki.Props().Siteinfo_misc());
		cfg_tbl.Insert_bry(Xow_cfg_consts.Grp__wiki_init, "props.siteinfo_mainpage", wiki.Props().Siteinfo_mainpage());
		gplx.fsdb.Fsdb_db_mgr__v2_bldr.Get_or_make(wiki, false);// always build file.user db; DATE:2015-05-12
		if (wiki.Appe().Api_root().Bldr().Wiki().Filter().Dansguardian().Enabled())	// if dansguardian, delete missing pages; DATE:2016-01-06
			new Xob_page_delete_cmd(wiki.Appe().Bldr(), wiki).Cmd_run();
		wiki.Data__core_mgr().Rls();
	}
}
