package gplx.xowa.apps.apis.xowa.html; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
public class Xoapi_page implements Gfo_invk {
	public void Ctor_by_app(Xoae_app app) {
		toggle_mgr.Ctor_by_app(app);
	}
	public Xoapi_toggle_mgr Toggle_mgr()	{return toggle_mgr;} private final    Xoapi_toggle_mgr toggle_mgr = new Xoapi_toggle_mgr();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_toggles)) 						return toggle_mgr;
		else	return Gfo_invk_.Rv_unhandled;
	}
	private static final String Invk_toggles = "toggles";
}
