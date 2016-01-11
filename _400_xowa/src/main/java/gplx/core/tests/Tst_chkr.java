package gplx.core.tests; import gplx.*; import gplx.core.*;
public interface Tst_chkr {
	Class<?> TypeOf();
	int Chk(Tst_mgr mgr, String path, Object actl);
}
class Tst_chkr_null implements Tst_chkr {
	public Class<?> TypeOf() {return Object.class;}
	public int Chk(Tst_mgr mgr, String path, Object actl) {
		mgr.Results().Add(Tst_itm.fail_("!=", path, "<cast type>", "<NULL TYPE>",  Type_adp_.NameOf_obj(actl)));
//			mgr.Results().Add(Tst_itm.fail_("!=", path, "<cast value>", "<NULL VAL>", Object_.Xto_str_strict_or_null(actl)));
		return 1;
	}
}
