package gplx.xowa.apps.apis.xowa; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*;
import gplx.xowa.apps.apis.xowa.gui.*;
public class Xoapi_gui implements GfoInvkAble {
	public void Init_by_kit(Xoae_app app) {
		browser.Init_by_kit(app);
		font.Init_by_kit(app);
		page.Init_by_kit(app);
	}
	public Xoapi_browser	Browser() {return browser;} private Xoapi_browser browser = new Xoapi_browser();
	public Xoapi_font		Font() {return font;} private Xoapi_font font = new Xoapi_font();
	public Xoapi_page		Page() {return page;} private Xoapi_page page = new Xoapi_page();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_browser)) 					return browser;
		else if	(ctx.Match(k, Invk_font))	 					return font;
		else if	(ctx.Match(k, Invk_page)) 						return page;
		return this;
	}
	private static final String Invk_browser = "browser", Invk_font = "font", Invk_page = "page";
}
