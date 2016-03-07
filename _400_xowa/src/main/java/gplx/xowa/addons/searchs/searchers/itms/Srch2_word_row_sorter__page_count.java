package gplx.xowa.addons.searchs.searchers.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
public class Srch2_word_row_sorter__page_count implements gplx.core.lists.ComparerAble {
	public int compare(Object lhsObj, Object rhsObj) {
		Srch2_word_row lhs = (Srch2_word_row)lhsObj;
		Srch2_word_row rhs = (Srch2_word_row)rhsObj;
		return -Int_.Compare(lhs.Page_count, rhs.Page_count);
	}
        public static final Srch2_word_row_sorter__page_count Desc = new Srch2_word_row_sorter__page_count(); Srch2_word_row_sorter__page_count() {}
}
