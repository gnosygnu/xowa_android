package gplx.xowa.addons.sqlite_utils.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.sqlite_utils.*;
import gplx.dbs.*; import gplx.dbs.qrys.*; import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
public class Sqlite_percentile_cmd extends Xob_cmd__base implements Xob_cmd {
	private String db_rel_url, tbl_name = "tmp_score"; private int score_max = 100000000; private String select_sql;
	public Sqlite_percentile_cmd(Xob_bldr bldr, Xowe_wiki wiki) {super(bldr, wiki);}
	public Sqlite_percentile_cmd Init(String db_rel_url, String tbl_name, int score_max, String select_sql) {
		this.db_rel_url = db_rel_url; this.tbl_name = tbl_name; this.score_max = score_max; this.select_sql = select_sql;
		return this;
	}
	@Override public String Cmd_key() {return Xob_cmd_keys.Key_util_sqlite_normalize;}
	@Override public void Cmd_run() {
		if (db_rel_url == null) throw Err_.new_("bldr", "db_rel_url can not be empty; EX: 'xowa.page_rank.sqlite3'");
		wiki.Init_assert();
		Db_conn conn = Db_conn_bldr.Instance.Get_or_autocreate(false, wiki.Fsys_mgr().Root_dir().GenSubFil(db_rel_url));

		Xoa_app_.Usr_dlg().Prog_many("", "", "creating temp_table: tbl=~{0}", tbl_name);
		conn.Meta_tbl_drop(tbl_name);
		conn.Meta_tbl_create
		( Dbmeta_tbl_itm.New(tbl_name
		,	Dbmeta_fld_itm.new_int("row_rank").Primary_y_().Autonum_y_()
		,	Dbmeta_fld_itm.new_int("row_key")
		,	Dbmeta_fld_itm.new_double("row_val")
		,	Dbmeta_fld_itm.new_double("row_score").Default_(-1)
		));

		Xoa_app_.Usr_dlg().Prog_many("", "", "filling temp_table: tbl=~{0} sql=~{1}", tbl_name, select_sql);
		new Db_attach_mgr(conn, new Db_attach_itm("page_db", wiki.Data__core_mgr().Tbl__page().conn))
			.Exec_sql(Bry_fmt.Make_str("INSERT INTO ~{tbl} (row_key, row_val) ~{select}", tbl_name, select_sql));

		Xoa_app_.Usr_dlg().Prog_many("", "", "updating row_score: tbl=~{0}", tbl_name);
		String score_max_as_str = Dbmeta_fld_itm.To_double_str_by_int(score_max);
		int count = conn.Exec_select_as_int("SELECT Count(*) FROM " + tbl_name, -1); if (count == -1) throw Err_.new_("bldr", "failed to get count; tbl=~{0}", tbl_name);
		String count_as_str = Dbmeta_fld_itm.To_double_str_by_int(count);
		conn.Exec_sql(Bry_fmt.Make_str("UPDATE ~{tbl} SET row_score = (row_rank * ~{score_max}) / ~{count}", tbl_name, score_max_as_str, count_as_str));

		Xoa_app_.Usr_dlg().Prog_many("", "", "resolving ties: tbl=~{0}", tbl_name);
		conn.Meta_tbl_drop(tbl_name + "_avg");
		conn.Meta_tbl_create
		( Dbmeta_tbl_itm.New(tbl_name + "_avg"
		,	Dbmeta_fld_itm.new_double("row_val").Primary_y_()
		,	Dbmeta_fld_itm.new_double("row_score")
		));
		conn.Exec_sql(Bry_fmt.Make_str(String_.Concat_lines_nl_skip_last
		( "INSERT INTO ~{tbl}_avg (row_val, row_score)"
		, "SELECT   row_val"
		, ",        (Avg(row_rank) * ~{score_max} / ~{count}) AS row_score"
		, "FROM     ~{tbl}"
		, "GROUP BY row_val"
		, "HAVING   Count(row_val > 1)"
		), tbl_name, score_max_as_str, count_as_str));

		conn.Exec_sql(Bry_fmt.Make_str(String_.Concat_lines_nl_skip_last
		( "UPDATE   ~{tbl}"
		, "SET      row_score = (SELECT row_score FROM ~{tbl}_avg t2 WHERE t2.row_val = ~{tbl}.row_val)"
		, "WHERE    row_val IN (SELECT row_val FROM ~{tbl}_avg t2)"
		), tbl_name));
		conn.Meta_idx_create(Xoa_app_.Usr_dlg(), Dbmeta_idx_itm.new_normal_by_tbl(tbl_name, "row_score", "row_key", "row_score"));
	}
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__db_rel_url_))			db_rel_url = m.ReadStr("v");
		else if	(ctx.Match(k, Invk__select_sql_))			select_sql = m.ReadStr("v");
		else if	(ctx.Match(k, Invk__tbl_name_))				tbl_name = m.ReadStr("v");
		else if	(ctx.Match(k, Invk__score_max_))			score_max = m.ReadInt("v");
		else												return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String Invk__db_rel_url_ = "db_rel_url_", Invk__select_sql_ = "select_sql_", Invk__tbl_name_ = "tbl_name_", Invk__score_max_ = "score_max_";
}
