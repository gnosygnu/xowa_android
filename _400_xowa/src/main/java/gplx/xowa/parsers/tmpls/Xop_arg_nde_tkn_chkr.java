package gplx.xowa.parsers.tmpls; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.tests.*;
public class Xop_arg_nde_tkn_chkr extends Xop_tkn_chkr_base {
	@Override public Class<?> TypeOf() {return Arg_nde_tkn.class;}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_arg_nde;}
	public Xop_tkn_chkr_base Key_tkn() {return key_tkn;} public Xop_arg_nde_tkn_chkr Key_tkn_(Xop_tkn_chkr_base v) {key_tkn = v; return this;} private Xop_tkn_chkr_base key_tkn;
	public Xop_tkn_chkr_base Val_tkn() {return val_tkn;} public Xop_arg_nde_tkn_chkr Val_tkn_(Xop_tkn_chkr_base v) {val_tkn = v; return this;} private Xop_tkn_chkr_base val_tkn;
	@Override public int Chk_hook(Tst_mgr mgr, String path, Object actl_obj, int err) {
		Arg_nde_tkn actl = (Arg_nde_tkn)actl_obj;
		if (key_tkn != null) err += mgr.Tst_sub_obj(key_tkn, actl.Key_tkn(), path + "." + "key", err);
		if (val_tkn != null) err += mgr.Tst_sub_obj(val_tkn, actl.Val_tkn(), path + "." + "val", err);
		return err;
	}
}
