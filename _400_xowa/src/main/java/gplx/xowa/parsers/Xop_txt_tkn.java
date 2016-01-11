package gplx.xowa.parsers; import gplx.*; import gplx.xowa.*;
public class Xop_txt_tkn extends Xop_tkn_itm_base {
	public Xop_txt_tkn(int bgn, int end) {this.Tkn_ini_pos(false, bgn, end);}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_txt;}
}
class Xop_colon_tkn extends Xop_tkn_itm_base {
	public Xop_colon_tkn(int bgn, int end) {this.Tkn_ini_pos(false, bgn, end);}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_colon;}
}
class Xop_brack_bgn_tkn extends Xop_tkn_itm_base {
	public Xop_brack_bgn_tkn(int bgn, int end) {this.Tkn_ini_pos(false, bgn, end);}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_brack_bgn;}
}
class Xop_brack_end_tkn extends Xop_tkn_itm_base {
	public Xop_brack_end_tkn(int bgn, int end) {this.Tkn_ini_pos(false, bgn, end);}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_brack_end;}
}
