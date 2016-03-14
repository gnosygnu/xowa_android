package gplx.xowa.addons.searchs.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
public class Srch_word_row {
	public Srch_word_row(int id, byte[] text, int link_count, int link_score_min, int link_score_max) {
		this.Id = id; this.Text = text; this.Link_count = link_count;
		this.Link_score_min = link_score_min; this.Link_score_max = link_score_max;
	}
	public final int Id;
	public final byte[] Text;
	public final int Link_count;
	public final int Link_score_min;
	public final int Link_score_max;

        public static final Srch_word_row Empty = new Srch_word_row(-1, Bry_.Empty, 0, 0, 0);
}
