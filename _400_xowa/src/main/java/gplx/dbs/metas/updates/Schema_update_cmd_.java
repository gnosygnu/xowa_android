package gplx.dbs.metas.updates; import gplx.*; import gplx.dbs.*; import gplx.dbs.metas.*;
import gplx.dbs.engines.sqlite.*;
public class Schema_update_cmd_ {
	public static Schema_update_cmd Make_tbl_create(String tbl_name, String tbl_sql, Db_idx_itm... tbl_idxs) {return new Schema_update_cmd__tbl_create(tbl_name, tbl_sql, tbl_idxs);}
}
class Schema_update_cmd__tbl_create implements Schema_update_cmd {
	private final String tbl_sql; private final Db_idx_itm[] tbl_idxs;
	public Schema_update_cmd__tbl_create(String tbl_name, String tbl_sql, Db_idx_itm... tbl_idxs) {
		this.tbl_name = tbl_name; this.tbl_sql = tbl_sql; this.tbl_idxs = tbl_idxs;
	}
	public String Name() {return "schema.tbl.create." + tbl_name;} private final String tbl_name;
	public boolean Exec_is_done() {return exec_is_done;} private boolean exec_is_done;
	public void Exec(Schema_db_mgr db_mgr, Db_conn conn) {
		if (db_mgr.Tbl_mgr().Has(tbl_name)) return;
		Gfo_usr_dlg_.Instance.Log_many("", "", "schema.tbl.create: tbl=~{0}", tbl_name);
		Sqlite_engine_.Tbl_create(conn, tbl_name, tbl_sql);
		Sqlite_engine_.Idx_create(conn, tbl_idxs);
		exec_is_done = true;
	}
}
