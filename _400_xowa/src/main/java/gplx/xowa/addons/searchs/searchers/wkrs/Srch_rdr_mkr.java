package gplx.xowa.addons.searchs.searchers.wkrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.dbs.*; import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.addons.searchs.searchers.crts.*; import gplx.xowa.addons.searchs.dbs.*; import gplx.xowa.addons.searchs.searchers.rslts.*;	
class Srch_rdr_mkr {
	private final Bry_bfr bfr = Bry_bfr.new_();
	public Db_rdr Rdr__make(Srch_ctx ctx, Db_attach_mgr attach_mgr, Xowd_page_tbl page, Srch_link_tbl link) {
		String sql = attach_mgr.Resolve_sql(Bld_sql(ctx));
		attach_mgr.Attach();
		return link.conn.Stmt_sql(sql).Exec_select__rls_auto();
	}
	private String Bld_sql(Srch_ctx ctx) {
		bfr.Add(Bry__page__bgn);
		Bld_where(bfr, ctx, Srch_word_count_calc.Find_main(ctx));
		bfr.Add(Bry__page__end);
		return bfr.To_str_and_clear();
	}
	private void Bld_where(Bry_bfr bfr, Srch_ctx ctx, Srch_crt_node node) {
		switch (node.tid) {
			case Srch_crt_node_.Tid__word:
				Bld_leaf(bfr, ctx, node);
				break;
			case Srch_crt_node_.Tid__word_quote:	
				Srch_quoted_phrase quoted_phrase = Srch_quoted_phrase.Get_or_make(ctx, node);
				if (	quoted_phrase == Srch_quoted_phrase.Not_found
					||	quoted_phrase.Words.length == 0) break;	// handle ""
				Srch_word_row smallest_word = quoted_phrase.Words[0];
				Bld_leaf(bfr, ctx, node, node.uid, Srch_crt_node_.Where__eq, smallest_word.Text);
				break;
			case Srch_crt_node_.Tid__or:	
			case Srch_crt_node_.Tid__and:
				Srch_crt_node[] subs = node.subs;					
				int subs_len = subs.length;
				for (int i = 0; i < subs_len; ++i) {
					Srch_crt_node sub = subs[i];
					if (i != 0)
						bfr.Add_str_a7(node.tid == Srch_crt_node_.Tid__and ? "INTERSECT\n" : "UNION\n");
					Bld_where(bfr, ctx, sub);
				}
				break;
			case Srch_crt_node_.Tid__not:			break;		// never check database for NOT node
			case Srch_crt_node_.Tid__invalid:		break;		// should not happen
			default:								throw Err_.new_unhandled_default(node.tid);
		}
	}
	private void Bld_leaf(Bry_bfr bfr, Srch_ctx ctx, Srch_crt_node node) {Bld_leaf(bfr, ctx, node, node.uid, node.raw_where_tid, node.raw);}
	private void Bld_leaf(Bry_bfr bfr, Srch_ctx ctx, Srch_crt_node node, int node_uid, int node_where_tid, byte[] node_raw) {
		int score_bgn = ctx.Score_rng.Score_bgn();
		int score_end = ctx.Score_rng.Score_end();
		bfr.Add(Bry__link__bgn);
		switch (node_where_tid) {
			case Srch_crt_node_.Where__eq:		// EX: "earth"
				Fmt__word_id.Bld_many(bfr, Int_.To_str(ctx.Tbl__word.Select_by_or_empty(node_raw).Id));
				break;
			case Srch_crt_node_.Where__rng:		// EX: "earth*"
				Fmt__word_text__rng .Bld_many(bfr, node_uid, node.raw_rng_bgn, node.raw_rng_end	, score_bgn, score_end);
				break;
			case Srch_crt_node_.Where__like:	// EX: "*earth"
				Fmt__word_text__like.Bld_many(bfr, node_uid, node_raw							, score_bgn, score_end);
				break;
		}
		Fmt__link.Bld_many(bfr, score_bgn, score_end);
	}
	private static final byte[]
	  Bry__page__bgn = Bry_.new_a7(String_.Concat_lines_nl_skip_last
	( "SELECT  p.page_id, p.page_name"+"space, p.page_title, p.page_len, p.page_score, p.page_redirect_id"
	, "FROM    <page_db>page p"
	, "WHERE   p.page_id IN"
	, "("
	, ""
	))
	, Bry__page__end = Bry_.new_a7(")\n")
	, Bry__link__bgn = Bry_.new_a7(String_.Concat_lines_nl_skip_last
	( "SELECT  l.page_id"
	, "FROM    search_link l INDEXED BY search_link__word_id__link_score"
	, "WHERE   "
	))
	;
	private static final String
	  Str__link__end = String_.Concat_lines_nl_skip_last
	( "AND     l.link_score >= ~{score_bgn}"
	, "AND     l.link_score <  ~{score_end}"
	, ""
	)
	, Str__word__text__bgn	= String_.Concat_lines_nl_skip_last
	( "l.word_id IN"
	, "("
	, "SELECT  w~{uid}.word_id"
	, "FROM    <word_db>search_word w~{uid} INDEXED BY search_word__word_text__link_score_max__link_score_min"
	, "WHERE   w~{uid}.word_text "
	)
	, Str__word__text__rng	= ">= '~{word_bgn}' AND w~{uid}.word_text < '~{word_end}'\n"
	, Str__word__text__like	= "LIKE '~{word_raw}'\n"
	, Str__word__text__end = String_.Concat_lines_nl_skip_last
	( "AND     w~{uid}.link_score_max >= ~{score_bgn}"
	, "AND     w~{uid}.link_score_min <  ~{score_end}"
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
