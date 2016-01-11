package gplx.xowa.wikis.data.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.data.*;
import gplx.dbs.*;
public class Xowd_css_core_tbl implements Rls_able {
	private final String tbl_name = "css_core"; private final Dbmeta_fld_list flds = Dbmeta_fld_list.new_();
	private final String fld_id, fld_key, fld_updated_on;
	public Xowd_css_core_tbl(Db_conn conn) {
		this.conn = conn;
		this.fld_id				= flds.Add_int_pkey_autonum("css_id");
		this.fld_key			= flds.Add_str("css_key", 255);
		this.fld_updated_on		= flds.Add_str("css_updated_on", 20);
		conn.Rls_reg(this);
	}
	public Db_conn Conn() {return conn;} private final Db_conn conn;
	public String Tbl_name() {return tbl_name;}
	public void Create_tbl() {conn.Ddl_create_tbl(Dbmeta_tbl_itm.New(tbl_name, flds, Dbmeta_idx_itm.new_unique_by_tbl(tbl_name, "main", fld_key)));}
	public void Rls() {}
	public int Insert(String key, DateAdp updated_on) {
		Db_stmt stmt_insert = conn.Stmt_insert(tbl_name, flds);
		stmt_insert.Val_str(fld_key, key).Val_str(fld_updated_on, updated_on.XtoStr_fmt_yyyyMMdd_HHmmss()).Exec_insert();
		return Select_id_by_key(key);
	}
	public void Update(int id, String key, DateAdp updated_on) {
		Db_stmt stmt_update = conn.Stmt_update_exclude(tbl_name, flds, fld_id);
		stmt_update.Val_str(fld_key, key).Val_str(fld_updated_on, updated_on.XtoStr_fmt_yyyyMMdd_HHmmss()).Crt_int(fld_id, id).Exec_update();
	}
	public Xowd_css_core_itm Select_by_key(String key) {
		Db_rdr rdr = conn.Stmt_select(tbl_name, flds, fld_key).Crt_str(fld_key, key).Exec_select__rls_auto();
		try {return rdr.Move_next() ? new_itm(rdr) : null;}
		finally {rdr.Rls();}
	}
	public int Select_id_by_key(String key) {
		Db_rdr rdr = conn.Stmt_select(tbl_name, flds, fld_key).Crt_str(fld_key, key).Exec_select__rls_auto();
		try {return rdr.Move_next() ? rdr.Read_int(fld_id) : Id_null;}
		finally {rdr.Rls();}
	}
	public Xowd_css_core_itm[] Select_all() {	// TEST:
		Db_stmt stmt = conn.Stmt_select(tbl_name, flds);
		return Select_by_stmt(stmt);
	}
	private Xowd_css_core_itm[] Select_by_stmt(Db_stmt stmt) {
		List_adp rv = List_adp_.new_();
		Db_rdr rdr = stmt.Exec_select__rls_auto();
		try {
			while (rdr.Move_next())
				rv.Add(new_itm(rdr));
		} finally {rdr.Rls();}
		return (Xowd_css_core_itm[])rv.To_ary_and_clear(Xowd_css_core_itm.class);
	}
	public void Delete_all() {
		conn.Stmt_delete(tbl_name).Exec_delete();
	}
	private Xowd_css_core_itm new_itm(Db_rdr rdr) {
		return new Xowd_css_core_itm(rdr.Read_int(fld_id), rdr.Read_str(fld_key), rdr.Read_date_by_str(fld_updated_on));
	}
	public static final int Id_null = -1;
}
