//namespace gplx.xowa.files {
//	using gplx.xowa.files.caches;
//	public class Xoa_file_mgr {
//		private final List_adp list = List_adp_.new_();
//		public void Clear() {list.Clear();}
//		public boolean Check_cache(Xow_wiki wiki, byte[] page_url, Xof_fsdb_itm fsdb_itm) {
//			fsdb_itm.Init_at_cache(0, 0, null);
////			Xou_cache_mgr cache_mgr = new Xou_cache_mgr(null, null, null);
////			Xou_cache_itm cache_itm = cache_mgr.Get_or_null(fsdb_itm);
////			if (cache_itm == null) {
////				fsdb_itm.Init_at_cache(Bool_.N_byte, 0, 0, null);
////				return false;
////			}
////			else {
////				fsdb_itm.Init_at_cache(Bool_.Y_byte, cache_itm.Html_w(), cache_itm.Html_h(), cache_itm.File_url());
////				return true;
////			}
//			return true;
//		}
//	}
//}
