package gplx.xowa.apps.wms.apis.parses; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.wms.*; import gplx.xowa.apps.wms.apis.*;
import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.wkrs.*; import gplx.xowa.htmls.core.hzips.*;
class Wm_hdoc_bldr implements Xoh_hdoc_wkr {
	private Xoh_hzip_bfr bfr;
	private byte[] src;
	
	public void Init_by_page(Xoh_hzip_bfr bfr, Xoh_page hpg, Xoh_hdoc_ctx hctx, byte[] src, int src_bgn, int src_end) {
		this.On_new_page(bfr, hpg, hctx, src, src_bgn, src_end);
	}
	public void On_new_page(Xoh_hzip_bfr bfr, Xoh_page hpg, Xoh_hdoc_ctx hctx, byte[] src, int src_bgn, int src_end) {
		this.bfr = bfr;
		this.src = src;
	}
	public void On_txt		(int rng_bgn, int rng_end) {bfr.Add_mid(src, rng_bgn, rng_end);}
	public void Add_bfr(Bry_bfr v) {bfr.Add_bfr_and_clear(v);}
	public void Add_str(String v) {bfr.Add_str_u8(v);}
	public void Add_bry(byte[] v) {bfr.Add(v);}

	public void On_escape	(gplx.xowa.htmls.core.wkrs.escapes.Xoh_escape_data data) {}
	public void On_xnde		(gplx.xowa.htmls.core.wkrs.xndes.Xoh_xnde_parser parser) {}
	public void On_lnki		(gplx.xowa.htmls.core.wkrs.lnkis.Xoh_lnki_data parser) {}
	public void On_thm		(gplx.xowa.htmls.core.wkrs.thms.Xoh_thm_data parser) {}
	public void On_gly		(gplx.xowa.htmls.core.wkrs.glys.Xoh_gly_grp_data parser) {}
	public boolean Process_parse(Xoh_data_itm data) {
		return false;
	}
}
