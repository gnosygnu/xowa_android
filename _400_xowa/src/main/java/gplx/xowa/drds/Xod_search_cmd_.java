package gplx.xowa.drds; import gplx.*; import gplx.xowa.*;
import gplx.xowa.addons.searchs.v1s.*;
import gplx.xowa.wikis.data.tbls.*;
public class Xod_search_cmd_ {
	public static final Xod_search_cmd 
//		  New__page_eq		= Xod_search_cmd__page_eq.Instance
//		, New__page_like	= Xod_search_cmd__page_like.Instance
//		, New__word_eq		= Xod_search_cmd__word_tbl.Instance_eq
//		, New__word_like	= Xod_search_cmd__word_tbl.Instance_like
	  New__search2		= Xod_search_cmd__srch2.Instance
	;
}
//	class Xod_search_cmd__page_eq : Xod_search_cmd {
//		public void Search(Cancelable cancelable, Srch_rslt_lnr rslt_lnr, Xow_wiki wiki, String search) {
//			Xowd_page_itm page_itm = new Xowd_page_itm();
//			if (wiki.Data__core_mgr().Tbl__page().Select_by_ttl(page_itm, wiki.Ns_mgr().Ns_main(), Bry_.Ucase__1st(Bry_.new_u8(search)))) {
//				Srch_rslt_itm search_itm = new Srch_rslt_itm(wiki.Domain_bry(), wiki.Ttl_parse(page_itm.Ttl_page_db()), page_itm.Id(), page_itm.Text_len(), page_itm.Text_len());
//				rslt_lnr.Notify_rslt_found(search_itm);
//			}
//		}
//        public static final Xod_search_cmd__page_eq Instance = new Xod_search_cmd__page_eq(); Xod_search_cmd__page_eq() {}
//	}
//	class Xod_search_cmd__page_like : Xod_search_cmd {// NOTE: slow; takes at least 10+ seconds
//		public void Search(Cancelable cancelable, Srch_rslt_lnr rslt_lnr, Xow_wiki wiki, String search) {
//			List_adp tmp_list = List_adp_.new_();
//			wiki.Data__core_mgr().Tbl__page().Select_by_search(cancelable, tmp_list, Bry_.Ucase__1st(Bry_.new_u8(search + "*")), 50);
//			int len = tmp_list.Count();
//			for (int i = 0; i < len; ++i) {
//				Xowd_page_itm page_itm = (Xowd_page_itm)tmp_list.Get_at(i);
//				Srch_rslt_itm search_itm = new Srch_rslt_itm(wiki.Domain_bry(), wiki.Ttl_parse(page_itm.Ttl_page_db()), page_itm.Id(), page_itm.Text_len(), page_itm.Text_len());
//				rslt_lnr.Notify_rslt_found(search_itm);
//			}
//		}
//        public static final Xod_search_cmd__page_like Instance = new Xod_search_cmd__page_like(); Xod_search_cmd__page_like() {}
//	}
//	class Xod_search_cmd__word_tbl : Xod_search_cmd {
//		private final boolean wildcard;
//		private final int results_wanted;
//		private final Srch_db_wkr search_wkr = new Srch_db_wkr();
//		Xod_search_cmd__word_tbl(boolean wildcard, int results_wanted) {this.wildcard = wildcard; this.results_wanted = results_wanted;}
//		public void Search(Cancelable cancelable, Srch_rslt_lnr rslt_lnr, Xow_wiki wiki, String search) {
//			search_wkr.Search_by_drd(cancelable, rslt_lnr, wiki, Bry_.new_u8(Standardize_search(search, wildcard)), results_wanted);
//		}
//        public static final Xod_search_cmd__word_tbl Instance_eq = new Xod_search_cmd__word_tbl(Bool_.N, 10), Instance_like = new Xod_search_cmd__word_tbl(Bool_.Y, 50);
//		private static String Standardize_search(String search, boolean wildcard) {
//			String rv = "";
//			String[] words = String_.Split(search, " ");
//			int words_len = words.length;
//			for (int i = 0; i < words_len; ++i) {
//				String word = words[i];
//				if (String_.Len(word) < 3) continue;
//				if (String_.Len(rv) != 0) rv += " ";
//				rv += word;
//				if (wildcard) rv += "*";
//			}
//			return rv;
//		}
//	}
