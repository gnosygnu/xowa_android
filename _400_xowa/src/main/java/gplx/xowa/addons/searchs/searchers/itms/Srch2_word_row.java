package gplx.xowa.addons.searchs.searchers.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.xowa.wikis.data.tbls.*;
public class Srch2_word_row {
	private final List_adp links__list = List_adp_.new_();
	public Srch2_word_row(int id, byte[] text, int page_count, int score_max) {this.Id = id; this.Text = text; this.Page_count = page_count; this.Page_score_max = score_max;}
	public final int			Id;
	public final byte[]		Text;
	public final int			Page_count;
	public final int			Page_score_max;
//		public int					Page_rank_tier__bgn = 0;
//		public int					Page_rank_tier__end = 1;
	public int					Links__idx = -1;
	public int					Links__score()			{return links__score;}	private int links__score;		public void	Links__score(int v)		{links__score = v;}
	public int					Links__offset()			{return links__offset;}	private int links__offset;
	public boolean					Links__done()			{return links__done;}	private boolean links__done;		public void	Links__done_y_()		{links__done = true; links__offset = -1;}
	public int					Links__len()			{return links__list.Count();}
	public Srch2_rslt_row		Links__get_at_or_null(int i) {
		return i < links__list.Len() ? (Srch2_rslt_row)links__list.Get_at(i) : null;
	}
	public void					Links__add(Srch2_rslt_row page) {
		links__list.Add(page);
		++links__offset;
	}
//		public void Page_rank_tier__set(int bgn, int end) {
//			Page_rank_tier__bgn = bgn; Page_rank_tier__end = end;
//			links__offset = 0;
//		}

	public static final Srch2_word_row Not_found = new Srch2_word_row(-1, Bry_.Empty, 0, Int_.Max_value);
}
