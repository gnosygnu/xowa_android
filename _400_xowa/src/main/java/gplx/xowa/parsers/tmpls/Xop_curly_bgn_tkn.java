package gplx.xowa.parsers.tmpls; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.btries.*;
public class Xop_curly_bgn_tkn extends Xop_tkn_itm_base {
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_tmpl_curly_bgn;}
	public Xop_curly_bgn_tkn(int bgn, int end) {this.Tkn_ini_pos(false, bgn, end);}
}
