package gplx.xowa.parsers.miscs; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
public class Xop_under_tkn extends Xop_tkn_itm_base {
	public Xop_under_tkn(int bgn, int end, int under_tid) {this.under_tid = under_tid; this.Tkn_ini_pos(false, bgn, end);}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_under;}
	public int Under_tid() {return under_tid;} private int under_tid;
}
