//namespace gplx.xowa.htmls.core.to_delete {
//	using gplx.core.primitives; using gplx.core.btries; using gplx.dbs; using gplx.core.ios;
//	using gplx.xowa.wikis.dbs; using gplx.xowa.wikis.pages; using gplx.xowa.htmls.core.makes.imgs; using gplx.xowa.wikis.pages.skins;
//	using gplx.xowa.wikis.data; using gplx.xowa.wikis.data.tbls; using gplx.xowa.guis;
//	public class Xohd_page_html_mgr__load {
//		private final    Bry_rdr_old rdr = new Bry_rdr_old(); private final    List_adp rows = List_adp_.New(), imgs = List_adp_.New();
//		public void Load_page(Xow_wiki wiki, Xoh_page hpg, Xowd_html_tbl tbl, int page_id, Xoa_ttl page_ttl) {
//			Xoa_app_.Usr_dlg().Plog_many("", "", "hdump.load.text: ttl=~{0}", page_ttl.Full_db_as_str());
//			tbl.Select_by_page(rows, page_id);
//			Parse_rows(wiki, hpg, page_id, Xoa_url.blank(), page_ttl, rows);
//		}
//		public void Parse_rows(Xow_wiki wiki, Xoh_page hpg, int page_id, Xoa_url page_url, Xoa_ttl page_ttl, List_adp rows) {	// TEST:
//			hpg.Init(wiki, page_id, page_url, page_ttl);
//			imgs.Clear();
//			int len = rows.Count();
//			for (int i = 0; i < len; ++i) {
//				Xowd_html_row row = (Xowd_html_row)rows.Get_at(i);
//				switch (row.Tid()) {
//					case Xowd_html_row.Tid__img:
//					case Xowd_html_row.Tid__redlink:
//															Parse_data(hpg, row); break;
//				}
//			}
//			rows.Clear();
//		}
//		private void Parse_data(Xoh_page hpg, Xowd_html_row row) {
//			rdr.Init(row.Data());
//			while (!rdr.Pos_is_eos()) {
//				int tid = rdr.Read_int_to_pipe();
//				switch (tid) {
//					case Xohd_img_tid.Tid_img		: Load_data_img(); break;				// 1|0|A.png|0|220|110|...
//					case Xohd_img_tid.Tid_gallery	: Load_data_gallery(hpg); break;		// 3|0|800
//					case Xohd_img_tid.Tid_redlink	: Load_data_redlink(hpg); break;		// 2|2|0|1
//				}
//			}
//			if (imgs.Count() > 0) hpg.Img_itms_((Xohd_img_itm__base[])imgs.To_ary_and_clear(typeof(Xohd_img_itm__base)));
//		}
//		public static Xohd_img_itm__base Load_img(Bry_rdr_old rdr) {
//			int tid = rdr.Read_int_to_pipe();
//			Xohd_img_itm__base img_itm = null;
//			switch (tid) {
//				case Xohd_img_itm__base.Tid_basic		: img_itm = new Xohd_img_itm__img(); break;
//				case Xohd_img_itm__base.Tid_gallery	: img_itm = new Xohd_img_itm__gallery_itm(); break;
//				default									: throw Err_.new_unhandled(tid);
//			}
//			img_itm.Data_parse(rdr);
//			// Xoa_app_.Usr_dlg().Log_many("", "", "itm: ~{0}", img_itm.Data_print());
//			rdr.Pos_add_one();
//			return img_itm;
//		}
//		private void Load_data_img() {
//			Xohd_img_itm__base img = Load_img(rdr);
//			if (img == null) return;
//			imgs.Add(img);
//		}
//		private void Load_data_redlink(Xoh_page hpg) {
//			Ordered_hash redlink_hash = hpg.Redlink_uids();
//			while (!rdr.Pos_is_eos()) {
//				Int_obj_ref redlink_uid = Int_obj_ref.New(rdr.Read_int_to_pipe());
//				redlink_hash.Add(redlink_uid, redlink_uid);
//			}
//		}
//		private void Load_data_gallery(Xoh_page hpg) {
//			int uid = rdr.Read_int_to_pipe();
//			int box_max = rdr.Read_int_to_pipe();
//			hpg.Gallery_itms().Add_if_dupe_use_nth(uid, new Xohd_img_itm__gallery_mgr(uid, box_max));	// TODO_OLD: temporarily added b/c last build did not add gallery uid correctly
//		}
//	}
//}
