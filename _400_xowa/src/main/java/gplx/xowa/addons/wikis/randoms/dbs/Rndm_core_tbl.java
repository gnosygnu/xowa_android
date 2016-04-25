package gplx.xowa.addons.wikis.randoms.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.randoms.*;
import gplx.core.ios.*; import gplx.dbs.*; import gplx.dbs.utls.*;
public class Rndm_core_tbl implements Rls_able {
	private final    String tbl_name = "rndm_core"; private final    Dbmeta_fld_list flds = Dbmeta_fld_list.new_();
	private final    String fld_uid, fld_where_sql, fld_total, fld_interval;
	private final    Db_conn conn; private Db_stmt stmt_select;
	public Rndm_core_tbl(Db_conn conn) {
		this.conn = conn;
		fld_uid			= flds.Add_int_pkey("rndm_uid");
		fld_where_sql	= flds.Add_str("rndm_where_sql", 1024);
		fld_total		= flds.Add_int("rndm_total");
		fld_interval 	= flds.Add_int("rndm_interval");
		conn.Rls_reg(this);
	}
	public void Create_tbl() {conn.Meta_tbl_create(Dbmeta_tbl_itm.New(tbl_name, flds, Dbmeta_idx_itm.new_unique_by_name(tbl_name, "core", fld_uid)));}
	public void Upsert(int uid, String where_sql, int total, int interval) {
		conn.Stmt_delete(tbl_name, fld_uid).Crt_int(fld_uid, uid).Exec_delete();
		conn.Stmt_insert(tbl_name, flds).Val_int(fld_uid, uid).Val_str(fld_where_sql, where_sql).Val_int(fld_total, total).Val_int(fld_interval, interval).Exec_insert();
	}
	public void Select(Rndm_core_row rv, int uid) {
		if (stmt_select == null) stmt_select = conn.Stmt_select(tbl_name, flds, fld_uid);
		Db_rdr rdr = stmt_select.Clear().Val_int(fld_uid, uid).Exec_select__rls_manual();
		try {
			if (rdr.Move_next()) {
				String where_sql = rdr.Read_str(fld_where_sql);
				int total = rdr.Read_int(fld_total);
				int interval = rdr.Read_int(fld_interval);
				rv.Load(uid, where_sql, total, interval);
			}
		}	finally {rdr.Rls();}
	}
	public void Rls() {
		stmt_select = Db_stmt_.Rls(stmt_select);
	}
}
