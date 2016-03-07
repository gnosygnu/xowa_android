package gplx.dbs; import gplx.*;
public interface Db_conn_info {
	String Key();				// EX: "sqlite"
	String Raw();				// EX: "gplx_key=sqlite;data source=/db.sqlite3;version=3"
	String Db_api();			// EX: "data source=/db.sqlite3;version=3"
	String Database();			// EX: /db.sqlite3 -> "db" ; xowa -> "xowa"
	Db_conn_info New_self(String raw, Keyval_hash hash);
}
