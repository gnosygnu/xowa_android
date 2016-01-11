package gplx.dbs.sqls; import gplx.*; import gplx.dbs.*;
public class Sql_order_by_itm {
	public String Name() {return name;} private String name;
	public boolean Ascending() {return ascending;} private boolean ascending;
	public String XtoSql() {
		String ascString = ascending ? "" : " DESC";
		return name + ascString;
	}
	public static Sql_order_by_itm new_(String name, boolean ascending) {
		Sql_order_by_itm rv = new Sql_order_by_itm();
		rv.name = name; rv.ascending = ascending;
		return rv;
	}	Sql_order_by_itm() {}
}
