package gplx.xowa.htmls.core.wkrs.tocs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*;
import gplx.core.brys.*; import gplx.core.primitives.*; import gplx.core.brys.fmtrs.*; import gplx.core.threads.poolables.*; import gplx.core.brys.args.*;
import gplx.langs.htmls.*; import gplx.xowa.htmls.core.wkrs.bfr_args.*;
import gplx.xowa.htmls.sections.*; import gplx.xowa.htmls.core.hzips.*;
public class Xoh_toc_wtr implements gplx.core.brys.Bfr_arg, Xoh_wtr_itm {
	private byte toc_mode;
	public void Init_by_parse(Bry_bfr bfr, Xoh_page hpg, Xoh_hdoc_ctx hctx, byte[] src, Xoh_toc_data data) {
		Init_by_decode(hpg, hctx, src, data);
		this.Bfr_arg__add(bfr);
	}
	public boolean Init_by_decode(Xoh_page hpg, Xoh_hdoc_ctx hctx, byte[] src, Xoh_data_itm data_itm) {
		Xoh_toc_data data = (Xoh_toc_data)data_itm;
		this.toc_mode = data.Toc_mode();
		return true;
	}
	public void Bfr_arg__add(Bry_bfr bfr) {
		Write_tag(bfr, toc_mode == Xoh_toc_data.Toc_mode__pgbnr);
	}
	public void				Pool__rls	() {pool_mgr.Rls_fast(pool_idx);} private Gfo_poolable_mgr pool_mgr; private int pool_idx;
	public Gfo_poolable_itm	Pool__make	(Gfo_poolable_mgr mgr, int idx, Object[] args) {Xoh_toc_wtr rv = new Xoh_toc_wtr(); rv.pool_mgr = mgr; rv.pool_idx = idx; return rv;}

	public static final    byte[] 
	  Atr__class__toc				= Bry_.new_a7("xo-toc")
	, Atr__data__toc__mode			= Bry_.new_a7("data-toc-mode")
	;
	public static void Write_tag(Bry_bfr bfr, boolean pgbnr_enabled) {
		bfr.Add(Gfh_tag_.Div_lhs_bgn);
		Gfh_atr_.Add(bfr, Gfh_atr_.Bry__class, Atr__class__toc);
		if (pgbnr_enabled)
			Gfh_atr_.Add(bfr, Atr__data__toc__mode, Xoh_toc_data.Toc_mode__pgbnr);
		Gfh_tag_.Lhs_end_nde(bfr);
		bfr.Add(Gfh_tag_.Div_rhs);
	}
}
