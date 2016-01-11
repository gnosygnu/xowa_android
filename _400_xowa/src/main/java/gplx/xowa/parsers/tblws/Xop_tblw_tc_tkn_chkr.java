package gplx.xowa.parsers.tblws; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.tests.*;
public class Xop_tblw_tc_tkn_chkr extends Xop_tkn_chkr_base {
	@Override public Class<?> TypeOf() {return Xop_tblw_tc_tkn.class;}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_tblw_tc;}
	@Override public int Chk_hook(Tst_mgr mgr, String path, Object actl_obj, int err) {
//			Xop_tblw_tc_tkn actl = (Xop_tblw_tc_tkn)actl_obj;
		return err;
	}
}
