package gplx.xowa.htmls.core.hzips; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*;
import gplx.core.brys.*; import gplx.core.threads.poolables.*;
import gplx.xowa.htmls.core.wkrs.*;
public interface Xoh_hzip_wkr extends gplx.core.threads.poolables.Gfo_poolable_itm {
	int					Tid();
	String				Key();
	byte[]				Hook();
	Gfo_poolable_itm	Encode1(Xoh_hzip_bfr bfr, Xoh_hdoc_wkr hdoc_wkr, Xoh_hdoc_ctx hctx, Xoh_page hpg, boolean wkr_is_root, byte[] src, Object data_obj);
	void				Decode1(Bry_bfr		 bfr, Xoh_hdoc_wkr hdoc_wkr, Xoh_hdoc_ctx hctx, Xoh_page hpg, Bry_rdr rdr, byte[] src, int src_bgn, int src_end, Xoh_data_itm data_itm);
}
