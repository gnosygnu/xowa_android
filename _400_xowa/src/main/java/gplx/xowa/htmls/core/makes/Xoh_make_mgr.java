package gplx.xowa.htmls.core.makes; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*;
import gplx.xowa.htmls.core.wkrs.*;
public class Xoh_make_mgr {
	private final    Xoh_hzip_bfr bfr = Xoh_hzip_bfr.New_txt(255);
	private final    Xoh_hdoc_ctx hctx = new Xoh_hdoc_ctx();
	private final    Xoh_hdoc_parser make_parser = new Xoh_hdoc_parser(new Xoh_hdoc_wkr__make());
	public byte[] Parse(byte[] src, Xoh_page hpg, Xow_wiki wiki) {
		hctx.Init_by_page(wiki, hpg);
		hpg.Section_mgr().Add(0, 2, Bry_.Empty, Bry_.Empty).Content_bgn_(0);	// +1 to skip \n
		make_parser.Parse(bfr, hpg, hctx, src);
		hpg.Section_mgr().Set_content(hpg.Section_mgr().Len() - 1, src, src.length);
		return bfr.To_bry_and_clear();
	}
}
