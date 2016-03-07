package gplx.dbs.engines; import gplx.*; import gplx.dbs.*;
import gplx.core.stores.*; import gplx.dbs.metas.*; import gplx.dbs.sqls.*;
public interface Db_engine {
	String			Tid();
	Db_conn_info	Conn_info();
	Sql_qry_wtr		Sql_wtr();
	Db_engine		New_clone(Db_conn_info url);
	Db_rdr			New_rdr__rls_manual	(Object rdr_obj, String sql);				// Object o:ResultSet if desktop; Cursor if android
	Db_rdr			New_rdr__rls_auto	(Db_stmt stmt, Object rdr_obj, String sql);	// Object o:ResultSet if desktop; Cursor if android
	Db_stmt			New_stmt_prep(Db_qry qry);
	Object			New_stmt_prep_as_obj(String sql);
	DataRdr			New_rdr(java.sql.ResultSet rdr, String sql); //#<>System.Data.IDataReader~java.sql.ResultSet
	void			Txn_bgn(String name);
	String			Txn_end();
	void			Txn_cxl();
	void			Txn_sav();
	void			Conn_open();
	void			Conn_term();
	Object			Exec_as_obj(Db_qry qry);
	void			Ddl_create_tbl(Dbmeta_tbl_itm meta);
	void			Ddl_create_idx(Gfo_usr_dlg usr_dlg, Dbmeta_idx_itm... ary);
	void			Ddl_append_fld(String tbl, Dbmeta_fld_itm fld);
	void			Ddl_delete_tbl(String tbl);
	void			Env_db_attach(String alias, Db_conn conn);
	void			Env_db_attach(String alias, Io_url db_url);
	void			Env_db_detach(String alias);
	void			Meta_reload();
	boolean			Meta_tbl_exists(String tbl);
	boolean			Meta_fld_exists(String tbl, String fld);
	Dbmeta_tbl_mgr	Meta_tbl_load_all();
}
