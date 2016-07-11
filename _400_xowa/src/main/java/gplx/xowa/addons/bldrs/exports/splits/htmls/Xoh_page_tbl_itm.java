package gplx.xowa.addons.bldrs.exports.splits.htmls; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*; import gplx.xowa.addons.bldrs.exports.splits.*;
import gplx.dbs.*; import gplx.xowa.htmls.core.dbs.*;
public class Xoh_page_tbl_itm {
	private final    boolean trg;
	public Xoh_page_tbl_itm(boolean trg, int db_id, Db_conn conn) {
		this.trg = trg;
		this.db_id = db_id;
		this.html_tbl = new Xowd_html_tbl(conn);
	}
	public int Db_id() {return db_id;} private final    int db_id;
	public Xowd_html_tbl Html_tbl() {return html_tbl;} private final    Xowd_html_tbl html_tbl;
	public void Rls() {
		html_tbl.Conn().Rls_conn();
		if (trg) html_tbl.Conn().Env_vacuum();
	}
}
