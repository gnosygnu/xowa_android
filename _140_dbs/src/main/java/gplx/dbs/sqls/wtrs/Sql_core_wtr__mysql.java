package gplx.dbs.sqls.wtrs; import gplx.*; import gplx.dbs.*; import gplx.dbs.sqls.*;
public class Sql_core_wtr__mysql extends Sql_core_wtr {//#*inherit
	@Override protected Sql_val_wtr Make__val_wtr() {return new Sql_val_wtr_mysql();}
}
