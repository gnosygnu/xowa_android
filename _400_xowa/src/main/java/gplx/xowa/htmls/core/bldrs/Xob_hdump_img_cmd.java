package gplx.xowa.htmls.core.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*;
import gplx.dbs.*; import gplx.xowa.wikis.data.*; import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.htmls.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*; import gplx.xowa.htmls.core.makes.imgs.*;
import gplx.xowa.files.*;
import gplx.xowa.guis.*;
class Xob_hdump_img_cmd extends Xob_itm_basic_base implements Xob_cmd {
	public Xob_hdump_img_cmd(Xob_bldr bldr, Xowe_wiki wiki) {this.Cmd_ctor(bldr, wiki);}
	public String Cmd_key() {return Key_const;} public static final String Key_const = "hdump.make.imgs";
	public void Cmd_init(Xob_bldr bldr) {}
	public void Cmd_bgn(Xob_bldr bldr) {}
	public void Cmd_run() {Exec_main();}
	public void Cmd_end() {}
	public void Cmd_term() {}
	private void Exec_main() {
		Bry_bfr bfr = Bry_bfr.reset_(Io_mgr.Len_mb);
		Db_conn conn = Xob_db_file.New__file_make(wiki.Fsys_mgr().Root_dir()).Conn();
		Db_rdr rdr = conn.Stmt_sql(Sql_select_clause).Exec_select__rls_auto();
//			html_tbl = new Xowd_html_tbl(conn, wiki.Db_mgr_as_sql().Core_data_mgr().Props().Zip_tid_text());
		int cur_page_id = -1;
		while (rdr.Move_next()) {
			int lnki_page_id		= rdr.Read_int("lnki_page_id");
			if (lnki_page_id != cur_page_id) {
				Save(cur_page_id, bfr.To_bry_and_clear());
				cur_page_id = lnki_page_id;
			}
//				int html_uid			= rdr.Read_int(1);
//				byte[] lnki_ttl			= rdr.Read_bry(2);
//				int html_w				= rdr.Read_int(3);
//				int html_h				= rdr.Read_int(4);
//				int file_repo_id		= rdr.Read_int(5);
//				int file_ext_id			= rdr.Read_int(6);
//				boolean file_is_orig		= rdr.Read_int(7) == 1;
//				double file_time		= rdr.Read_double(8);
//				int file_page			= rdr.Read_int(9);
//				Xohd_img_itm__base.Data_write_static(bfr, 0, lnki_ttl, Byte_.Zero, 0, 0, Xof_img_size.Upright_null, html_uid, html_w, html_h, file_repo_id, file_ext_id, file_is_orig, html_w, file_time, file_page);
		}
		Save(cur_page_id, bfr.To_bry_and_clear());;
	}
	private void Save(int page_id, byte[] data) {
		if (page_id == -1 || data.length == 0) return;
//			html_tbl.Insert(page_id, Xohd_img_tid.Tid_img, data);
	}
	private static final String Sql_select_clause = String_.Concat_lines_nl_skip_last
	( "SELECT  lt.lnki_page_id"
	, ",       lt.html_uid"
	, ",       lt.lnki_ttl"
	, ",       xr.file_w"
	, ",       xr.file_h"
	, ",       xr.orig_repo"
	, ",       xr.lnki_ext"
	, ",       xr.file_is_orig"
	, ",       xr.lnki_time"
	, ",       xr.lnki_page"
	, "FROM    xfer_regy xr"
	, "        JOIN lnki_temp lt ON xr.file_ttl = lt.lnki_ttl"
	// , "        LEFT JOIN xtn_gallery lt ON xr.file_ttl = lt.lnki_ttl"
	// , "        LEFT JOIN xtn_imap lt ON xr.file_ttl = lt.lnki_ttl"
	, "ORDER BY "
	, "        lt.lnki_page_id"
	, ",       lt.lnki_uid"
	);
}
interface Page_async_cmd {
	void Prep();
	void Exec();
}
//	class Page_async_cmd__img : Page_async_cmd {
//		private Xoh_page hpg;
//		private List_adp missing = List_adp_.new_();
//		public Page_async_cmd__img(Xoh_page hpg) {this.hpg = hpg;}
//		public void Prep() {
////			int len = hpg.Img_count();
////			Xohd_img_itm__base[] ary = hpg.Img_itms();
////			missing.Clear();
////			for (int i = 0; i < len; ++i) {
////				Xohd_img_itm__base itm = ary[i];
////				boolean exists = Io_mgr.Instance.ExistsFil(itm.Html_view_url());
////				if (!exists) missing.Add(itm);
////			}
//		}
//		public void Exec() {
//			int len = missing.Count();
//			for (int i = 0; i < len; ++i) {
////				Xohd_img_itm__base itm = (Xohd_img_itm__base)missing.Get_at(i);
////				byte[] bytes = null; //fsdb.Db_get()ttl, file_w,....):
////				Write file(bytes);
//			}
//		}
//	}
//	/*		
//	CREATE TABLE xtn_gallery
//	( src_page_id		integer		NOT NULL
//	, html_uid			integer		NOT NULL
//	, box_max			integer		NOT NULL
//	, box_w				integer		NOT NULL
//	, img_w				integer		NOT NULL
//	, img_pad			integer		NOT NULL
//	);	
//	*/
