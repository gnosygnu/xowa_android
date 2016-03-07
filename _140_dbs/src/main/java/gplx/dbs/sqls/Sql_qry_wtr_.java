package gplx.dbs.sqls; import gplx.*; import gplx.dbs.*;
import gplx.dbs.sqls.wtrs.*;
public class Sql_qry_wtr_ {
	public static final Sql_qry_wtr
	  Basic			= new Sql_core_wtr()
	, Mysql			= new Sql_core_wtr__mysql()
	, Sqlite		= new Sql_core_wtr__sqlite()
	;
	public static String Gen_placeholder_parameters(Db_qry qry) {return Sql_qry_wtr_.Sqlite.To_sql_str(qry, true);}	// replace arguments with ?; EX: UPDATE a SET b = ? WHERE c = ?;
}
