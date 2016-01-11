package gplx.dbs.sqls; import gplx.*; import gplx.dbs.*;
public class Sql_from {
	public List_adp Tbls() {return tbls;} List_adp tbls = List_adp_.new_();
	public Sql_tbl_src BaseTable() {return (Sql_tbl_src)tbls.Get_at(0);}
	public static Sql_from new_(Sql_tbl_src baseTable) {
		Sql_from rv = new Sql_from();
		rv.tbls.Add(baseTable);
		return rv;
	}	Sql_from() {}
}
