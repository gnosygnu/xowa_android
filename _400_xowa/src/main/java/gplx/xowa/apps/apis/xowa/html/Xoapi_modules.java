package gplx.xowa.apps.apis.xowa.html; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
import gplx.xowa.apps.apis.xowa.html.modules.*;
public class Xoapi_modules implements GfoInvkAble {
	public void Init_by_kit(Xoae_app app) {
		popups.Init_by_app(app);
	}
	public Xoapi_collapsible	Collapsible()	{return collapsible;} private Xoapi_collapsible collapsible = new Xoapi_collapsible();
	public Xoapi_navframe		Navframe()		{return navframe;} private Xoapi_navframe navframe = new Xoapi_navframe();
	public Xoapi_toc			Toc()			{return toc;} private Xoapi_toc toc = new Xoapi_toc();
	public Xoapi_popups			Popups()		{return popups;} private Xoapi_popups popups = new Xoapi_popups();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_collapsible))	 		return collapsible;
		else if	(ctx.Match(k, Invk_navframe))	 			return navframe;
		else if	(ctx.Match(k, Invk_toc))	 				return toc;
		else if	(ctx.Match(k, Invk_popups))	 				return popups;
		else	return GfoInvkAble_.Rv_unhandled;
	}
	private static final String Invk_collapsible = "collapsible", Invk_navframe = "navframe", Invk_toc = "toc", Invk_popups = "popups";
}
