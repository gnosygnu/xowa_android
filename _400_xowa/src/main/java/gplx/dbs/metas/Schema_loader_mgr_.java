package gplx.dbs.metas; import gplx.*; import gplx.dbs.*;
import gplx.dbs.qrys.*;
public class Schema_loader_mgr_ {
	public static final Schema_loader_mgr Null = new Schema_loader_mgr__null();
	public static final Schema_loader_mgr Sqlite = new Schema_loader_mgr__sqlite();
}
class Schema_loader_mgr__null implements Schema_loader_mgr {
	public void Load(Schema_db_mgr db_mgr, Db_conn conn) {}
}
class Schema_loader_mgr__sqlite implements Schema_loader_mgr {
	public void Load(Schema_db_mgr db_mgr, Db_conn conn) {
		Gfo_usr_dlg_.Instance.Log_many("", "", "db.schema.load.bgn: conn=~{0}", conn.Conn_info().Db_api());
		Dbmeta_tbl_mgr tbl_mgr = db_mgr.Tbl_mgr();
		Db_qry__select_in_tbl qry = Db_qry__select_in_tbl.new_("sqlite_master", String_.Ary_empty, String_.Ary("type", "name", "sql"), Db_qry__select_in_tbl.Order_by_null);
		Db_rdr rdr = conn.Stmt_new(qry).Exec_select__rls_auto();
		try {
			while (rdr.Move_next()) {
				String type_str = rdr.Read_str("type");
				String name = rdr.Read_str("name");
				int type_int = Dbmeta_itm_tid.Xto_int(type_str);
				switch (type_int) {
					case Dbmeta_itm_tid.Tid_table:
						Dbmeta_tbl_itm tbl_itm = Dbmeta_tbl_itm.New(name);
						tbl_mgr.Add(tbl_itm);
						break;
					case Dbmeta_itm_tid.Tid_index:	break;	// noop for now
					default:						throw Err_.new_unhandled(type_str);
				}
			}
		}	finally {rdr.Rls();}
		Gfo_usr_dlg_.Instance.Log_many("", "", "db.schema.load.end");
	}
}
