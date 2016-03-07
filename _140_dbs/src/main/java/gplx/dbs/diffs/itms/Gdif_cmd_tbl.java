package gplx.dbs.diffs.itms; import gplx.*; import gplx.dbs.*; import gplx.dbs.diffs.*;
public class Gdif_cmd_tbl implements Rls_able {
	private String tbl_name = "gdif_cmd";
	private String fld_grp_id, fld_cmd_id, fld_tid, fld_data;
	private final Dbmeta_fld_list flds = Dbmeta_fld_list.new_();
	private final Db_conn conn; private Db_stmt stmt_insert;
	public Gdif_cmd_tbl(Db_conn conn) {
		this.conn = conn;
		fld_grp_id = flds.Add_int("grp_id"); fld_cmd_id = flds.Add_int("cmd_id"); fld_tid = flds.Add_int("tid"); fld_data = flds.Add_text("data");
		conn.Rls_reg(this);
	}
	public void Create_tbl() {conn.Meta_tbl_create(Dbmeta_tbl_itm.New(tbl_name, flds, Dbmeta_idx_itm.new_unique_by_tbl(tbl_name, "main", fld_grp_id, fld_cmd_id)));}
	public Gdif_cmd_itm Insert(int grp_id, int cmd_id, int tid, String data) {
		if (stmt_insert == null) stmt_insert = conn.Stmt_insert(tbl_name, flds);
		stmt_insert.Clear()
		.Val_int(fld_grp_id		, grp_id)
		.Val_int(fld_cmd_id		, cmd_id)
		.Val_int(fld_tid		, tid)
		.Val_str(fld_data		, data)
		.Exec_insert();
		return new Gdif_cmd_itm(grp_id, cmd_id, tid);
	}
	public void Rls() {
		stmt_insert = Db_stmt_.Rls(stmt_insert);
	}
}
