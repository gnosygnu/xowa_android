package gplx.xowa.addons.searchs.searchers.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
public class Srch2_wildcard_row {
	private final Ordered_hash words__list = Ordered_hash_.New_bry();
	public Srch2_wildcard_row(byte[] text) {
		this.Text = text;
		this.Words__idx = 0;
	}
	public final byte[]		Text;
	public int					Words__idx;
	public int					Count_estimate = Int_.Min_value;
	public int					Words__offset()			{return words__offset;}	private int words__offset;
	public boolean					Words__done()			{return words__done;}	private boolean words__done;		public void	Words__done_y_()		{words__done = true;}
	public int					Words__len()			{return words__list.Count();}
	@gplx.Internal protected Srch2_word_row		Words__get_by_or_null(byte[] key) {return (Srch2_word_row)words__list.Get_by(key);}
	public Srch2_word_row		Words__get_at_or_null(int i) {
		return i < words__list.Len() ? (Srch2_word_row)words__list.Get_at(i) : null;
	}
	public void					Words__add(Srch2_word_row word) {
		words__list.Add(word.Text, word);
		++words__offset;
	}
}
