package gplx.xowa.addons.searchs.v1s; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.core.primitives.*; import gplx.core.brys.fmtrs.*;
import gplx.dbs.*; import gplx.dbs.qrys.*; import gplx.xowa.wikis.data.*; import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.wikis.nss.*; import gplx.xowa.langs.cases.*;
import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.crts.*; import gplx.xowa.addons.searchs.dbs.*;
import gplx.xowa.addons.searchs.searchers.*;
public class Srch_db_wkr {
	private Xol_case_mgr drd_case_mgr;
	public Srch_rslt_itm[] Search_by_drd(Cancelable cxl, Srch_rslt_lnr rslt_lnr, Xow_wiki wiki, byte[] search, int search_results_max) {
		Srch_ns_mgr ns_mgr = new Srch_ns_mgr(); ns_mgr.Add_main_if_empty();
		Srch_qry qry = new Srch_qry(search, 0, search_results_max, ns_mgr, Bool_.Y, new gplx.xowa.wikis.domains.Xow_domain_itm[] {wiki.Domain_itm()});
		Srch_rslt_reg cache = new Srch_rslt_reg(); cache.Init_by_db(cxl, search, Srch_search_addon.Get(wiki).Db_mgr().Tbl__word());
		if (drd_case_mgr == null) drd_case_mgr = Xol_case_mgr_.U8();
		Search(cxl, rslt_lnr, cache, wiki, drd_case_mgr, qry);
		int len = cache.Total_count();
		Srch_rslt_itm[] rv = new Srch_rslt_itm[len];
		for (int i = 0; i < len; ++i)
			rv[i] = cache.Get_at(i);
		return rv;
	}
	@gplx.Internal protected void Search(Cancelable cxl, Srch_rslt_lnr rslt_lnr, Srch_rslt_reg cache, Xow_wiki wiki, Xol_case_mgr case_mgr, Srch_qry qry) {
		// get search_words
		Srch_db_mgr search_db_mgr = Srch_search_addon.Get(wiki).Db_mgr();
		Db_conn word_conn = search_db_mgr.Tbl__word().conn;
		Xoa_app_.Usr_dlg().Prog_many("", "", "search started (please wait)");
		Srch2_crt_node ttl_matcher = cache.Ttl_matcher();
		if (ttl_matcher == null) {
			cache.Init_by_db(cxl, case_mgr.Case_build_lower(qry.search_raw), search_db_mgr.Tbl__word());	// lower-case search
			ttl_matcher = cache.Ttl_matcher();
		}
		// do some init
		int rslts_wanted = qry.itms_end - qry.itms_bgn;
		Xowd_db_file core_db = wiki.Data__core_mgr().Db__core();
		Xowd_page_tbl page_tbl = core_db.Tbl__page();
		int link_tbls_len = search_db_mgr.Tbl__link__len();
		for (int k = 0; k < link_tbls_len; ++k) {
			Srch_link_tbl link_tbl = search_db_mgr.Tbl__link__get_at(k);
			Srch1_word_row[] word_ary = cache.Words(); int word_ary_len = word_ary.length;
			// read pages for each word from db
			Db_attach_mgr attach_mgr = New_attach_mgr(word_conn, page_tbl);
			int total_found = 0;
			try {
				while (true) {
					boolean found_none = true;
					for (int i = 0; i < word_ary_len; ++i) {	// loop each word to get rslts_wanted
						if (cxl.Canceled()) {cxl.Cancel_ackd_(); return;}
						Srch1_word_row word = word_ary[i];
						if (word.Rslts_done()) continue;		// last db_search for word returned 0 results; don't search again;
						int offset = word.Rslts_offset();
						Db_stmt search_stmt = New_attach_sql(attach_mgr, page_tbl, link_tbl, word.id, offset);
						Xoa_app_.Usr_dlg().Prog_many("", "", "searching; wiki=~{0} total=~{1} offset=~{2} index=~{3} word=~{4}", wiki.Domain_str(), word_ary_len, offset, i, word.text);
						// String sql = search_fmt.Bld_many_to_str_auto_bfr(link_tbl.Tbl_name(), link_tbl.Fld_page_id(), link_tbl.Fld_word_id(), word.id, offset); // need to return enough results to fill qry.page_len as many results may be discarded below; DATE:2015-04-24
						int rslts_found = Search_pages(cxl, rslt_lnr, cache, wiki, case_mgr, qry, page_tbl, link_tbl, attach_mgr, search_stmt, ttl_matcher, word, rslts_wanted);
						total_found += rslts_found;
						if		(rslts_found == -1)		return;				// canceled
						else if (rslts_found > 0)		found_none = false;	// NOTE: do not reverse and do rslts_found == 0; want to check if any word returns results;
					}
					if (found_none)					{cache.Done_y_(); break;}
					if (total_found >= rslts_wanted) break;
				}
				cache.Itms_end_(qry.itms_end);
				cache.Sort();
			}
			catch (Exception e) {Gfo_usr_dlg_.Instance.Warn_many("", "", "error during search; wiki=~{0} total=~{1} err=~{2}", wiki.Domain_str(), word_ary_len, Err_.Message_gplx_log(e));}
		}
	}
	private int Search_pages(Cancelable cxl, Srch_rslt_lnr rslt_lnr, Srch_rslt_reg rslt_reg, Xow_wiki wiki, Xol_case_mgr case_mgr, Srch_qry qry
		, Xowd_page_tbl page_tbl, Srch_link_tbl link_tbl, Db_attach_mgr attach_mgr, Db_stmt search_stmt, Srch2_crt_node ttl_matcher, Srch1_word_row word, int rslts_wanted) {
		int rslts_found = 0;
		Xow_ns_mgr ns_mgr = wiki.Ns_mgr();
		Db_rdr rdr = search_stmt.Clear().Crt_int(link_tbl.fld_word_id, word.id).Exec_select__rls_auto();
		try {
			while (rdr.Move_next()) {
				if (cxl.Canceled()) {cxl.Cancel_ackd_(); return -1;}
				word.Rslts_offset_add_1();
				int page_ns = rdr.Read_int(page_tbl.Fld_page_ns());
				if (!qry.ns_mgr.Has(page_ns)) continue;							// ignore: ns doesn't match
				byte[] page_ttl = rdr.Read_bry_by_str(page_tbl.Fld_page_title());
				// Io_mgr.Instance.AppendFilStr("C:\\temp.txt", String_.new_u8(word.Text()) + "|" + Int_.To_str(page_ns) + "|" + String_.new_u8(page_ttl) + "\n");
				if (!ttl_matcher.Matches(null, case_mgr, page_ttl)) continue;			// ignore: ttl doesn't match ttl_matcher
				Xow_ns ns = ns_mgr.Ids_get_or_null(page_ns);
				byte[] page_ttl_w_ns = ns.Gen_ttl(page_ttl);
				if (rslt_reg.Has(page_ttl_w_ns)) continue;						// ignore: page already added by another word; EX: "A B"; word is "B", but "A B" already added by "A"
				int page_len = rdr.Read_int(page_tbl.Fld_page_len());
				int page_score = page_tbl.Fld_page_score_eval(rdr, page_len);
				Srch_rslt_itm row = new Srch_rslt_itm(wiki.Domain_bry(), wiki.Ttl_parse(page_ttl_w_ns), rdr.Read_int(page_tbl.Fld_page_id())
					, page_len
					, page_score
					);
				rslt_lnr.Notify_rslt_found(row);
				if (++rslts_found == rslts_wanted) break;						// stop: found enough results; DATE:2015-04-24
			}
		}
		finally {
			rdr.Rls();
			attach_mgr.Detach();
		}
		if (rslts_found == 0) word.Rslts_done_y_(); // read through entire rdr and nothing found; mark word done
		return rslts_found;
	}
	public Db_attach_mgr New_attach_mgr(Db_conn search_conn, Xowd_page_tbl page) {return new Db_attach_mgr(search_conn, new Db_attach_itm("page_db", page.conn));}
	public Db_stmt New_attach_sql(Db_attach_mgr attach_mgr, Xowd_page_tbl page, Srch_link_tbl link, int word_id, int offset) {
		Db_qry__select_cmd qry = Db_qry_.select_()
			.Cols_w_tbl_("p", page.Fld_page_id(), page.Fld_page_ns(), page.Fld_page_title(), page.Fld_page_len())
			.From_("page_db", page.Tbl_name(), "p")
			.Join_(link.tbl_name, "l", Db_qry_.New_join__join(link.fld_page_id, "p", page.Fld_page_id()))
			.Where_(Db_crt_.New_eq("l", link.fld_word_id, word_id))
			.Order_("p", page.Fld_page_len(), Bool_.N)
			.Offset_(offset);
		return attach_mgr.Make_stmt_and_attach(qry, qry.From());
	}
}
