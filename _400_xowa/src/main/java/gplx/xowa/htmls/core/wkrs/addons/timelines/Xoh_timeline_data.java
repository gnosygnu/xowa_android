package gplx.xowa.htmls.core.wkrs.addons.timelines; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*; import gplx.xowa.htmls.core.wkrs.addons.*;
import gplx.core.threads.poolables.*;
import gplx.langs.htmls.*; import gplx.langs.htmls.docs.*;
import gplx.xowa.htmls.core.hzips.*;
public class Xoh_timeline_data implements Xoh_data_itm {
	public int Tid()			{return Xoh_hzip_dict_.Tid__timeline;}
	public int Src_bgn()		{return src_bgn;} private int src_bgn;
	public int Src_end()		{return src_end;} private int src_end;
	public void Clear() {
		this.src_bgn = this.src_end = -1;
	}
	public boolean Init_by_parse(Xoh_hdoc_wkr hdoc_wkr, Xoh_hdoc_ctx hctx, Gfh_tag_rdr tag_rdr, byte[] src, Gfh_tag timeline_lhs, Gfh_tag unused) {
		this.Clear();
		this.src_bgn = timeline_lhs.Src_bgn(); 
		this.src_end = timeline_lhs.Src_end();
		return true;
	}
	public void Init_by_decode(int src_bgn, int src_end) {
		this.src_bgn = src_bgn;
		this.src_end = src_end;
	}
	public static final    byte[] Hook_bry = Bry_.new_a7("<pre class='xowa-timeline'>");

	public void				Pool__rls	() {pool_mgr.Rls_fast(pool_idx);} private Gfo_poolable_mgr pool_mgr; private int pool_idx;
	public Gfo_poolable_itm	Pool__make	(Gfo_poolable_mgr mgr, int idx, Object[] args) {Xoh_timeline_data rv = new Xoh_timeline_data(); rv.pool_mgr = mgr; rv.pool_idx = idx; return rv;}
}
