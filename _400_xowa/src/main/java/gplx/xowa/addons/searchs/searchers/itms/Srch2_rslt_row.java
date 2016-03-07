package gplx.xowa.addons.searchs.searchers.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
public class Srch2_rslt_row {
	public Srch2_rslt_row(byte[] key, byte[] wiki_bry, int page_ns, byte[] page_ttl_wo_ns, int page_id, int page_len, int page_score, int rslt_stage) {
		this.Key = key;
		this.Wiki_bry = wiki_bry;
		this.Page_id = page_id;
		this.Page_ns = page_ns;
		this.Page_ttl_wo_ns = page_ttl_wo_ns;
		this.Page_len = page_len;
		this.Page_score = page_score;
		this.Rslt_stage = rslt_stage;
	}
	public final byte[]		Key;
	public final byte[]		Wiki_bry;
	public final int			Page_id;
	public final int			Page_ns;
	public final byte[]		Page_ttl_wo_ns;
	public final int			Page_len;
	public final int			Page_score;
	public final int			Rslt_stage;
}
