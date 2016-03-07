package gplx.dbs.qrys; import gplx.*; import gplx.dbs.*;
public interface Db_arg_owner extends Db_qry {
	Db_arg_owner From_(String tbl);
	Db_arg_owner Crt_int(String k, int v);
	Db_arg_owner Crt_str(String k, String v);
	Db_arg_owner Val_byte(String k, byte v);
	Db_arg_owner Val_int(String k, int v);
	Db_arg_owner Val_long(String k, long v);
	Db_arg_owner Val_decimal(String k, Decimal_adp v);
	Db_arg_owner Val_str(String k, String v);
	Db_arg_owner Val_date(String k, DateAdp v);
	Db_arg_owner Val_blob(String k, byte[] v);
	Db_arg_owner Val_str_by_bry(String k, byte[] v);
	Db_arg_owner Val_obj(String key, Object val);
	Db_arg_owner Val_obj_type(String key, Object val, byte val_tid);
}
