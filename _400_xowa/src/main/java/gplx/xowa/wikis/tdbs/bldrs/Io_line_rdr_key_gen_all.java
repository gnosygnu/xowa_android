package gplx.xowa.wikis.tdbs.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.tdbs.*;
import gplx.core.ios.*;
public class Io_line_rdr_key_gen_all implements Io_line_rdr_key_gen {
	public void Gen(Io_line_rdr bfr) {
		bfr.Key_pos_bgn_(bfr.Itm_pos_bgn()).Key_pos_end_(bfr.Itm_pos_end());
	}
	public static final Io_line_rdr_key_gen_all Instance = new Io_line_rdr_key_gen_all(); Io_line_rdr_key_gen_all() {}
}
