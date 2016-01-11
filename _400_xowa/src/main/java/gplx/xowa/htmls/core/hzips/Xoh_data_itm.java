package gplx.xowa.htmls.core.hzips; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*;
import gplx.langs.htmls.*; import gplx.langs.htmls.docs.*;
import gplx.xowa.htmls.core.wkrs.*; import gplx.core.threads.poolables.*;
public interface Xoh_data_itm extends Gfo_poolable_itm {
	int Tid();
	int Src_bgn();
	int Src_end();
	void Clear();
	boolean Init_by_parse(Xoh_hdoc_wkr hdoc_wkr, Xoh_hdoc_ctx hctx, Gfh_tag_rdr tag_rdr, byte[] src, Gfh_tag cur, Gfh_tag nxt);
}
