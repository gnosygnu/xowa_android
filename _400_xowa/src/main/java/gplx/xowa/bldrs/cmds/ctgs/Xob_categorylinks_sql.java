package gplx.xowa.bldrs.cmds.ctgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.core.ios.*;
import gplx.xowa.bldrs.sqls.*;
public class Xob_categorylinks_sql extends Xob_categorylinks_base {
	private Db_idx_mode idx_mode = Db_idx_mode.Itm_end;
	public Xob_categorylinks_sql(Xob_bldr bldr, Xowe_wiki wiki) {this.Cmd_ctor(bldr, wiki); this.make_fil_len = Io_mgr.Len_mb;}
	@Override public String Cmd_key() {return Xob_cmd_keys.Key_text_cat_link;}
	@Override public Io_sort_cmd Make_sort_cmd(Sql_file_parser sql_parser) {return new Xob_categorylinks_sql_make(sql_parser, wiki, idx_mode);}
	public static final String Sql_categorylinks = String_.Concat_lines_nl
	( "CREATE TABLE `categorylinks` ("
	, "  `cl_from` int(10) unsigned NOT NULL DEFAULT '0',"
	, "  `cl_to` varbinary(255) NOT NULL DEFAULT '',"
	, "  `cl_sortkey` varbinary(230) NOT NULL DEFAULT '',"
	, "  `cl_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
	, "  `cl_sortkey_prefix` varbinary(255) NOT NULL DEFAULT '',"
	, "  `cl_collation` varbinary(32) NOT NULL DEFAULT '',"
	, "  `cl_type` enum('page','subcat','file') NOT NULL DEFAULT 'page',"
	, "  UNIQUE KEY `cl_from` (`cl_from`,`cl_to`),"
	, ");"
	);
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_idx_mode_))					idx_mode = Db_idx_mode.Xto_itm(m.ReadStr("v"));
		else													return super.Invk(ctx, ikey, k, m);
		return this;
	}
	private static final String Invk_idx_mode_ = "idx_mode_";
}
