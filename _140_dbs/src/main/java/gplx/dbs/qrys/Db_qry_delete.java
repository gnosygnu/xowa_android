package gplx.dbs.qrys; import gplx.*; import gplx.dbs.*;
import gplx.core.criterias.*; import gplx.dbs.sqls.*;
public class Db_qry_delete implements Db_qry {
	Db_qry_delete(String base_table, Criteria where) {this.base_table = base_table; this.where = where;}
	public int			Tid()					{return Db_qry_.Tid_delete;}
	public boolean			Exec_is_rdr()			{return false;}
	public String		Base_table()			{return base_table;} private final String base_table;
	public String		Xto_sql()				{return Sql_qry_wtr_.Instance.Xto_str(this, false);}
	public Criteria		Where()					{return where;} private final Criteria where;
	public int			Exec_qry(Db_conn conn)	{return conn.Exec_qry(this);}
	public static Db_qry_delete new_all_(String tbl)						{return new Db_qry_delete(tbl, Criteria_.All);}
	public static Db_qry_delete new_(String tbl, String... where)		{return new Db_qry_delete(tbl, Db_crt_.eq_many_(where));}
	public static Db_qry_delete new_(String tbl, Criteria where)			{return new Db_qry_delete(tbl, where);}
}
