package gplx.xowa.htmls.core.wkrs.txts; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*;
import gplx.langs.htmls.docs.*;
public class Xoh_txt_parser implements Gfh_txt_wkr {
	private final Xoh_hdoc_wkr wkr;
	public Xoh_txt_parser(Xoh_hdoc_wkr wkr) {this.wkr = wkr;}
	public void Parse(int rng_bgn, int rng_end) {wkr.On_txt(rng_bgn, rng_end);}
}
