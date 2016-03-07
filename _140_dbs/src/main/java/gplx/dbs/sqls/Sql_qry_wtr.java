package gplx.dbs.sqls; import gplx.*; import gplx.dbs.*;
import gplx.dbs.sqls.wtrs.*;
public interface Sql_qry_wtr {
	String To_sql_str(Db_qry qry, boolean mode_is_prep);
	Sql_schema_wtr Schema_wtr();
}
