package gplx.dbs.sqls; import gplx.*; import gplx.dbs.*;
import gplx.dbs.sqls.wtrs.*;
public class Sql_qry_wtr_ {
	public static Sql_qry_wtr New__basic()		{return new Sql_core_wtr();}
	public static Sql_qry_wtr New__mysql()		{return new Sql_core_wtr__mysql();}
	public static Sql_qry_wtr New__sqlite()		{return new Sql_core_wtr__sqlite();}

	public static final byte Like_wildcard = Byte_ascii.Percent;
	public static String Quote_arg(String s) {	// only for constructing DEBUG SQL strings
		return "'" + String_.Replace(s, "'", "''") + "'";
	}
}
