package gplx.fsdb.meta; import gplx.*; import gplx.fsdb.*;
import gplx.dbs.*; import gplx.dbs.qrys.*;
public class Fsm_atr_tbl {
	private final String tbl_name; private final Dbmeta_fld_list flds = Dbmeta_fld_list.new_();
	private final String fld_uid, fld_url;
	private final Db_conn conn;
	public Fsm_atr_tbl(Db_conn conn, boolean schema_is_1) {
		this.conn = conn;
		String fld_prefix = "";
		if (schema_is_1)			{tbl_name = "fsdb_db_atr";}
		else						{tbl_name = "fsdb_dba"; fld_prefix = "dba_";}
		this.fld_uid				= flds.Add_int_pkey	(fld_prefix + "uid");
		this.fld_url				= flds.Add_str		(fld_prefix + "url", 255);
	}
	public void Create_tbl() {conn.Ddl_create_tbl(Dbmeta_tbl_itm.New(tbl_name, flds));}
	public Fsm_atr_fil Select_1st_or_fail(Fsm_mnt_itm mnt_itm, Fsdb_db_mgr core_mgr, int mnt_id, boolean schema_thm_page) {
		Db_rdr rdr = conn.Stmt_select(tbl_name, flds, Dbmeta_fld_itm.Str_ary_empty).Exec_select__rls_auto();
		boolean schema_is_1 = core_mgr.File__schema_is_1();
		try {
			if (rdr.Move_next()) {
				String url_rel = rdr.Read_str(fld_url);
				return new Fsm_atr_fil
				( mnt_itm
				, rdr.Read_int(fld_uid)
				, url_rel
				, core_mgr.File__atr_file__at(mnt_id).Conn()
				, schema_is_1
				, schema_thm_page
				);
			}
		}
		finally {rdr.Rls();}
		throw Err_.new_wo_type("missing atr db", "conn", conn.Conn_info().Xto_api());
	}
	public void Insert(int id, String url_rel) {
		conn.Stmt_insert(tbl_name, flds).Val_int(fld_uid, id).Val_str(fld_url, url_rel).Exec_insert();
	}
}
