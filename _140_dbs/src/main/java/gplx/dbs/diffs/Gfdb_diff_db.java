package gplx.dbs.diffs; import gplx.*; import gplx.dbs.*;
public class Gfdb_diff_db {
	public Gfdb_diff_db(Db_conn conn) {
		this.conn = conn;
	}
	public Db_conn Conn() {return conn;} private final Db_conn conn;
}
