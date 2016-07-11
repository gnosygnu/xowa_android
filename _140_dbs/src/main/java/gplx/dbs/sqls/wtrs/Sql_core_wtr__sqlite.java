package gplx.dbs.sqls.wtrs; import gplx.*; import gplx.dbs.*; import gplx.dbs.sqls.*;
public class Sql_core_wtr__sqlite extends Sql_core_wtr {//#*inherit
	@Override protected Sql_val_wtr			Make__val_wtr()							{return new Sql_val_wtr_sqlite();}
	@Override protected Sql_select_wtr		Make__select_wtr(Sql_core_wtr qry_wtr)	{return new Sql_select_wtr_sqlite(qry_wtr);}
}
