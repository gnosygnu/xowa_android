package gplx.xowa.parsers.miscs; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
public class Xop_hr_tkn extends Xop_tkn_itm_base {//_20111226
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_hr;}
	public int Hr_len() {return hr_len;} public Xop_hr_tkn Hr_len_(int v) {hr_len = v; return this;} private int hr_len;
	public Xop_hr_tkn(int bgn, int end, int hr_len) {this.Tkn_ini_pos(false, bgn, end); this.hr_len = hr_len;}
}
