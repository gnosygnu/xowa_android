package gplx.xowa.addons.bldrs.mass_parses.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.mass_parses.*;
import gplx.dbs.*;
public class Xomp_mgr_db {
	public Xomp_mgr_db(Io_url url) {
		this.url = url;
		this.conn = Db_conn_bldr.Instance.Get_or_autocreate(true, url);
		this.page_tbl = new Xomp_page_tbl(conn);
	}
	public Io_url Url() {return url;}  private Io_url url;
	public Db_conn Conn() {return conn;} private Db_conn conn;
	public Xomp_page_tbl Page_tbl() {return page_tbl;} private Xomp_page_tbl page_tbl;
}
