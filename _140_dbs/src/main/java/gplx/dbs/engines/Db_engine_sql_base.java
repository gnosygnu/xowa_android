package gplx.dbs.engines; import gplx.*; import gplx.dbs.*;
import java.sql.*; //#<>System.Data~java.sql
import gplx.core.stores.*;
import gplx.dbs.engines.*; import gplx.dbs.metas.*; import gplx.dbs.qrys.*; import gplx.dbs.sqls.*; import gplx.dbs.sqls.wtrs.*;
public abstract class Db_engine_sql_base implements Db_engine {
	@gplx.Internal protected void Ctor(Db_conn_info conn_info) {this.conn_info = conn_info;}
	public abstract String		Tid();
	public Db_conn_info			Conn_info() {return conn_info;} protected Db_conn_info conn_info;
	public abstract Sql_qry_wtr	Sql_wtr();
	public abstract				Db_engine New_clone(Db_conn_info conn_info);
	public Db_rdr				New_rdr__rls_manual(Object rdr_obj, String sql)				{return New_rdr(null, rdr_obj, sql);}
	public Db_rdr				New_rdr__rls_auto(Db_stmt stmt, Object rdr_obj, String sql)	{return New_rdr(stmt, rdr_obj, sql);}
	@gplx.Virtual public 			Db_rdr New_rdr_clone() {return new Db_rdr__basic();}
	@gplx.Virtual public Db_stmt		New_stmt_prep(Db_qry qry) {return new Db_stmt_cmd(this, qry);}
	@gplx.Virtual public void			Txn_bgn(String name)	{Exec_as_obj(Db_qry_sql.xtn_("BEGIN TRANSACTION;"));}
	@gplx.Virtual public String		Txn_end()				{Exec_as_obj(Db_qry_sql.xtn_("COMMIT TRANSACTION;")); return "";}
	@gplx.Virtual public void			Txn_cxl()				{Exec_as_obj(Db_qry_sql.xtn_("ROLLBACK TRANSACTION;"));}
	@gplx.Virtual public void			Txn_sav() {
		String txn_name = this.Txn_end();
		this.Txn_bgn(txn_name);
	}
	public Object		Exec_as_obj(Db_qry qry) {
		if (qry.Tid() == Db_qry_.Tid_flush) return null;	// ignore flush (delete-db) statements
		String sql = this.Sql_wtr().To_sql_str(qry, false); // DBG: Tfds.Write(sql);
		return qry.Exec_is_rdr() ? (Object)this.Exec_as_rdr(sql) : this.Exec_as_int(sql);
	}
	protected int Exec_as_int(String sql) {
		try {
			Statement cmd = New_stmt_exec(sql);	//#<>IDbCommand~Statement
			return cmd.executeUpdate(sql);			//#<>ExecuteNonQuery()~executeUpdate(sql)
		}
		catch (Exception e) {throw Err_.new_exc(e, "db", "db.engine:exec failed", "url", conn_info.Db_api(), "sql", sql);}
	}
	private DataRdr Exec_as_rdr(String sql) {
		try {
			Statement cmd = New_stmt_exec(sql);	//#<>IDbCommand~Statement
			cmd.execute(sql);										//#<>;~cmd.execute(sql);
			ResultSet rdr = cmd.getResultSet();	//#<>IDataReader rdr = cmd.ExecuteReader()~ResultSet rdr = cmd.getResultSet()
			return New_rdr(rdr, sql);
		}
		catch (Exception e) {throw Err_.new_exc(e, "db", "db.engine:rdr failed", "url", conn_info.Db_api(), "sql", sql);}
	}
	public void Ddl_create_tbl(Dbmeta_tbl_itm tbl) {Exec_as_int(tbl.To_sql_create(this.Sql_wtr())); this.Meta_reload();}
	public void Ddl_create_idx(Gfo_usr_dlg usr_dlg, Dbmeta_idx_itm... ary) {
		int len = ary.length;
		for (int i = 0; i < len; ++i) {
			Dbmeta_idx_itm idx = ary[i];
			usr_dlg.Plog_many("", "", "creating db index (please wait); db=~{0} idx=~{1}", conn_info.Database(), idx.Name());
			Exec_as_int(idx.To_sql_create(Sql_wtr()));
		}
		this.Meta_reload();
	}
	public void Ddl_append_fld(String tbl, Dbmeta_fld_itm fld)		{
		Gfo_usr_dlg_.Instance.Plog_many("", "", "adding column to table: db=~{0} tbl=~{1} fld=~{2}", conn_info.Database(), tbl, fld.Name());
		try {
			Exec_as_int(this.Sql_wtr().Schema_wtr().Bld_alter_tbl_add(tbl, fld));
			Gfo_usr_dlg_.Instance.Plog_many("", "", "column added to table: db=~{0} tbl=~{1} fld=~{2}", conn_info.Database(), tbl, fld.Name());
		}
		catch (Exception e) {	// catch error if column already added to table
			Gfo_usr_dlg_.Instance.Warn_many("", "", "column not added to table: db=~{0} tbl=~{1} fld=~{2} err=~{3}", conn_info.Database(), tbl, fld.Name(), Err_.Message_gplx_full(e));
		}
		this.Meta_reload();
	}
	public void Ddl_delete_tbl(String tbl)							{Exec_as_int(this.Sql_wtr().Schema_wtr().Bld_drop_tbl(tbl)); this.Meta_reload();}
	@gplx.Virtual public void Env_db_attach(String alias, Io_url db_url)	{}
	@gplx.Virtual public void Env_db_attach(String alias, Db_conn db_url)	{}
	@gplx.Virtual public void	Env_db_detach(String alias)					{}
	@gplx.Virtual public void	Meta_reload()								{}
	@gplx.Virtual public boolean Meta_tbl_exists(String tbl)					{return false;}
	@gplx.Virtual public boolean	Meta_fld_exists(String tbl, String fld)		{return false;}
	public abstract Dbmeta_tbl_mgr Meta_tbl_load_all();
	@gplx.Virtual public DataRdr New_rdr(ResultSet rdr, String sql)		{return gplx.core.stores.Db_data_rdr_.new_(rdr, sql);}//#<>IDataReader~ResultSet
	private Db_rdr New_rdr(Db_stmt stmt, Object rdr, String sql) {
		Db_rdr__basic rv = (Db_rdr__basic)New_rdr_clone();	
		rv.Ctor(stmt, (ResultSet)rdr, sql);	//#<>IDataReader~ResultSet
		return rv;
	}
	@gplx.Internal protected abstract Connection Conn_new();	//#<>IDbConnection~Connection
	//#{lang
	protected Connection connection;
	public void Conn_open() {connection = Conn_new();}
	public void Conn_term() {
		if (connection == null) return;	// connection never opened; just exit
		try 	{connection.close();}
		catch 	(Exception e) {throw Err_.new_exc(e, "db", "Conn_term failed", "url", conn_info.Raw());}
		connection = null;
	}
	public Object New_stmt_prep_as_obj(String sql) {
		if (connection == null) connection = Conn_new();	// auto-open connection
		try 	{return connection.prepareStatement(sql);}
		catch 	(Exception e) {throw Err_.new_exc(e, "db", "New_stmt_prep failed", "sql", sql);}
	}
	private Statement New_stmt_exec(String sql) {
		if (connection == null) connection = Conn_new();	// auto-open connection
		try 	{return connection.createStatement();}
		catch 	(Exception e) {throw Err_.new_exc(e, "db", "New_stmt_exec failed", "sql", sql);}
	}
	protected Connection Conn_make_by_url(String url, String uid, String pwd) {
		try {return DriverManager.getConnection(url, uid, pwd);}
		catch (SQLException e) {throw Err_.new_exc(e, "db", "connection open failed", "info", Conn_info().Raw());}
	}
	//#}
}
