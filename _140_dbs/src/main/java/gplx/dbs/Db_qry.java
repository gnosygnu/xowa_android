package gplx.dbs; import gplx.*;
public interface Db_qry {
	int			Tid();
	boolean		Exec_is_rdr();
	String		Base_table();
	String		To_sql__exec(gplx.dbs.sqls.Sql_qry_wtr wtr);
}
class Db_qry__noop implements Db_qry {
	public int	Tid() {return Db_qry_.Tid_noop;}
	public boolean	Exec_is_rdr() {return false;}
	public String Base_table() {return "";}
	public String To_sql__exec(gplx.dbs.sqls.Sql_qry_wtr wtr) {return "";}
}
