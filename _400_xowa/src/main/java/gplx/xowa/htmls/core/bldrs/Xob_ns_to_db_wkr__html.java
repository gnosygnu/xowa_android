package gplx.xowa.htmls.core.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*;
import gplx.dbs.*; import gplx.xowa.bldrs.*;
import gplx.xowa.wikis.data.*; import gplx.xowa.wikis.data.tbls.*; import gplx.xowa.htmls.core.dbs.*;
class Xob_ns_to_db_wkr__html implements Xob_ns_to_db_wkr {
	private final Xowd_db_file page_db;
	public Xob_ns_to_db_wkr__html(Xowd_db_file page_db) {this.page_db = page_db;}
	public byte Db_tid() {return Xowd_db_file_.Tid_html_data;}
	public void Tbl_init(Xowd_db_file db) {			
		Xoh_page_tbl tbl = db.Tbl__html();
		tbl.Create_tbl();
		tbl.Insert_bgn(); 
	}
	public void Tbl_term(Xowd_db_file db) {
		db.Tbl__text().Insert_end(); 
		Db_conn db_conn = db.Conn();
		Db_attach_cmd.new_(page_db.Conn(), "html_db", db.Url())
			.Add_fmt("hdump.update page.html_db_id", Sql_update_page_html_db_id, db.Id())
			.Exec();
		db_conn.Rls_conn();
	}
	private static final String Sql_update_page_html_db_id = String_.Concat_lines_nl_skip_last
	( "REPLACE INTO page (page_id, page_name"+"space, page_title, page_is_redirect, page_touched, page_len, page_random_int, page_text_db_id, page_html_db_id, page_redirect_id)"
	, "SELECT   p.page_id"
	, ",        p.page_name"+"space"
	, ",        p.page_title"
	, ",        p.page_is_redirect"
	, ",        p.page_touched"
	, ",        p.page_len"
	, ",        p.page_random_int"
	, ",        p.page_text_db_id"
	, ",        {0}"
	, ",        p.page_redirect_id"
	, "FROM     page p"
	, "         JOIN <attach_db>html h ON p.page_id = h.page_id"
	);
}
