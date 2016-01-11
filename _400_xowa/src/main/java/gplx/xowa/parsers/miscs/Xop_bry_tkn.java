package gplx.xowa.parsers.miscs; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.xowa.parsers.tmpls.*;
public class Xop_bry_tkn extends Xop_tkn_itm_base {
	public Xop_bry_tkn(int bgn, int end, byte[] val) {this.val = val; this.Tkn_ini_pos(false, bgn, end);}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_bry;}
	public byte[] Val() {return val;} private byte[] val;
	@Override public boolean Tmpl_evaluate(Xop_ctx ctx, byte[] src, Xot_invk caller, Bry_bfr bfr) {
		bfr.Add(val);
		return true;
	}
}
