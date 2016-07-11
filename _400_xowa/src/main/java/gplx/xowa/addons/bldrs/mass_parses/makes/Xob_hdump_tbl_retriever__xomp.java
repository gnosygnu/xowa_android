package gplx.xowa.addons.bldrs.mass_parses.makes; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.mass_parses.*;
import gplx.dbs.*;
import gplx.xowa.wikis.nss.*; import gplx.xowa.htmls.core.bldrs.*; import gplx.xowa.htmls.core.dbs.*;
class Xob_hdump_tbl_retriever__xomp implements Xob_hdump_tbl_retriever {
	private final    Db_conn conn;
	private final    Xowd_html_tbl tbl;
	public Xob_hdump_tbl_retriever__xomp(Db_conn conn) {
		this.conn = conn;
		this.tbl = new Xowd_html_tbl(conn);
		conn.Meta_tbl_assert(tbl);
	}
	public Xowd_html_tbl Get_html_tbl(Xow_ns ns, int prv_row_len) {
		return tbl;
	}
	public void Commit() {conn.Txn_sav();}
	public void Rls_all() {conn.Txn_sav(); conn.Rls_conn();}
}
