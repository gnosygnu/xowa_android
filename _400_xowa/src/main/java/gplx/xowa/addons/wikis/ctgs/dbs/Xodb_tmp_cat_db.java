package gplx.xowa.addons.wikis.ctgs.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.ctgs.*;
import gplx.dbs.*;
public class Xodb_tmp_cat_db {
	private final    Io_url url;
	public Xodb_tmp_cat_db(Xowe_wiki wiki) {
		this.url = wiki.Fsys_mgr().Root_dir().GenSubFil("xowa.temp.category.sqlite3");
		this.conn = Db_conn_bldr.Instance.Get_or_autocreate(true, url);
	}
	public Db_conn Conn() {return conn;} private final    Db_conn conn;
	public void Delete() {
		conn.Rls_conn();
		Io_mgr.Instance.DeleteFil(url);
	}
}
