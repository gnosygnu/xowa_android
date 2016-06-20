package gplx.dbs.sqls.itms; import gplx.*; import gplx.dbs.*; import gplx.dbs.sqls.*;
public class Sql_group_clause {
	public List_adp Flds() {return flds;} List_adp flds = List_adp_.New();

	public static Sql_group_clause new_(String... ary) {
		Sql_group_clause rv = new Sql_group_clause();
		for (String itm : ary)
			rv.flds.Add(itm);
		return rv;
	}	Sql_group_clause() {}
}