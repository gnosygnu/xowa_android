package gplx.dbs; import gplx.*;
public interface Db_conn_info {
	String Tid();
	String Database();
	String Xto_raw();
	String Xto_api();
	Db_conn_info New_self(String raw, GfoMsg m);
}
