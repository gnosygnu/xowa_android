package gplx.xowa.addons.searchs.searchers.wkrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.dbs.*; import gplx.dbs.qrys.*; import gplx.xowa.wikis.data.*; import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.wikis.nss.*; import gplx.xowa.langs.cases.*;	
import gplx.xowa.addons.searchs.dbs.*; import gplx.xowa.addons.searchs.searchers.crts.*; import gplx.xowa.addons.searchs.searchers.itms.*;
class Srch2_link_wkr {
	private final Db_attach_mgr attach_mgr = new Db_attach_mgr();
	private final Srch2_rslt_wkr rdr_mkr = new Srch2_rslt_wkr();
	public void Search1(Srch2_rslt_hash rslts, Srch2_rslt_cbk rslt_cbk, Srch2_ctx ctx) {
		int link_tbls_len = ctx.Tbl__link__ary.length;
		for (int i = 0; i < link_tbls_len; ++i) {
			if (i == 1 && ctx.Qry.Ns_mgr.Ns_main_only()) return;// TODO: search other dbs beside main_db
			Srch_link_tbl link_tbl = ctx.Tbl__link__ary[i];
			attach_mgr.Init(link_tbl.conn, new Db_attach_itm("page_db", ctx.Db__core.Conn()), new Db_attach_itm("word_db", ctx.Tbl__word.conn));
			Db_rdr rdr = null;									// note that rdr can be opened and kept open until proc ends
			try {
				while (true) {									// loop until enough rows found
					if (ctx.Cxl.Canceled()) {ctx.Cxl.Cancel_ackd_(); return;}				// EXIT: canceled
					if (rdr == null) {
						rdr = rdr_mkr.Rdr__make(ctx, attach_mgr, ctx.Tbl__page, link_tbl); 
						if (ctx.Cxl.Canceled()) {ctx.Cxl.Cancel_ackd_(); return;}			// EXIT: canceled
					}
					Srch2_rslt_row row = Load_row(ctx, rdr);
					if (ctx.Cxl.Canceled()) {ctx.Cxl.Cancel_ackd_(); return;}
					if (row == null) {							// no more rows in rdr
						rdr = Close_rdr(rdr, attach_mgr);
						rslt_cbk.On_rdr_done();
						if (	ctx.Score_rng.Update(rslts.Len())
							||	rslts.Len() >= rslts.Request_count
							) return;	// EXIT: next_rng called but score_bgn is already at 0
						else continue;
					}
					Eval_row(rslts, ctx, rslt_cbk, row);		// EXIT: row added and enough rows found
				}
			}	finally {rslt_cbk.On_rdr_done(); rdr = Close_rdr(rdr, attach_mgr);}
		}
	}
	private static Db_rdr Close_rdr(Db_rdr rdr, Db_attach_mgr attach_mgr) {
		if (rdr != null) rdr.Rls();
		attach_mgr.Detach();
		return null;
	}
	private static Srch2_rslt_row Load_row(Srch2_ctx ctx, Db_rdr rdr) {
		if (!rdr.Move_next()) return null;
		Xowd_page_tbl page_tbl = ctx.Tbl__page; byte[] wiki_bry = ctx.Wiki_domain;
		int page_id = rdr.Read_int(page_tbl.Fld_page_id());
		byte[] key = ctx.Bld_key(wiki_bry, page_id);
		Srch2_rslt_row rv = ctx.Page_cache.Get_or_null(key);				// note that page could have been added from other word
		if (rv == null) {
			int page_len = rdr.Read_int(page_tbl.Fld_page_len());
			int page_score = page_tbl.Fld_page_score() == Dbmeta_fld_itm.Key_null ? page_len : rdr.Read_int(page_tbl.Fld_page_score());
			rv = new Srch2_rslt_row(key, wiki_bry, rdr.Read_int(page_tbl.Fld_page_ns()), rdr.Read_bry_by_str(page_tbl.Fld_page_title()), page_id, page_len, page_score, Srch2_rslt_stage.Tid__title_words);
			ctx.Page_cache.Add(rv);
		}
		return rv;
	}
	private static void Eval_row(Srch2_rslt_hash rslts, Srch2_ctx ctx, Srch2_rslt_cbk rslt_cbk, Srch2_rslt_row row) {
		if (!ctx.Qry.Ns_mgr.Has(row.Page_ns)) return;							// ignore: ns doesn't match
		if (!ctx.Qry.Crt.Matches(ctx.Addon.Parsers__ttl_parser(), ctx.Case_mgr, row.Page_ttl_wo_ns)) return;		// ignore: ttl doesn't match ttl_matcher; EX: "A B"
		if (rslts.Has(row.Key)) return;											// ignore: page already added by another word; EX: "A B"; word is "B", but "A B" already added by "A"
		rslt_cbk.On_rslt_found(row);
		rslts.Add(row);															// true if rows_found == request_count
	}
}
