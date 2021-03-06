package gplx.dbs.engines.postgres; import gplx.*; import gplx.dbs.*; import gplx.dbs.engines.*;
import gplx.core.stores.*; import gplx.dbs.engines.*; import gplx.dbs.sqls.*; import gplx.dbs.metas.*;
import java.sql.*; //#<>System.Data~java.sql
//#{import
//#}
public class Postgres_engine extends Db_engine_sql_base {
	@Override public String Tid() {return Postgres_conn_info.Tid_const;}
	@Override public Sql_qry_wtr	Sql_wtr() {return Sql_qry_wtr_.New__mysql();}
	@Override public Db_engine New_clone(Db_conn_info connectInfo) {
		Postgres_engine rv = new Postgres_engine();
		rv.Ctor(connectInfo);
		return rv;
	}
	@Override public DataRdr New_rdr(ResultSet rdr, String commandText) {return Db_data_rdr_.new_(rdr, commandText);}//#<>IDataReader~ResultSet
	@Override public Dbmeta_tbl_mgr Meta_mgr() {throw Err_.new_unimplemented();}
	//#{lang
	@gplx.Internal @Override protected Connection Conn_make() {
		Postgres_conn_info conn_info_as_postgres = (Postgres_conn_info)conn_info;
		return Conn_make_by_url("jdbc:" + conn_info_as_postgres.Key() + "://localhost/" + conn_info_as_postgres.Database(), conn_info_as_postgres.Uid(), conn_info_as_postgres.Pwd());
	}
	//#}
	public static final    Postgres_engine Instance = new Postgres_engine(); Postgres_engine() {}
}
