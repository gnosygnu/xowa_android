package gplx.dbs; import gplx.*;
public interface Db_qry {
	int			Tid();
	boolean		Exec_is_rdr();
	String		Base_table();
	String		Xto_sql();
}
