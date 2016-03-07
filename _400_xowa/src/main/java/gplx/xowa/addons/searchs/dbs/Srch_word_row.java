package gplx.xowa.addons.searchs.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
public class Srch_word_row {
	public Srch_word_row(int id, byte[] text, int page_count, int score_max) {
		this.Id = id; this.Text = text; this.Page_count = page_count; this.Score_max = score_max;
	}
	public final int Id;
	public final byte[] Text;
	public final int Page_count;
	public final int Score_max;

        public static final Srch_word_row Null = null;
}
