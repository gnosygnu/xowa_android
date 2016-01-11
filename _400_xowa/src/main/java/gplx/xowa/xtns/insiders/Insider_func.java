package gplx.xowa.xtns.insiders; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.xtns.pfuncs.*; import gplx.xowa.wikis.pages.skins.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Insider_func extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_insider;}
	@Override public Pf_func New(int id, byte[] name) {return new Insider_func().Name_(name);}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		byte[] val = Eval_argx(ctx, src, caller, self);
		Xopg_xtn_skin_mgr skin_mgr = ctx.Cur_page().Html_data().Xtn_skin_mgr();
		Insider_xtn_skin_itm skin_itm = (Insider_xtn_skin_itm)skin_mgr.Get_or_null(Insider_xtn_skin_itm.KEY);
		if (skin_itm == null) {
			skin_itm = new Insider_xtn_skin_itm(ctx.Wiki().Xtn_mgr().Xtn_insider().Html_bldr());
			skin_mgr.Add(skin_itm);
		}
		skin_itm.Add(val);
	}
	public static final Insider_func Instance = new Insider_func(); Insider_func() {}
}
