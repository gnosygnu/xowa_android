package gplx.xowa.wikis.data.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.data.*;
import gplx.dbs.*;
public class Xowd_css_file_tbl implements Rls_able {
	private final String tbl_name = "css_file"; private final Dbmeta_fld_list flds = Dbmeta_fld_list.new_();
	private final String fld_css_id, fld_path, fld_data;
	private final Db_conn conn; private Db_stmt stmt_insert;
	public Xowd_css_file_tbl(Db_conn conn) {
		this.conn = conn;
		fld_css_id	= flds.Add_int("css_id");
		fld_path	= flds.Add_str("file_path", 255);
		fld_data	= flds.Add_bry("file_data");
		conn.Rls_reg(this);
	}
	public void Rls() {
		stmt_insert = Db_stmt_.Rls(stmt_insert);
	}
	public void Create_tbl() {conn.Meta_tbl_create(Dbmeta_tbl_itm.New(tbl_name, flds));}
	public void Insert(int css_id, String path, byte[] data) {
		if (stmt_insert == null) stmt_insert = conn.Stmt_insert(tbl_name, flds);
		stmt_insert.Clear().Val_int(fld_css_id, css_id).Val_str(fld_path, path).Val_bry(fld_data, data).Exec_insert();
	}
	public void Delete(int css_id) {
		conn.Stmt_delete(tbl_name, fld_css_id).Crt_int(fld_css_id, css_id).Exec_delete();
	}
	public Xowd_css_file_itm[] Select_by_owner(int css_id) {
		Db_stmt stmt = conn.Stmt_select(tbl_name, flds, fld_css_id).Crt_int(fld_css_id, css_id);
		return Select_by_stmt(stmt);
	}
	public Xowd_css_file_itm[] Select_all() {	// TEST:
		Db_stmt stmt = conn.Stmt_select(tbl_name, flds);
		return Select_by_stmt(stmt);
	}
	private Xowd_css_file_itm[] Select_by_stmt(Db_stmt stmt) {
		List_adp rv = List_adp_.new_();
		Db_rdr rdr = stmt.Exec_select__rls_auto();
		try {
			while (rdr.Move_next())
				rv.Add(new_itm(rdr));
		} finally {rdr.Rls();}
		return (Xowd_css_file_itm[])rv.To_ary_and_clear(Xowd_css_file_itm.class);
	}
	public void Delete_all() {
		conn.Stmt_delete(tbl_name).Exec_delete();
	}
	private Xowd_css_file_itm new_itm(Db_rdr rdr) {return new Xowd_css_file_itm(rdr.Read_int(fld_css_id), rdr.Read_str(fld_path), rdr.Read_bry(fld_data));}
}
