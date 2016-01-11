package gplx.dbs.sqls; import gplx.*; import gplx.dbs.*;
public class Sql_group_by {
	public List_adp Flds() {return flds;} List_adp flds = List_adp_.new_();

	public static Sql_group_by new_(String... ary) {
		Sql_group_by rv = new Sql_group_by();
		for (String itm : ary)
			rv.flds.Add(itm);
		return rv;
	}	Sql_group_by() {}
}