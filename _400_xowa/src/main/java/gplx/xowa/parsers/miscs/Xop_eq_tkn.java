package gplx.xowa.parsers.miscs; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
public class Xop_eq_tkn extends Xop_tkn_itm_base {//20111222
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_eq;}
	public int Eq_len() {return eq_len;} private int eq_len = -1;
	public int Eq_ws_rhs_bgn() {return eq_ws_rhs_bgn;} public Xop_eq_tkn Eq_ws_rhs_bgn_(int v) {eq_ws_rhs_bgn = v; return this;} private int eq_ws_rhs_bgn = -1;
	public Xop_eq_tkn(int bgn, int end, int eq_len) {this.Tkn_ini_pos(false, bgn, end); this.eq_len = eq_len;}
}
