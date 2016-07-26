package gplx.xowa.bldrs.cmds.ctgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.core.envs.*;
import gplx.dbs.*; import gplx.xowa.wikis.dbs.*; import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.bldrs.sqls.*;
import gplx.xowa.wikis.data.*;
public class Xoctg_hiddencat_parser_sql extends Xoctg_hiddencat_parser_base {
	private Xowd_cat_core_tbl tbl;
	public Xoctg_hiddencat_parser_sql(Xob_bldr bldr, Xowe_wiki wiki) {this.Ctor(bldr, wiki);}
	@Override public String Cmd_key() {return Xob_cmd_keys.Key_text_cat_hidden;}
	@Override public void Cmd_bgn_hook(Xob_bldr bldr, Sql_file_parser parser) {
		super.Cmd_bgn_hook(bldr, parser);
		Xodb_mgr_sql db_mgr = wiki.Db_mgr_as_sql();
		tbl = db_mgr.Core_data_mgr().Db__cat_core().Tbl__cat_core();
		tbl.Update_bgn();
	}
	@Override public void Exec_hook(Bry_bfr file_bfr, int cur_id, boolean cur_is_hiddencat) {
		if (cur_is_hiddencat)
			tbl.Update_by_batch(cur_id, cur_is_hiddencat ? Bool_.Y_byte : Bool_.N_byte);
	}
	@Override public void Cmd_end() {
		tbl.Update_end();
		if (!Env_.Mode_testing())	// NOTE: do not delete when testing
			Io_mgr.Instance.DeleteDirDeep(wiki.Fsys_mgr().Tmp_dir());	// delete /wiki/wiki_name/tmp

		// cleanup; delete files;
		Io_url wiki_root_dir = wiki.Fsys_mgr().Root_dir();
		gplx.xowa.bldrs.wkrs.Xob_io_utl_.Delete_sql_files(wiki_root_dir, this.Sql_file_name());
		Io_mgr.Instance.DeleteFil_args(wiki_root_dir.GenSubFil("xowa_categorylinks.sql")).MissingFails_off().Exec();
	}
}
