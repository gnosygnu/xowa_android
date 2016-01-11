package gplx.xowa.apps.apis.xowa.startups; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
import gplx.xowa.apps.apis.xowa.startups.tabs.*;
public class Xoapi_startups implements GfoInvkAble {
	public void Init_by_kit(Xoae_app app) {
	}
	public Xoapi_startup_tabs		Tabs() {return tabs;} private Xoapi_startup_tabs tabs = new Xoapi_startup_tabs();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_tabs)) 						return tabs;
		else	return GfoInvkAble_.Rv_unhandled;
	}
	private static final String Invk_tabs = "tabs";
}
