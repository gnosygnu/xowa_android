package gplx.xowa.addons.searchs.v1s; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
public class Srch_rslt_itm {
	public final byte[]		key;
	public final byte[]		wiki_bry;
	public final Xoa_ttl		page_ttl;
	public final int			page_id;
	public final int			page_len;
	public final int			page_score;
	public Srch_rslt_itm(byte[] wiki_bry, Xoa_ttl page_ttl, int page_id, int page_len, int page_score) {
		this.wiki_bry = wiki_bry;
		this.page_ttl = page_ttl;
		this.page_id = page_id;
		this.page_len = page_len;
		this.key = Bry_.Add(wiki_bry, Byte_ascii.Pipe_bry, page_ttl.Full_db());
		this.page_score = page_score;
	}
}
class Srch_rslt_itm_sorter implements gplx.core.lists.ComparerAble {
	public int compare(Object lhsObj, Object rhsObj) {
		Srch_rslt_itm lhs = (Srch_rslt_itm)lhsObj;
		Srch_rslt_itm rhs = (Srch_rslt_itm)rhsObj;
		return -Int_.Compare(lhs.page_score, rhs.page_score);
	}
        public static final Srch_rslt_itm_sorter Page_len_dsc = new Srch_rslt_itm_sorter(); Srch_rslt_itm_sorter() {}
}
