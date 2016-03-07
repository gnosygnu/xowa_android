package gplx.xowa.wikis.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.dbs.*;
import gplx.xowa.wikis.data.*; import gplx.xowa.wikis.data.tbls.*;
class Xodb_page_rdr__sql implements Xodb_page_rdr {
	private final Xowd_db_mgr db_mgr;
	private final Xowd_page_tbl page_tbl; private final Db_rdr rdr;
	public Xodb_page_rdr__sql(Xowe_wiki wiki) {
		this.db_mgr = wiki.Data__core_mgr();
		this.page_tbl = db_mgr.Tbl__page();
		this.rdr = page_tbl.Select_all__id__ttl();
	}
	public boolean Move_next() {return rdr.Move_next();}
	public boolean Read(Xowd_page_itm page) {
		page_tbl.Read_page__all(page, rdr);
		Xowd_text_tbl text_tbl = db_mgr.Dbs__get_by_id(page.Text_db_id()).Tbl__text();
		page.Text_(text_tbl.Select(page.Id()));
		return true;
	}
	public void Rls() {
		rdr.Rls();
	}
}

