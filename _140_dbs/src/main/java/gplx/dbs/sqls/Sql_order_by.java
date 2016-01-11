package gplx.dbs.sqls; import gplx.*; import gplx.dbs.*;
public class Sql_order_by {
	public List_adp Flds() {return flds;} List_adp flds = List_adp_.new_();

	public static Sql_order_by new_(Sql_order_by_itm... ary) {
		Sql_order_by rv = new Sql_order_by();
		for (Sql_order_by_itm itm : ary)
			rv.flds.Add(itm);
		return rv;
	}	Sql_order_by() {}
}
