package gplx.xowa.parsers.tblws; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.tests.*;
public class Xop_tblw_tb_tkn_chkr extends Xop_tkn_chkr_base {
	@Override public Class<?> TypeOf() {return Xop_tblw_tb_tkn.class;}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_tblw_tb;}
	public int Caption_count() {return caption_count;} public Xop_tblw_tb_tkn_chkr Caption_count_(int v) {caption_count = v; return this;} private int caption_count = Int_.Neg1_count;
	public Xop_tblw_tb_tkn_chkr Atrs_rng_(int bgn, int end) {this.atrs_bgn = bgn; this.atrs_end = end; return this;} private int atrs_bgn = Xop_tblw_wkr.Atrs_null, atrs_end = Xop_tblw_wkr.Atrs_null;
	@Override public int Chk_hook(Tst_mgr mgr, String path, Object actl_obj, int err) {
		Xop_tblw_tb_tkn actl = (Xop_tblw_tb_tkn)actl_obj;
		err += mgr.Tst_val(caption_count == Int_.Neg1_count, path, "caption_count", caption_count, actl.Caption_count());
		err += mgr.Tst_val(atrs_bgn == Xop_tblw_wkr.Atrs_null, path, "atrs_bgn", atrs_bgn, actl.Atrs_bgn());
		err += mgr.Tst_val(atrs_end == Xop_tblw_wkr.Atrs_null, path, "atrs_end", atrs_end, actl.Atrs_end());
		return err;
	}
}
