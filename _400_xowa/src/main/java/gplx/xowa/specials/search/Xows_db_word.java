package gplx.xowa.specials.search; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*;
public class Xows_db_word {
	public Xows_db_word(int id, byte[] text, int page_count) {this.id = id; this.text = text; this.page_count = page_count;}
	public int				Id()			{return id;}			private final int id;
	public byte[]			Text()			{return text;}			private final byte[] text;
	public int				Page_count()	{return page_count;}	private final int page_count;
	public int				Rslts_offset()	{return rslts_offset;}	private int rslts_offset;
	public boolean				Rslts_done()	{return rslts_done;}	private boolean rslts_done;
	public void Rslts_offset_add_1() {++rslts_offset;}
	public void Rslts_done_y_() {rslts_done = true;}
}
class Xows_db_word_sorter implements gplx.core.lists.ComparerAble {
	public int compare(Object lhsObj, Object rhsObj) {
		Xows_db_word lhs = (Xows_db_word)lhsObj;
		Xows_db_word rhs = (Xows_db_word)rhsObj;
		return -Int_.Compare(lhs.Page_count(), rhs.Page_count());
	}
        public static final Xows_db_word_sorter Page_count_dsc = new Xows_db_word_sorter(); Xows_db_word_sorter() {}
}
