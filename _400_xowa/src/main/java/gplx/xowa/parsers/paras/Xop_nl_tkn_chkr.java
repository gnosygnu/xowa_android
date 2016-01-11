package gplx.xowa.parsers.paras; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.tests.*;
public class Xop_nl_tkn_chkr extends Xop_tkn_chkr_base {
	@Override public Class<?> TypeOf() {return Xop_nl_tkn.class;}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_newLine;}
	public byte Nl_tid() {return nl_typeId;} public Xop_nl_tkn_chkr Nl_tid_(byte v) {nl_typeId = v; return this;} private byte nl_typeId = Xop_nl_tkn.Tid_unknown;
	@Override public int Chk_hook(Tst_mgr mgr, String path, Object actl_obj, int err) {
		Xop_nl_tkn actl = (Xop_nl_tkn)actl_obj;
		err += mgr.Tst_val(nl_typeId == Xop_nl_tkn.Tid_unknown, path, "nl_typeId", nl_typeId, actl.Nl_tid());
		return err;
	}
}
