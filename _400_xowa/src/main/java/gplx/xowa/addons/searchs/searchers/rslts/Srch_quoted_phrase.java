package gplx.xowa.addons.searchs.searchers.rslts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.dbs.*;
import gplx.xowa.addons.searchs.dbs.*;
import gplx.xowa.addons.searchs.searchers.crts.*; import gplx.xowa.addons.searchs.searchers.rslts.*;
public class Srch_quoted_phrase {
	public Srch_quoted_phrase(Srch_word_row[] words) {
		this.Words = words;
	}
	public Srch_word_row[] Words;
	public Srch_word_row	Min_link_count__word()	{return Words[0];}
	public int				Min_link_count__value()	{return (Words.length == 0) ? 0 : Min_link_count__word().Link_count;}
	public static final Srch_quoted_phrase Not_found = new Srch_quoted_phrase(new Srch_word_row[0]);
	public static Srch_quoted_phrase Get_or_make(Srch_ctx ctx, Srch_crt_node node) {
		Srch_quoted_phrase rv = (Srch_quoted_phrase)node.raw_as_quoted_phrase;
		if (rv == null) {
			rv = Make(ctx, node);
			node.raw_as_quoted_phrase = rv;
		}
		return rv;
	}
	private static Srch_quoted_phrase Make(Srch_ctx ctx, Srch_crt_node node) {
		List_adp tmp_list = List_adp_.new_();
		byte[] quoted_phrase = node.raw;
		byte[][] words = Bry_split_.Split(quoted_phrase, Byte_ascii.Space, Bool_.Y); // TODO: splitting by space is simplistic; this should call Srch2_split_words
		int words_len = words.length;
		for (int i = 0; i < words_len; ++i) {
			byte[] word = words[i];
			Srch_word_row word_row = ctx.Tbl__word.Select_by_or_empty(word); if (word_row == Srch_word_row.Empty) continue;
			tmp_list.Add(word_row);
		}
		if (tmp_list.Count() == 0) return Srch_quoted_phrase.Not_found;	// no words in db; EX: "xyz cba"
		tmp_list.Sort_by(Srch_word_row_sorter__link_count.Desc);
		return new Srch_quoted_phrase((Srch_word_row[])tmp_list.To_ary_and_clear(Srch_word_row.class));
	}		
}
class Srch_word_row_sorter__link_count implements gplx.core.lists.ComparerAble {
	public int compare(Object lhsObj, Object rhsObj) {
		Srch_word_row lhs = (Srch_word_row)lhsObj;
		Srch_word_row rhs = (Srch_word_row)rhsObj;
		return -Int_.Compare(lhs.Link_count, rhs.Link_count);
	}
        public static final Srch_word_row_sorter__link_count Desc = new Srch_word_row_sorter__link_count(); Srch_word_row_sorter__link_count() {}
}
