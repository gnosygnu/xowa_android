package gplx.xowa.addons.searchs.searchers.wkrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.dbs.*; import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.addons.searchs.dbs.*; import gplx.xowa.addons.searchs.searchers.crts.*; import gplx.xowa.addons.searchs.searchers.rslts.*; import gplx.dbs.percentiles.*;
import gplx.xowa.langs.cases.*; import gplx.xowa.addons.searchs.parsers.*;
public class Srch_link_wkr extends Percentile_select_base {
	private final Srch_rdr_mkr rdr_mkr = new Srch_rdr_mkr();
	private Srch_rslt_list rslts; private Srch_rslt_cbk rslt_cbk; private Srch_ctx ctx;
	private List_adp rdr_rslts = List_adp_.new_();
	private Xowd_page_tbl page_tbl; private Srch_link_tbl cur_link_tbl;
	private Srch_rslt_row cur_row; private final Xowd_page_itm tmp_page_itm = new Xowd_page_itm();
	public void Search(Srch_rslt_list rslts, Srch_rslt_cbk rslt_cbk, Srch_ctx ctx) {
		super.cxl = ctx.Cxl;
		super.rng = ctx.Score_rng;
		super.rng_log = new Percentile_rng_log(ctx.Addon.Db_mgr().Cfg().Link_score_max());

		rng_log.Init(ctx.Qry.Search_raw, ctx.Rslts_needed, ctx.Qry.Slab_idx);
		this.rslts = rslts; this.rslt_cbk = rslt_cbk; this.ctx = ctx;
		this.page_tbl = ctx.Tbl__page;
		int link_tbls_len = ctx.Tbl__link__ary.length;
		for (int i = 0; i < link_tbls_len; ++i) {
			if (i == 1 && ctx.Qry.Ns_mgr.Ns_main_only()) return;// TODO: search other dbs beside main_db
			this.cur_link_tbl = ctx.Tbl__link__ary[i];
			attach_mgr.Init(cur_link_tbl.conn, new Db_attach_itm("page_db", ctx.Db__core.Conn()), new Db_attach_itm("word_db", ctx.Tbl__word.conn));
			super.Select();
		}
	}	
	@Override protected Db_rdr Rdr__init(Db_attach_mgr attach_mgr) {
		return rdr_mkr.Rdr__make(ctx, attach_mgr, page_tbl, cur_link_tbl);
	}
	@Override protected boolean Row__read(Db_rdr rdr) {
		if (!rdr.Move_next()) return false;
		byte[] wiki_bry = ctx.Wiki_domain;
		int page_id = rdr.Read_int(page_tbl.Fld_page_id());
		byte[] key = Srch_rslt_row.Bld_key(wiki_bry, page_id);
		this.cur_row = ctx.Cache__page.Get_by(key);				// note that page could have been added from another word
		if (cur_row == null) {
			int page_len = rdr.Read_int(page_tbl.Fld_page_len());
			int page_score = page_tbl.Fld_page_score() == Dbmeta_fld_itm.Key_null ? page_len : rdr.Read_int(page_tbl.Fld_page_score());
			int page_ns_id = rdr.Read_int(page_tbl.Fld_page_ns());
			byte[] page_ttl_wo_ns = rdr.Read_bry_by_str(page_tbl.Fld_page_title());
			Xoa_ttl page_ttl = ctx.Wiki.Ttl_parse(page_ns_id, page_ttl_wo_ns);
			this.cur_row = new Srch_rslt_row(key, wiki_bry, page_ttl, page_ns_id, page_ttl_wo_ns, page_id, page_len, page_score, rdr.Read_int(page_tbl.Fld_redirect_id()), Srch_rslt_tid_.Tid__title__search);
			ctx.Cache__page.Add(cur_row);
		}
		return true;
	}
	@Override protected boolean Row__eval() {
		if (	!ctx.Qry.Ns_mgr.Has(cur_row.Page_ns)							// ignore: ns doesn't match
			||	!Srch_link_wkr_.Matches(ctx.Search_crt, ctx.Addon.Ttl_parser(), ctx.Case_mgr, cur_row.Page_ttl_wo_ns)		// ignore: ttl doesn't match ttl_matcher; EX: "A B"
			)
			return false;
		Srch_rslt_row_.Format_ttl(ctx, page_tbl, tmp_page_itm, cur_row);
		if (rslts.Has(cur_row.Key)											// ignore: page already added by another word; EX: "A B"; word is "B", but "A B" already added by "A"				
			) return false;
		if (!Srch_rslt_row_.Redirect_handle(rslts, cur_row)) return false;
		rslt_cbk.On_rslt_found(cur_row);
		rslts.Add(cur_row);														// true if rows_found == request_count
		rslts.Ids__add(cur_row.Page_id, cur_row);
		rdr_rslts.Add(cur_row);
		return true;
	}
	@Override protected boolean Select__finished() {return rslts.Len() >= ctx.Qry.Slab_end;}
	@Override protected void Rdr__done(boolean last_rdr) {
		rslt_cbk.On_rdr_done(last_rdr);
		if (last_rdr)
			gplx.core.consoles.Console_adp__sys.Instance.Write_str(rng_log.To_str_and_clear());
	}
	public static int Percentile_rng__calc_adj(int last_word_len) {
		switch (last_word_len) {
			case  1: return     0;
			case  2: return    10;
			case  3: return    20;
			case  4: return    30;
			case  5: return    40;
			case  6: return    50;
			case  7: return    60;
			case  8: return    70;
			default: return    80;
		}
	}
}
class Srch_link_wkr_ {
	public static boolean Matches(Srch_crt_node node, Srch_text_parser text_parser, Xol_case_mgr case_mgr, byte[] ttl) {
		byte[] ttl_lower = case_mgr.Case_build_lower(Xoa_ttl.Replace_unders(ttl));
		byte[][] ttl_words = text_parser.Parse_to_bry_ary(Bool_.Y, ttl);
		return Matches(node, ttl_lower, ttl_words);
	}
	private static boolean Matches(Srch_crt_node node, byte[] ttl_lower, byte[][] ttl_words) {
		int tid = node.tid;
		byte[] raw = node.raw;
		Srch_crt_node[] subs = node.subs;
		int subs_len = subs.length;
		switch (tid) {
			case Srch_crt_node_.Tid__word: {
				int len = ttl_words.length;
				for (int i = 0; i < len; ++i) {
					byte[] word = ttl_words[i];
					if (node.raw_pattern == null) {
						if (Bry_.Eq(word, raw)) return true;
					}
					else {
						if (node.raw_pattern.Match(word)) return true;
					}
				}
				return false;
			}
			case Srch_crt_node_.Tid__word_quote:		return Bry_find_.Find_fwd(ttl_lower, raw) != Bry_find_.Not_found;// note that raw does not have quotes; EX: "B*" -> B*
			case Srch_crt_node_.Tid__not:				return !Matches(subs[0], ttl_lower, ttl_words);
			case Srch_crt_node_.Tid__or: {
				for (int i = 0; i < subs_len; ++i) {
					Srch_crt_node sub = subs[i];
					if (Matches(sub, ttl_lower, ttl_words))
						return true;
				}
				return false;
			}
			case Srch_crt_node_.Tid__and:
				for (int i = 0; i < subs_len; ++i) {
					Srch_crt_node sub = subs[i];
					if (!Matches(sub, ttl_lower, ttl_words))
						return false;
				}
				return true;
			case Srch_crt_node_.Tid__invalid:			return false;
			default: throw Err_.new_unhandled(tid);
		}
	}
}
	