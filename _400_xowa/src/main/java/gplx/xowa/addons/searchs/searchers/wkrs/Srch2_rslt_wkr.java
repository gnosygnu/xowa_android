package gplx.xowa.addons.searchs.searchers.wkrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.dbs.*; import gplx.core.primitives.*; import gplx.core.brys.fmtrs.*; import gplx.core.intls.*;
import gplx.xowa.addons.searchs.dbs.*; import gplx.xowa.addons.searchs.searchers.crts.*; import gplx.xowa.addons.searchs.searchers.itms.*;
import gplx.xowa.wikis.data.tbls.*;
class Srch2_rslt_wkr {
	private final Bry_bfr bfr = Bry_bfr.new_();
	public Db_rdr Rdr__make(Srch2_ctx ctx, Db_attach_mgr attach_mgr, Xowd_page_tbl page, Srch_link_tbl link) {
		String sql = Build_sql(ctx);
		sql = attach_mgr.Resolve_sql(sql);
		attach_mgr.Attach();
		return link.conn.Stmt_sql(sql).Exec_select__rls_auto();
	}
	private String Build_sql(Srch2_ctx ctx) {
		bfr.Add(Bry__page__bgn);
		Add_where(bfr, ctx, ctx.Qry.Crt);
		bfr.Add(Bry__page__end);
		return bfr.To_str_and_clear();
	}
	private void Add_where(Bry_bfr bfr, Srch2_ctx ctx, Srch2_crt_node node) {
		switch (node.tid) {
			case Srch2_crt_node.Tid_word:
				Add_where__leaf(bfr, ctx, node);
				break;
			case Srch2_crt_node.Tid_word_quote:	
				Srch2_quoted_phrase quoted_phrase = (Srch2_quoted_phrase)node.raw_as_quoted_phrase;
				if (quoted_phrase.Words.length == 0) return; // handle ""
				Srch2_word_row smallest_word = quoted_phrase.Words[0];
				Add_where__leaf(bfr, ctx, node, node.uid, Srch2_crt_node.Where_tid__eq, smallest_word.Text);
				break;
			case Srch2_crt_node.Tid_or:	
			case Srch2_crt_node.Tid_and:
				Add_where(bfr, ctx, node.lhs);
				bfr.Add_str_a7(node.tid == Srch2_crt_node.Tid_and ? "INTERSECT\n" : "UNION\n");
				Add_where(bfr, ctx, node.rhs);
				break;
			case Srch2_crt_node.Tid_not:			break;		// never check database for NOT node
			case Srch2_crt_node.Tid_invalid:		break;		// should not happen
			default:								throw Err_.new_unhandled_default(node.tid);
		}
	}
	private void Add_where__leaf(Bry_bfr bfr, Srch2_ctx ctx, Srch2_crt_node node) {Add_where__leaf(bfr, ctx, node, node.uid, node.raw_where_tid, node.raw);}
	private void Add_where__leaf(Bry_bfr bfr, Srch2_ctx ctx, Srch2_crt_node node, int node_uid, int node_where_tid, byte[] node_raw) {
		int score_bgn = ctx.Score_rng.Rng_bgn();
		int score_end = ctx.Score_rng.Rng_end();
		bfr.Add(Bry__link__bgn);
		switch (node_where_tid) {
			case Srch2_crt_node.Where_tid__eq:		// EX: "earth"
				Fmt__word_id.Bld_many(bfr, Int_.To_str(ctx.Word_cache.Get_id_or_load(ctx, node_raw)));
				break;
			case Srch2_crt_node.Where_tid__rng:		// EX: "earth*"
				Fmt__word_text__rng .Bld_many(bfr, node_uid, node.raw_rng_bgn, node.raw_rng_end	, score_bgn, score_end);
				break;
			case Srch2_crt_node.Where_tid__like:	// EX: "*earth"
				Fmt__word_text__like.Bld_many(bfr, node_uid, node_raw							, score_bgn, score_end);
				break;
		}
		Fmt__link.Bld_many(bfr, score_bgn, score_end);
	}
	private static final byte[]
	  Bry__page__bgn = Bry_.new_a7(String_.Concat_lines_nl_skip_last
	( "SELECT  p.page_id, p.page_name"+"space, p.page_title, p.page_len, p.page_score"
	, "FROM    <page_db>page p"
	, "WHERE   p.page_id IN"
	, "("
	, ""
	))
	, Bry__page__end = Bry_.new_a7(")\n")
	, Bry__link__bgn = Bry_.new_a7(String_.Concat_lines_nl_skip_last
	( "SELECT  l.page_id"
	, "FROM    search_link l INDEXED BY search_link__word_id__page_score"
	, "WHERE   "
	))
	;
	private static final String
	  Str__link__end = String_.Concat_lines_nl_skip_last
	( "AND     l.page_score >= ~{score_bgn}"
	, "AND     l.page_score <  ~{score_end}"
	, ""
	)
	, Str__word__text__bgn	= String_.Concat_lines_nl_skip_last
	( "l.word_id IN"
	, "("
	, "SELECT  w~{uid}.word_id"
	, "FROM    <word_db>search_word w~{uid} INDEXED BY search_word__word_score_max"
	, "WHERE   w~{uid}.word_text "
	)
	, Str__word__text__rng	= ">= '~{word_bgn}' AND w~{uid}.word_text < '~{word_end}'\n"
	, Str__word__text__like	= "LIKE '~{word_raw}'\n"
	, Str__word__text__end = String_.Concat_lines_nl_skip_last
	( "AND     w~{uid}.word_page_score_max >= ~{score_bgn}"	// 50 - 70; max > 50
	, "AND     w~{uid}.word_page_score_min <  ~{score_end}" // 50 - 70: min < 70
	, ")"
	, ""
	);
	private static final Bry_fmt 
	  Fmt__link				= Bry_fmt.Auto(Str__link__end)
	, Fmt__word_id			= Bry_fmt.Auto("l.word_id = ~{word_uid}\n")
	, Fmt__word_text__rng	= Bry_fmt.Auto(Str__word__text__bgn + Str__word__text__rng  + Str__word__text__end)
	, Fmt__word_text__like	= Bry_fmt.Auto(Str__word__text__bgn + Str__word__text__like + Str__word__text__end)
	;
}
