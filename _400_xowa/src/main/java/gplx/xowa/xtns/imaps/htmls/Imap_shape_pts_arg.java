package gplx.xowa.xtns.imaps.htmls; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.imaps.*;
import gplx.core.primitives.*;
public class Imap_shape_pts_arg implements gplx.core.brys.Bfr_arg {
	private final    double scale; private Double_obj_val[] pts;
	public Imap_shape_pts_arg(double scale) {
		this.scale = scale;
	}
	public void Pts_(Double_obj_val[] v) {this.pts = v;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		int pts_len = pts.length;
		for (int i = 0; i < pts_len; ++i) {
			Double_obj_val pt = pts[i];
			int pt_int = (int)pt.Val();
			pt_int = (int)Math_.Round(scale * pt_int, 0);
			if (i != 0) bfr.Add_byte_comma();
			bfr.Add_int_variable(pt_int);
		}
	}
}
