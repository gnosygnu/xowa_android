package gplx.dbs.qrys; import gplx.*; import gplx.dbs.*;
import gplx.core.criterias.*; import gplx.dbs.sqls.*;
public class Db_qry_update implements Db_qry_arg_owner {
	public int			Tid() {return Db_qry_.Tid_update;}
	public boolean			Exec_is_rdr() {return false;}
	public String		Xto_sql() {return Sql_qry_wtr_.Instance.Xto_str(this, false);}
	public int			Exec_qry(Db_conn conn) {return conn.Exec_qry(this);}
	public String		Base_table() {return base_table;} private String base_table;
	public String[]		Cols_for_update() {return cols_for_update;} private String[] cols_for_update;
	public Criteria		Where() {return where;} public Db_qry_update Where_(Criteria crt) {where = crt; return this;} private Criteria where;
	public Db_qry_arg_owner From_(String tbl) {base_table = tbl; return this;}
	public KeyValHash Args() {return args;} private final KeyValHash args = KeyValHash.new_();
	public Db_qry_arg_owner Arg_(String k, Decimal_adp v)	{return Arg_obj_type_(k, v.Under(), Db_val_type.Tid_decimal);}
	public Db_qry_arg_owner Arg_(String k, DateAdp v)		{return Arg_obj_type_(k, v, Db_val_type.Tid_date);}
	public Db_qry_arg_owner Arg_byte_(String k, byte v)		{return Arg_obj_type_(k, v, Db_val_type.Tid_byte);}
	public Db_qry_arg_owner Arg_(String k, int v)			{return Arg_obj_type_(k, v, Db_val_type.Tid_int32);}
	public Db_qry_arg_owner Arg_(String k, long v)			{return Arg_obj_type_(k, v, Db_val_type.Tid_int64);}
	public Db_qry_arg_owner Arg_(String k, String v)		{return Arg_obj_type_(k, v, Db_val_type.Tid_varchar);}
	public Db_qry_arg_owner Arg_bry_(String k, byte[] v)	{return Arg_obj_type_(k, v, Db_val_type.Tid_bry);}
	public Db_qry_arg_owner Arg_(String k, byte[] v)		{return Arg_obj_type_(k, String_.new_u8(v), Db_val_type.Tid_varchar);}
	public Db_qry_arg_owner Arg_obj_(String k, Object v)	{return Arg_obj_type_(k, v, Db_val_type.Tid_null);}
	public Db_qry_arg_owner Arg_obj_type_(String key, Object val, byte val_tid) {
		if (key == Dbmeta_fld_itm.Key_null) return this;
		Db_arg arg = new Db_arg(key, val).Val_tid_(val_tid);
		args.Add(arg.Key(), arg);
		return this;
	}
	public Db_qry_arg_owner Key_arg_(String k, int v) {return Key_arg_obj_(k, v);}
	public Db_qry_arg_owner Key_arg_(String k, String v) {return Key_arg_obj_(k, v);}
	private Db_qry_arg_owner Key_arg_obj_(String k, Object v) {
		Criteria crt = Db_crt_.eq_(k, v);
		where = where == null ? crt : Criteria_.And(where, crt);
		return this;
	}
	public static Db_qry_update new_() {return new Db_qry_update();} Db_qry_update() {}
	public static Db_qry_update new_(String tbl, String[] where, String... update) {
		Db_qry_update rv = Db_qry_.update_(tbl, Db_crt_.eq_many_(where));
		rv.cols_for_update = update;
		int len = update.length;
		for (int i = 0; i < len; i++)
			rv.Arg_obj_(update[i], null);
		return rv;
	}
}
