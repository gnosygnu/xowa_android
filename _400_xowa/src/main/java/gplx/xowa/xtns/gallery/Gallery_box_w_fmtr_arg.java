package gplx.xowa.xtns.gallery; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
class Gallery_box_w_fmtr_arg implements gplx.core.brys.Bfr_arg {
	private final    int width;
	public Gallery_box_w_fmtr_arg(int width) {this.width = width;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		bfr.Add(Style_bgn);
		bfr.Add_int_variable(width);
		bfr.Add(Style_end);
	}
	private static final    byte[] Style_bgn = Bry_.new_a7("style=\"width:"), Style_end = Bry_.new_a7("px;\"");
}
