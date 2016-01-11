package gplx.xowa.parsers.tmpls; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.tests.*;
public class Xop_arg_itm_tkn_chkr extends Xop_tkn_chkr_base {
	@Override public Class<?> TypeOf() {return Arg_itm_tkn.class;}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_arg_itm;}
	@Override public int Chk_hook(Tst_mgr mgr, String path, Object actl_obj, int err) {
//			Arg_itm_tkn actl = (Arg_itm_tkn)actl_obj;
		return err;
	}
}
