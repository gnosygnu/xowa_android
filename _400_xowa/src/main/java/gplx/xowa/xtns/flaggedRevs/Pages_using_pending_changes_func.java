package gplx.xowa.xtns.flaggedRevs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.htmls.*; import gplx.xowa.wikis.pages.skins.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Pages_using_pending_changes_func extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_pagesUsingPendingChanges;}
	@Override public Pf_func New(int id, byte[] name) {return new Pages_using_pending_changes_func().Name_(name);}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		bfr.Add_int_fixed(0, 1);
	}
	public static final Pages_using_pending_changes_func Instance = new Pages_using_pending_changes_func(); Pages_using_pending_changes_func() {}
}
