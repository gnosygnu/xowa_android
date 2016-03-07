package gplx.dbs; import gplx.*;
public class Db_attach_itm {
	public Db_attach_itm(String key, Io_url url) {
		this.Key = key; this.Url = url;
	}
	public Db_attach_itm(String key, Db_conn conn) {
		this.Key = key; this.Url = gplx.dbs.engines.sqlite.Sqlite_conn_info.To_url(conn);
	}
	public final String Key;
	public final Io_url Url;
}
