package gplx.dbs.qrys; import gplx.*; import gplx.dbs.*;
public interface Db_qry_arg_owner extends Db_qry {
	Db_qry_arg_owner From_(String tbl);
	Db_qry_arg_owner Key_arg_(String k, int v);
	Db_qry_arg_owner Key_arg_(String k, String v);
	Db_qry_arg_owner Arg_(String k, int v);
	Db_qry_arg_owner Arg_(String k, long v);
	Db_qry_arg_owner Arg_(String k, String v);
	Db_qry_arg_owner Arg_(String k, byte[] v);
	Db_qry_arg_owner Arg_(String k, DateAdp v);
	Db_qry_arg_owner Arg_(String k, Decimal_adp v);
	Db_qry_arg_owner Arg_byte_(String k, byte v);
	Db_qry_arg_owner Arg_bry_(String k, byte[] v);
	Db_qry_arg_owner Arg_obj_(String key, Object val);
	Db_qry_arg_owner Arg_obj_type_(String key, Object val, byte val_tid);
}
