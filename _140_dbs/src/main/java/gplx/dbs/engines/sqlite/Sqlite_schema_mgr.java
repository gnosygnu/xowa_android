package gplx.dbs.engines.sqlite; import gplx.*; import gplx.dbs.*; import gplx.dbs.engines.*;
import gplx.dbs.qrys.*;
import gplx.dbs.metas.*; import gplx.dbs.metas.parsers.*;
public class Sqlite_schema_mgr {
	private final Db_engine engine; private boolean init = true;
	private final Dbmeta_idx_mgr idx_mgr = new Dbmeta_idx_mgr();
	private final Dbmeta_tbl_mgr tbl_mgr = new Dbmeta_tbl_mgr();
	public Sqlite_schema_mgr(Db_engine engine) {this.engine = engine;}
	public boolean Tbl_exists(String name) {
		if (init) Init(engine);
		return tbl_mgr.Has(name);
	}
	public boolean Fld_exists(String tbl, String fld) {
		if (init) Init(engine);
		Dbmeta_tbl_itm tbl_itm = tbl_mgr.Get_by(tbl);
		return tbl_itm == null ? false : tbl_itm.Flds().Has(fld);
	}
	public Dbmeta_tbl_mgr Tbl_load_all() {
		Init(engine);
		return tbl_mgr;
	}
	private void Init(Db_engine engine) {
		init = false;
		Gfo_usr_dlg_.Instance.Log_many("", "", "db.schema.load.bgn: conn=~{0}", engine.Conn_info().Db_api());
		tbl_mgr.Clear(); idx_mgr.Clear();
		Dbmeta_parser__tbl tbl_parser = new Dbmeta_parser__tbl();
		Dbmeta_parser__idx idx_parser = new Dbmeta_parser__idx();
		Db_qry__select_in_tbl qry = Db_qry__select_in_tbl.new_("sqlite_master", String_.Ary_empty, String_.Ary("type", "name", "sql"), Db_qry__select_in_tbl.Order_by_null);
		Db_rdr rdr = engine.New_stmt_prep(qry).Exec_select__rls_auto();	
		try {
			while (rdr.Move_next()) {
				String type_str = rdr.Read_str("type");
				String name = rdr.Read_str("name");
				String sql = rdr.Read_str("sql");
				int type_int = Dbmeta_itm_tid.Xto_int(type_str);
				switch (type_int) {
					case Dbmeta_itm_tid.Tid_table:
						if (String_.Has_at_bgn(name, "sqlite_")) continue;	// ignore b/c of non-orthodox syntax; EX: "CREATE TABLE sqlite_sequence(name, seq)"; also "CREATE TABLE sqlite_stat(tbl,idx,stat)";
						tbl_mgr.Add(tbl_parser.Parse(Bry_.new_u8(sql)));
						break;
					case Dbmeta_itm_tid.Tid_index:
						if (sql == null) continue; // ignore "autoindex"; EX: sqlite_autoindex_temp_page_len_avg_1
						idx_mgr.Add(idx_parser.Parse(Bry_.new_u8(sql)));
						break;
					default:
						Gfo_usr_dlg_.Instance.Log_many("", "", "db.schema.unknown type: conn=~{0} type=~{1} name=~{2} sql=~{3}", engine.Conn_info().Db_api(), type_str, name, sql);
						break;
				}
			}
		}	finally {rdr.Rls();}
		Gfo_usr_dlg_.Instance.Log_many("", "", "db.schema.load.end");
	}
}
