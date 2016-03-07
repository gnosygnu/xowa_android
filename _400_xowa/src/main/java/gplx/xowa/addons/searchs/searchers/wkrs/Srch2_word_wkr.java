package gplx.xowa.addons.searchs.searchers.wkrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.dbs.*;
import gplx.xowa.addons.searchs.dbs.*;
import gplx.xowa.addons.searchs.searchers.crts.*; import gplx.xowa.addons.searchs.searchers.itms.*;
public class Srch2_word_wkr {
	private final List_adp tmp_list = List_adp_.new_();
	private final Srch2_link_wkr link_wkr = new Srch2_link_wkr();
//		private final Srch2_rslt_wkr wkr = new Srch2_rslt_wkr();
	public void Search(Srch2_rslt_hash rslts, Srch2_rslt_cbk rslt_cbk, Srch2_ctx ctx, Srch2_crt_node node) {
		if (ctx.Cxl.Canceled()) {ctx.Cxl.Cancel_ackd_(); return;}
		link_wkr.Search1(rslts, rslt_cbk, ctx);
		rslts.Sort();
//			wkr.Search1(rslts, rslt_cbk, ctx, link_wkr, node);
//			Srch_word_tbl word_tbl = ctx.Tbl__word;
//			switch (node.tid) {
//				case Srch2_crt_node.Tid_word: {
//					link_wkr.Search(rslts, rslt_cbk, ctx);
//					// wkr.Search(rslts, rslt_cbk, ctx, link_wkr, node);
//					break;
//				}
//				case Srch2_crt_node.Tid_word_quote:
//					Srch2_quoted_phrase quoted_phrase = Srch2_quoted_phrase__get_or_make(ctx, node);
//					if (quoted_phrase != Srch2_quoted_phrase.Not_found) {
////						link_wkr.Search(rslts, rslt_cbk, ctx, link_rdr_func, quoted_phrase.Min_page_count__word());
//					}
//					break;
//				case Srch2_crt_node.Tid_or:	// search both branches and merge
//					int request_len = ctx.Qry.Request_len;
//					Srch2_rslt_hash lhs_rslts = new Srch2_rslt_hash(request_len); Search(lhs_rslts, rslt_cbk, ctx, node.lhs);
//					Srch2_rslt_hash rhs_rslts = new Srch2_rslt_hash(request_len); Search(rhs_rslts, rslt_cbk, ctx, node.rhs);
//					rslts.Merge(lhs_rslts, rhs_rslts);
//					break;
//				case Srch2_crt_node.Tid_and: {// do complicated search;
//					wkr.Search(rslts, rslt_cbk, ctx, link_wkr, node);
//					break;
//				}
//				case Srch2_crt_node.Tid_not:							// never add "NOT" to db_search
//				case Srch2_crt_node.Tid_invalid:		break;			// should not happen
//				default:								throw Err_.new_unhandled(node.tid);
//			}
	}
	private Srch2_quoted_phrase Srch2_quoted_phrase__get_or_make(Srch2_ctx ctx, Srch2_crt_node node) {
		Srch2_quoted_phrase rv = (Srch2_quoted_phrase)node.raw_as_quoted_phrase;
		if (rv == null) {
			rv = Srch2_quoted_phrase__make(ctx, node);
			node.raw_as_quoted_phrase = rv;
		}
		return rv;
	}
	private Srch2_quoted_phrase Srch2_quoted_phrase__make(Srch2_ctx ctx, Srch2_crt_node node) {
		byte[] quoted_phrase = node.raw;
		byte[][] words = Bry_split_.Split(quoted_phrase, Byte_ascii.Space, Bool_.Y); // TODO: splitting by space is simplistic; this should call Srch2_split_words
		int words_len = words.length;
		for (int i = 0; i < words_len; ++i) {
			byte[] word = words[i];
			Srch2_word_row word_row = ctx.Word_cache.Get_word_or_load(ctx, word); if (word_row == Srch2_word_row.Not_found) continue;
			tmp_list.Add(word_row);
		}
		if (tmp_list.Count() == 0) return Srch2_quoted_phrase.Not_found;	// no words in db; EX: "xyz cba"
		tmp_list.Sort_by(Srch2_word_row_sorter__page_count.Desc);
		return new Srch2_quoted_phrase((Srch2_word_row[])tmp_list.To_ary_and_clear(Srch2_word_row.class));
	}		
}
class Srch2_quoted_phrase {
	public Srch2_quoted_phrase(Srch2_word_row[] words) {
		this.Words = words;
	}
	public Srch2_word_row[] Words;
	public Srch2_word_row	Min_page_count__word()	{return Words[0];}
	public int				Min_page_count__value()	{return (Words.length == 0) ? 0 : Min_page_count__word().Page_count;}
	public static final Srch2_quoted_phrase Not_found = new Srch2_quoted_phrase(new Srch2_word_row[0]);
}
