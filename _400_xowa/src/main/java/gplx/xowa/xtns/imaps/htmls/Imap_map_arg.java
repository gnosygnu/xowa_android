package gplx.xowa.xtns.imaps.htmls; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.imaps.*;
import gplx.core.brys.fmtrs.*;
import gplx.xowa.xtns.imaps.itms.*;
public class Imap_map_arg implements gplx.core.brys.Bfr_arg {
	private final    int imap_id;
	private final    Imap_shapes_arg shapes_arg;
	public Imap_map_arg(int imap_id, Imap_part_shape[] shapes, double scale) {
		this.imap_id = imap_id;
		this.shapes_arg = new Imap_shapes_arg(shapes, scale);
	}
	public void Bfr_arg__add(Bry_bfr bfr) {
		Imap_html_fmtrs.Map.Bld_bfr_many(bfr, imap_id, shapes_arg);
	}
}
