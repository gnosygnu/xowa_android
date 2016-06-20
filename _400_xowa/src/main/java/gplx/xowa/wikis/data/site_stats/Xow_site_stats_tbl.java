package gplx.xowa.wikis.data.site_stats; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.data.*;
import gplx.dbs.*; import gplx.xowa.wikis.data.site_stats.*;
public class Xow_site_stats_tbl {
	private final    String tbl_name = "site_stats";
	private final    String fld_row_id, fld_good_articles, fld_total_pages, fld_images;
	private final    Db_conn conn; private final    Dbmeta_fld_list flds = new Dbmeta_fld_list();
	public Xow_site_stats_tbl(Db_conn conn, boolean schema_is_1) {
		this.conn = conn;
		fld_row_id			= flds.Add_int_pkey("ss_row_id");
		fld_good_articles	= flds.Add_long("ss_good_articles");
		fld_total_pages		= flds.Add_long("ss_total_pages");
		fld_images			= flds.Add_int("ss_images");
	}
	public void Create_tbl() {
		conn.Meta_tbl_create(Dbmeta_tbl_itm.New(tbl_name, flds));
		conn.Stmt_insert(tbl_name, flds).Val_int(fld_row_id, Site_stats_row_id).Val_long(fld_good_articles, 0).Val_long(fld_total_pages, 0).Val_int(fld_images, 0).Exec_insert();
	}
	public void Update(int num_articles, int num_pages, int num_files) {
		Db_stmt stmt = conn.Stmt_update(tbl_name, String_.Ary(fld_row_id), fld_good_articles, fld_total_pages, fld_images);
		stmt.Val_long(fld_good_articles, num_articles).Val_long(fld_total_pages, num_pages).Val_int(fld_images, num_files)
			.Crt_int(fld_row_id, Site_stats_row_id)
			.Exec_update();
	}
	public void Select(Xow_site_stats_mgr stats) {
		Db_rdr rdr = conn.Stmt_select(tbl_name, flds, fld_row_id).Crt_int(fld_row_id, Site_stats_row_id).Exec_select__rls_auto();
		try {
			if (rdr.Move_next()) {
				stats.Load_by_db
				( rdr.Read_long(fld_good_articles)
				, rdr.Read_long(fld_total_pages)
				, rdr.Read_int(fld_images)
				, 0, 0, 0, 0, 0);
			}
		} finally {rdr.Rls();}
	}
	private static final int Site_stats_row_id = 1;
}
