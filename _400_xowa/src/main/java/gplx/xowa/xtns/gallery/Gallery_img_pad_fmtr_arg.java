package gplx.xowa.xtns.gallery; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
class Gallery_img_pad_fmtr_arg implements gplx.core.brys.Bfr_arg {
	private final    int vpad;
	public Gallery_img_pad_fmtr_arg(int vpad) {this.vpad = vpad;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		bfr.Add(Style_bgn);
		bfr.Add_int_variable(vpad);
		bfr.Add(Style_end);
	}
	private static final    byte[] Style_bgn = Bry_.new_a7("style=\"margin:"), Style_end = Bry_.new_a7("px auto;\"");
}
