package gplx.xowa.htmls.core.wkrs.imgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*;
import gplx.core.brys.*; import gplx.core.threads.poolables.*; import gplx.core.brys.fmtrs.*;
import gplx.xowa.htmls.core.hzips.*; import gplx.xowa.htmls.core.wkrs.imgs.atrs.*;
import gplx.xowa.files.*;
public class Xoh_img_bare_wtr implements Bfr_arg, Xoh_wtr_itm {
	private byte[] src;
	private Xoh_img_bare_data data_itm;
	private Xoh_hdoc_ctx hctx;
	public void Init_by_parse(Bry_bfr bfr, Xoh_page hpg, Xoh_hdoc_ctx hctx, byte[] src, Xoh_img_bare_data data) {
		this.src = src; this.hctx = hctx;
		this.data_itm = data;
	}
	public boolean Init_by_decode(Xoh_page hpg, Xoh_hdoc_ctx hctx, byte[] src, Xoh_data_itm data_itm) {
		this.src = src; this.hctx = hctx;
		this.data_itm = (Xoh_img_bare_data)data_itm;			
		return true;
	}
	public void Bfr_arg__add(Bry_bfr bfr) {
		switch (data_itm.Img_tid()) {
			case Xoh_img_bare_data.Img_tid__hiero:
				bfr.Add_mid(src, data_itm.Src_bgn(), data_itm.Dir_bgn());
				bfr.Add(hctx.Fsys__root()).Add(Xoh_img_bare_data.Url__hiero);
				bfr.Add_mid(src, data_itm.Dir_end(), data_itm.Src_end());
				break;
		}
	}

	public void				Pool__rls	() {pool_mgr.Rls_fast(pool_idx);} private Gfo_poolable_mgr pool_mgr; private int pool_idx;
	public Gfo_poolable_itm	Pool__make	(Gfo_poolable_mgr mgr, int idx, Object[] args) {Xoh_img_bare_wtr rv = new Xoh_img_bare_wtr(); rv.pool_mgr = mgr; rv.pool_idx = idx; return rv;}
}
