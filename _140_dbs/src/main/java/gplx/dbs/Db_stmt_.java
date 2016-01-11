package gplx.dbs; import gplx.*;
import gplx.dbs.qrys.*;
public class Db_stmt_ {
	public static final Db_stmt Null = new Db_stmt_sql();
	public static Db_stmt new_insert_(Db_conn conn, String tbl, String... flds) {
		Db_qry qry = Db_qry_insert.new_(tbl, flds);
		return conn.Stmt_new(qry);
	}
	public static Db_stmt new_update_(Db_conn conn, String tbl, String[] where, String... flds) {
		Db_qry qry = Db_qry_update.new_(tbl, where, flds);
		return conn.Stmt_new(qry);
	}
	public static Db_stmt new_delete_(Db_conn conn, String tbl, String... where) {
		Db_qry_delete qry = Db_qry_.delete_(tbl, Db_crt_.eq_many_(where));
		return conn.Stmt_new(qry);
	}
	public static Db_stmt new_select_(Db_conn conn, String tbl, String[] where, String... flds) {
		Db_qry__select_cmd qry = Db_qry_.select_cols_(tbl, Db_crt_.eq_many_(where), flds);
		return conn.Stmt_new(qry);
	}
	public static Db_stmt new_select_in_(Db_conn conn, String tbl, String in_fld, Object[] in_vals, String... flds) {
		Db_qry__select_cmd qry = Db_qry_.select_cols_(tbl, Db_crt_.in_(in_fld, in_vals), flds).OrderBy_asc_(in_fld);
		return conn.Stmt_new(qry);
	}
	public static Db_stmt new_select_all_(Db_conn conn, String tbl) {
		return conn.Stmt_new(Db_qry_.select_tbl_(tbl));
	}
	public static Db_stmt new_select_as_rdr(Db_conn conn, Db_qry__select_in_tbl qry) {
		return conn.Stmt_new(qry);
	}
	public static Db_stmt new_select_as_rdr(Db_conn conn, String sql) {
		return conn.Stmt_new(Db_qry_sql.rdr_(sql));
	}
	public static Err err_(Exception e, Db_stmt stmt, String proc) {
		throw Err_.new_exc(e, "db", "db stmt failed", "proc", proc);
	}
	public static Err err_(Exception e, String tbl, String proc) {
		throw Err_.new_exc(e, "core", "db call failed", "tbl", tbl, "proc", proc);
	}
	public static Db_stmt Rls(Db_stmt v) {if (v != null) v.Rls(); return null;}
}
