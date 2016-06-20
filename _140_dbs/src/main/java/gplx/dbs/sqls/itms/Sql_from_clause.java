package gplx.dbs.sqls.itms; import gplx.*; import gplx.dbs.*; import gplx.dbs.sqls.*;
public class Sql_from_clause {
	public Sql_from_clause(Sql_tbl_itm base_tbl) {
		this.Base_tbl = base_tbl;
		Tbls.Add(base_tbl);
	}
	public final    List_adp Tbls = List_adp_.New();
	public final    Sql_tbl_itm Base_tbl;
}
