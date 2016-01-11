package gplx.dbs.sqls; import gplx.*; import gplx.dbs.*;
public class Sql_qry_wtr_ {
	public static Sql_qry_wtr	new_ansi()				{return new Sql_qry_wtr_ansi();}
	public static Sql_qry_wtr	new_escape_backslash()	{return new Sql_qry_wtr_ansi_escape_backslash();}
	public static final Sql_qry_wtr Instance = new Sql_qry_wtr_ansi();
	public static String Gen_placeholder_parameters(Db_qry qry) {return Sql_qry_wtr_.Instance.Xto_str(qry, true);}	// replace arguments with ?; EX: UPDATE a SET b = ? WHERE c = ?;
}
