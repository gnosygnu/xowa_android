package gplx.dbs; import gplx.*;
public class Db_conn_bldr_data {
	public Db_conn_bldr_data(Db_conn conn, boolean exists) {this.conn = conn; this.exists = exists;}
	public Db_conn Conn() {return conn;} private final Db_conn conn;
	public boolean Exists() {return exists;} private final boolean exists;
	public boolean Created() {return !exists;}
}
