package gplx.dbs.engines.mysql; import gplx.*; import gplx.dbs.*; import gplx.dbs.engines.*;
import gplx.core.stores.*; import gplx.dbs.engines.*; import gplx.dbs.sqls.*;
import java.sql.*; //#<>System.Data~java.sql
//#{import
//#}
public class Mysql_engine extends Db_engine_sql_base {//_20110501
	@Override public String Tid() {return Mysql_conn_info.Tid_const;}
	@Override public Sql_qry_wtr SqlWtr() {return Sql_qry_wtr_.new_escape_backslash();}
	@Override public Db_engine New_clone(Db_conn_info connectInfo) {
		Mysql_engine rv = new Mysql_engine();
		rv.Ctor(connectInfo);
		return rv;
	}
	@Override public DataRdr New_rdr(ResultSet rdr, String commandText) {return Mysql_rdr.new_(rdr, commandText);}//#<>IDataReader~ResultSet
	//#{lang
	@gplx.Internal @Override protected Connection Conn_new() {
		Mysql_conn_info conn_info_as_mysql = (Mysql_conn_info)conn_info; 
		return Conn_make_by_url("jdbc:mysql://localhost/" + conn_info_as_mysql.Database() + "?characterEncoding=UTF8", conn_info_as_mysql.Uid(), conn_info_as_mysql.Pwd());
	}
	//#}
	public static final Mysql_engine Instance = new Mysql_engine(); Mysql_engine() {}
}
class Mysql_rdr extends Db_data_rdr {//_20110501 //#*inherit
	//#{MySqlRdr
	//PATCH:MYSQL:byte actually returned as int by Jdbc ResultSet (or MYSQL impmentation); convert to byte 
	@Override public byte ReadByte(String key) {return ReadByteOr(key, Byte.MAX_VALUE);}
	@Override public byte ReadByteOr(String key, byte or) {
		Object val = Read(key);
		return val == null ? or : ((Integer)val).byteValue();
	}
//#}
	public static Mysql_rdr new_(ResultSet rdr, String commandText) {//#<>IDataReader~ResultSet
		Mysql_rdr rv = new Mysql_rdr();
		rv.ctor_db_data_rdr(rdr, commandText);
		return rv;
	}	Mysql_rdr() {}
}
