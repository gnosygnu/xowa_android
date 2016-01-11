package gplx.xowa.parsers.amps; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
public class Xop_amp_tkn_num extends Xop_tkn_itm_base {
	public Xop_amp_tkn_num(int bgn, int end, int val, byte[] str_as_bry) {
		this.val = val; this.str_as_bry = str_as_bry;
		this.Tkn_ini_pos(false, bgn, end);
	}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_html_ncr;}
	public int Val() {return val;} private int val;
	public byte[] Str_as_bry() {return str_as_bry;} private byte[] str_as_bry;
}
