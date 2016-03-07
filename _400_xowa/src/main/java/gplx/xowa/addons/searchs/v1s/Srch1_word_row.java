package gplx.xowa.addons.searchs.v1s; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
public class Srch1_word_row {
	public Srch1_word_row(int id, byte[] text, int page_count) {this.id = id; this.text = text; this.page_count = page_count;}
	public final int		id;
	public final byte[]	text;
	public final int		page_count;
	public int				Rslts_offset()	{return rslts_offset;}	private int rslts_offset;
	public boolean				Rslts_done()	{return rslts_done;}	private boolean rslts_done;
	public void Rslts_offset_add_1() {++rslts_offset;}
	public void Rslts_done_y_() {rslts_done = true;}
}
class Srch1_word_row_sorter implements gplx.core.lists.ComparerAble {
	public int compare(Object lhsObj, Object rhsObj) {
		Srch1_word_row lhs = (Srch1_word_row)lhsObj;
		Srch1_word_row rhs = (Srch1_word_row)rhsObj;
		return -Int_.Compare(lhs.page_count, rhs.page_count);
	}
        public static final Srch1_word_row_sorter Page_count_dsc = new Srch1_word_row_sorter(); Srch1_word_row_sorter() {}
}
