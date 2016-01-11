package gplx.xowa.xtns.flaggedRevs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.htmls.*; import gplx.xowa.wikis.pages.skins.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Pending_change_level_func extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_pendingChangeLevel;}
	@Override public Pf_func New(int id, byte[] name) {return new Pending_change_level_func().Name_(name);}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {}// NOOP
	public static final Pending_change_level_func Instance = new Pending_change_level_func(); Pending_change_level_func() {}
}
