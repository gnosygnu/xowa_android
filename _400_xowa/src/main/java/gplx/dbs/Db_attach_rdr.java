package gplx.dbs; import gplx.*;
import gplx.dbs.engines.sqlite.*;
public class Db_attach_rdr {
	private final boolean diff_db;
	private final Db_conn conn; private final String attach_name; private final Io_url attach_url;
	public Db_attach_rdr(Db_conn conn, String attach_name, Io_url attach_url) {
		this.conn = conn; this.attach_name = attach_name; this.attach_url = attach_url;
		Sqlite_conn_info conn_info = (Sqlite_conn_info)conn.Conn_info();
		this.diff_db = !String_.Eq(conn_info.Url().Raw(), attach_url.Raw());
	}
	public void Attach() {
		try {
			if (diff_db) conn.Env_db_attach(attach_name, attach_url);
		}	catch (Exception e) {Err_.Noop(e); Gfo_usr_dlg_.Instance.Warn_many("", "", "db:failed to attach db; name=~{0} url=~{1}", attach_name, attach_url.Raw());}
	}
	public Db_rdr Exec_as_rdr(String sql) {
		sql = String_.Replace(sql, "<attach_db>", diff_db ? attach_name + "." : "");	// replace <attach> with either "attach_db." or "";
		return conn.Exec_sql_as_rdr_v2(sql);
	}
	public void Detach() {
		if (diff_db) conn.Env_db_detach(attach_name);
	}
}
