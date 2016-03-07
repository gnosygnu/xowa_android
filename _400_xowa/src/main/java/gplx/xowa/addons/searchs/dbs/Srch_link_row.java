package gplx.xowa.addons.searchs.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
public class Srch_link_row {
	public Srch_link_row(int word_id, int page_id, int page_ns, int page_rank) {
		this.Word_id = word_id;
		this.Page_id = page_id;
		this.Page_ns = page_ns;
		this.Page_rank = page_rank;
	}
	public final int Word_id;
	public final int Page_id;
	public final int Page_ns;
	public final int Page_rank;
}
