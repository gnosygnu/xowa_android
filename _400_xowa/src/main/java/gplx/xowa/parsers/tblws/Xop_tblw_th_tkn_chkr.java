package gplx.xowa.parsers.tblws; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.tests.*;
public class Xop_tblw_th_tkn_chkr extends Xop_tkn_chkr_base {
	@Override public Class<?> TypeOf() {return Xop_tblw_th_tkn.class;}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_tblw_th;}
	public Xop_tblw_th_tkn_chkr Atrs_rng_(int bgn, int end) {this.atrs_bgn = bgn; this.atrs_end = end; return this;} private int atrs_bgn = Xop_tblw_wkr.Atrs_null, atrs_end = Xop_tblw_wkr.Atrs_null;
	@Override public int Chk_hook(Tst_mgr mgr, String path, Object actl_obj, int err) {
		Xop_tblw_th_tkn actl = (Xop_tblw_th_tkn)actl_obj;
		err += mgr.Tst_val(atrs_bgn == -1, path, "atrs_bgn", atrs_bgn, actl.Atrs_bgn());
		err += mgr.Tst_val(atrs_end == -1, path, "atrs_end", atrs_end, actl.Atrs_end());
		return err;
	}
}
