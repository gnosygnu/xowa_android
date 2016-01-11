package gplx.xowa.htmls.core.wkrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*;
import gplx.langs.htmls.docs.*;
import gplx.xowa.wikis.ttls.*;
import gplx.xowa.htmls.core.hzips.*;
public interface Xoh_hdoc_wkr {
	void On_new_page(Xoh_hzip_bfr bfr, Xoh_page hpg, Xoh_hdoc_ctx hctx, byte[] src, int src_bgn, int src_end);
	void On_txt		(int rng_bgn, int rng_end);
	void On_escape	(gplx.xowa.htmls.core.wkrs.escapes.Xoh_escape_data data);
	void On_xnde	(gplx.xowa.htmls.core.wkrs.xndes.Xoh_xnde_parser parser);
	void On_lnki	(gplx.xowa.htmls.core.wkrs.lnkis.Xoh_lnki_data parser);
	void On_thm		(gplx.xowa.htmls.core.wkrs.thms.Xoh_thm_data parser);
	void On_gly		(gplx.xowa.htmls.core.wkrs.glys.Xoh_gly_grp_data parser);
	boolean Process_parse(Xoh_data_itm data);
}
