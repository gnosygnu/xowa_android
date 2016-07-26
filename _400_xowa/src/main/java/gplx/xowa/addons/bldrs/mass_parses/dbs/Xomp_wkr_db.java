package gplx.xowa.addons.bldrs.mass_parses.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.mass_parses.*;
import gplx.dbs.*;
import gplx.xowa.htmls.core.dbs.*;
public class Xomp_wkr_db {
	public Xomp_wkr_db(int idx, Io_url url) {
		this.idx = idx;
		this.url = url;
		this.conn = Db_conn_bldr.Instance.Get_or_autocreate(true, url);
		this.html_tbl = new Xowd_html_tbl(conn);
		conn.Meta_tbl_assert(html_tbl);
	}
	public int Idx() {return idx;} private final    int idx;
	public Io_url Url() {return url;}  private Io_url url;
	public Db_conn Conn() {return conn;} private Db_conn conn;
	public Xowd_html_tbl Html_tbl() {return html_tbl;} private final    Xowd_html_tbl html_tbl;
}
