package gplx.xowa.guis.views; import gplx.*; import gplx.xowa.*; import gplx.xowa.guis.*;
public class Gfo_usr_dlg_fmt {
	public boolean Write_prog_cur(int cur, Gfo_usr_dlg usr_dlg) {
		if (cur < prog_prv + prog_interval) return usr_dlg.Canceled();
		prog_prv = cur;
		String pct = Decimal_adp_.CalcPctStr(cur + List_adp_.Base1, end, "00.00");
		usr_dlg.Prog_many(grp_key, msg_key, fmt, Int_.To_str_pad_bgn_zero(cur + List_adp_.Base1, endLen), end, pct);
		return usr_dlg.Canceled();
	}	String fmt; int end, endLen;
	public static Gfo_usr_dlg_fmt fmt_(String grp_key, String msg_key, String fmt, int end, float pct) {
		Gfo_usr_dlg_fmt rv = new Gfo_usr_dlg_fmt();
		rv.grp_key = grp_key; rv.msg_key = msg_key;
		rv.fmt = fmt; rv.end = end; rv.endLen = Int_.DigitCount(end); rv.prog_interval = (int)((float)end * (float)(pct / (float)100));;
		return rv;
	}	String grp_key, msg_key;
	int prog_interval; int prog_prv = Int_.Min_value;
}
