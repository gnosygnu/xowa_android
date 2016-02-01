package gplx.dbs; import gplx.*;
import gplx.dbs.engines.*; import gplx.core.stores.*;
public interface Db_stmt extends Rls_able {
	Db_stmt Crt_bool_as_byte(String k, boolean v);
	Db_stmt Val_bool_as_byte(String k, boolean v);
	Db_stmt Val_bool_as_byte(boolean v);
	Db_stmt Crt_byte(String k, byte v);
	Db_stmt Val_byte(String k, byte v);
	Db_stmt Val_byte(byte v);
	Db_stmt Crt_int(String k, int v);
	Db_stmt Val_int(String k, int v);
	Db_stmt Val_int_by_bool(String k, boolean v);
	Db_stmt Val_int(int v);
	Db_stmt Crt_long(String k, long v);
	Db_stmt Val_long(String k, long v);
	Db_stmt Val_long(long v);
	Db_stmt Crt_float(String k, float v);
	Db_stmt Val_float(String k, float v);
	Db_stmt Val_float(float v);
	Db_stmt Crt_double(String k, double v);
	Db_stmt Val_double(String k, double v);
	Db_stmt Val_double(double v);
	Db_stmt Crt_decimal(String k, Decimal_adp v);
	Db_stmt Val_decimal(String k, Decimal_adp v);
	Db_stmt Val_decimal(Decimal_adp v);
	Db_stmt Crt_bry(String k, byte[] v);
	Db_stmt Val_bry(String k, byte[] v);
	Db_stmt Val_bry(byte[] v);
	Db_stmt Crt_str(String k, String v);
	Db_stmt Val_str(String k, String v);
	Db_stmt Val_str(String v);
	Db_stmt Crt_bry_as_str(String k, byte[] v);
	Db_stmt Val_bry_as_str(String k, byte[] v);
	Db_stmt Val_bry_as_str(byte[] v);
	Db_stmt Val_rdr_(gplx.core.ios.Io_stream_rdr rdr, long rdr_len);
	boolean Exec_insert();
	int Exec_update();
	int Exec_delete();
	DataRdr Exec_select();
	Db_rdr Exec_select__rls_auto();		// stmt is automatically released
	Db_rdr Exec_select__rls_manual();	// stmt must be released manually; for "batch" insert
	Object Exec_select_val();
	void Ctor_stmt(Db_engine engine, Db_qry qry);
	Db_stmt Clear();
	Db_stmt Reset_stmt();
}