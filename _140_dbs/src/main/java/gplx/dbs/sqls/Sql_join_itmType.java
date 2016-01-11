package gplx.dbs.sqls; import gplx.*; import gplx.dbs.*;
public class Sql_join_itmType {
	public int Val() {return val;} int val;
	public String Name() {return name;} private String name;
	Sql_join_itmType(int v, String name) {this.val = v; this.name = name;}
	public static final Sql_join_itmType
	  From	= new Sql_join_itmType(0, "FROM")
	, Inner	= new Sql_join_itmType(1, "INNER JOIN")
	, Left	= new Sql_join_itmType(2, "LEFT JOIN")
	, Right	= new Sql_join_itmType(3, "RIGHT JOIN")
	, Outer	= new Sql_join_itmType(4, "OUTER JOIN")
	, Cross	= new Sql_join_itmType(5, "CROSS JOIN")
	;
}
