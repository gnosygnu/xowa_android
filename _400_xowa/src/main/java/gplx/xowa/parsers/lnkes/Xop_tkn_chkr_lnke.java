package gplx.xowa.parsers.lnkes; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.tests.*;
public class Xop_tkn_chkr_lnke extends Xop_tkn_chkr_base {
	@Override public Class<?> TypeOf() {return Xop_lnke_tkn.class;}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_lnke;}
	public Xop_tkn_chkr_lnke(int bgn, int end) {super.Src_rng_(bgn, end);}
	public byte Lnke_typ() {return lnke_typ;} public Xop_tkn_chkr_lnke Lnke_typ_(byte v) {lnke_typ = v; return this;} private byte lnke_typ = Xop_lnke_tkn.Lnke_typ_null;
	public Xop_tkn_chkr_lnke Lnke_rng_(int bgn, int end) {lnke_bgn = bgn; lnke_end = end; return this;} private int lnke_bgn = -1; int lnke_end = -1;
	@Override public int Chk_hook(Tst_mgr mgr, String path, Object actl_obj, int err) {
		Xop_lnke_tkn actl = (Xop_lnke_tkn)actl_obj;
		err += mgr.Tst_val(lnke_typ == Xop_lnke_tkn.Lnke_typ_null, path, "lnke_typ", lnke_typ, actl.Lnke_typ());
		err += mgr.Tst_val(lnke_bgn == -1, path, "lnke_bgn", lnke_bgn, actl.Lnke_href_bgn());
		err += mgr.Tst_val(lnke_end == -1, path, "lnke_end", lnke_end, actl.Lnke_href_end());
		return err;
	}
}
