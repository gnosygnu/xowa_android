package gplx.dbs.sys; import gplx.*; import gplx.dbs.*;
public class Db_sys_mgr {
	private final    Db_conn conn;
	private final    Db_sys_tbl sys_tbl;
	private boolean assert_exists = true;
	public Db_sys_mgr(Db_conn conn) {
		this.conn = conn;
		this.sys_tbl = new Db_sys_tbl(conn);
	}
	public int Autonum_next(String tbl, String fld) {return Autonum_next(String_.Concat(tbl, ".", fld));}
	public int Autonum_next(String key) {
		if (assert_exists) Assert_exists();
		int rv = sys_tbl.Assert_int_or(key, 1);
		sys_tbl.Update_int(key, rv + 1);
		return rv;
	}
	private void Assert_exists() {
		assert_exists = false;
		if (!conn.Meta_tbl_exists(sys_tbl.Tbl_name())) sys_tbl.Create_tbl();
	}
}
