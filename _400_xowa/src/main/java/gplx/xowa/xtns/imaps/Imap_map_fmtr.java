package gplx.xowa.xtns.imaps; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.core.brys.fmtrs.*;
class Imap_map_fmtr implements gplx.core.brys.Bfr_arg {
	private int imap_id;
	public void Init(int imap_id, Imap_itm_shape[] shapes, double scale) {this.imap_id = imap_id; shapes_fmtr_arg.Init(shapes, scale);}
	public Imap_pts_fmtr_arg Pts_fmtr() {return shapes_fmtr_arg.Pts_fmtr();}
	public void Bfr_arg__add(Bry_bfr bfr) {
		Imap_html_fmtrs.Map.Bld_bfr_many(bfr, imap_id, shapes_fmtr_arg);
	}
	private static final Imap_shapes_fmtr shapes_fmtr_arg = new Imap_shapes_fmtr();
}
class Imap_shapes_fmtr implements gplx.core.brys.Bfr_arg {
	private Imap_itm_shape[] shapes;
	public void Init(Imap_itm_shape[] shapes, double scale) {this.shapes = shapes; pts_fmtr_arg.Scale_(scale);}
	public Imap_pts_fmtr_arg Pts_fmtr() {return pts_fmtr_arg;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		int shapes_len = shapes.length;
		Bry_fmtr fmtr = Imap_html_fmtrs.Area;
		for (int i = 0; i < shapes_len; ++i) {
			Imap_itm_shape shape = shapes[i];
			Fmt_shape(bfr, fmtr, pts_fmtr_arg, shape);
		}
	}
	@gplx.Internal protected static void Fmt_shape(Bry_bfr bfr, Bry_fmtr fmtr, Imap_pts_fmtr_arg pts_fmtr, Imap_itm_shape shape) {
		pts_fmtr_arg.Pts_(shape.Shape_pts());
		fmtr.Bld_bfr_many(bfr
		, shape.Link_href()
		, Imap_itm_.Xto_key(shape.Itm_tid())
		, pts_fmtr_arg
		, shape.Link_text()
		);
	}
	private static final Imap_pts_fmtr_arg pts_fmtr_arg = new Imap_pts_fmtr_arg();
}
