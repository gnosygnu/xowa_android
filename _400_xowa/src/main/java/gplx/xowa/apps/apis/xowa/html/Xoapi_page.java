package gplx.xowa.apps.apis.xowa.html; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
import gplx.core.btries.*;
public class Xoapi_page implements GfoInvkAble {
	public void Ctor_by_app(Xoae_app app) {
		toggle_mgr.Ctor_by_app(app);
	}
	public boolean View_html_generates_hdump() {return view_html_generates_hdump;} private boolean view_html_generates_hdump = false;
	public Xoapi_toggle_mgr Toggle_mgr()	{return toggle_mgr;} private final Xoapi_toggle_mgr toggle_mgr = new Xoapi_toggle_mgr();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_toggles)) 						return toggle_mgr;
		else if	(ctx.Match(k, Invk_view_html_generates_hdump_)) 	view_html_generates_hdump = m.ReadYn("v");
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String Invk_toggles = "toggles", Invk_view_html_generates_hdump_ = "view_html_generates_hdump_";
}
