package gplx.xowa.addons.bldrs.mass_parses.inits; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.mass_parses.*;
import gplx.dbs.*;
import gplx.xowa.bldrs.*;
class Xomp_init_mgr {
	private final    Xow_wiki wiki;
	public Xomp_init_mgr(Xow_wiki wiki) {this.wiki = wiki;}
	public void Exec() {
		Xob_db_file make_db = Xob_db_file.New__file_make(wiki.Fsys_mgr().Root_dir());
		Db_conn conn = make_db.Conn();

		// make table
		conn.Meta_tbl_remake(Dbmeta_tbl_itm.New("mp_page", new Dbmeta_fld_itm[]
		{ Dbmeta_fld_itm.new_int("page_id").Primary_y_()
		, Dbmeta_fld_itm.new_bool("page_done")
		}
		, Dbmeta_idx_itm.new_normal_by_tbl("mp_page", "page_id__page_done", "page_id", "page_done")
		));

		// fill table
		Db_attach_mgr attach_mgr = new Db_attach_mgr(conn, new Db_attach_itm("page_db", wiki.Data__core_mgr().Db__core().Conn()));
		int[] ns_ary = new int[] {0, 4, 14};
		int len = ns_ary.length;
		String sql = String_.Concat_lines_nl_skip_last
		( "INSERT INTO mp_page (page_id, page_done)"
		, "SELECT page_id, 0"
		, "FROM   <page_db>page"
		, "WHERE  page_namespace = {0}"
		, "AND    page_is_redirect = 0"
		); 
		for (int i = 0; i < len; ++i) {
			int ns_id = ns_ary[i];
			attach_mgr.Exec_sql_w_msg("adding rows for mp_page: ns=" + ns_id, sql, ns_id);// ANSI.Y
		}
	}
}
