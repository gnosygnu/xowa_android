package gplx.xowa.htmls.core.wkrs.lnkis.htmls; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*; import gplx.xowa.htmls.core.wkrs.lnkis.*;
import gplx.core.brys.fmtrs.*;
public class Xoh_arg_img_core__basic implements gplx.core.brys.Bfr_arg, Xoh_arg_img_core {
	private byte[] src; private int w, h;
	public Xoh_arg_img_core Init(int uid, byte[] src, int w, int h) {this.src = src; this.w = w; this.h = h; return this;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		fmtr_img_atrs.Bld_bfr_many(bfr, src, w, h);
	}
	private Bry_fmtr fmtr_img_atrs = Bry_fmtr.new_(" src=\"~{img_src}\" width=\"~{img_w}\" height=\"~{img_h}\"", "img_src", "img_w", "img_h");
}
