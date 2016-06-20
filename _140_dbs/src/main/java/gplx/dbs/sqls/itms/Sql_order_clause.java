package gplx.dbs.sqls.itms; import gplx.*; import gplx.dbs.*; import gplx.dbs.sqls.*;
public class Sql_order_clause {
	private final    List_adp list = List_adp_.New();
	private Sql_order_fld[] ary;
	public void Flds__add(Sql_order_fld fld) {list.Add(fld);}
	public Sql_order_fld[] Flds() {
		if (ary == null) ary = (Sql_order_fld[])list.To_ary_and_clear(Sql_order_fld.class);
		return ary;
	}
}
