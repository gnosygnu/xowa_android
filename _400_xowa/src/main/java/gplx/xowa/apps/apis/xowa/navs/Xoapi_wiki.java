package gplx.xowa.apps.apis.xowa.navs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
import gplx.xowa.guis.views.*;
import gplx.xowa.htmls.hrefs.*;
public class Xoapi_wiki implements GfoInvkAble {
	private Xog_win_itm win;
	public void Init_by_kit(Xoae_app app) {
		win = app.Gui_mgr().Browser_win();
	}
	public void Random()		{win.Page__navigate_by_url_bar("Special:Random");}
	public void Sandbox()		{win.Page__navigate_by_url_bar("Project:Sandbox");}
	public void Main_page()		{
		win.Tab_mgr().Active_tab_assert();	// force an active tab in case all tabs are closed; needed for win.Active_page() below; DATE:2014-09-17
		win.Page__navigate_by_url_bar(win.Active_tab().Wiki().Domain_str() + Xoh_href_.Str__wiki);	// NOTE: add "/wiki/" to generate non-page like url;  EX: "home" -> "home/wiki/" which will be interpreted as a url, as opposed to "home" which will be intrepretted as page; DATE:2014-04-14
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_main_page)) 						this.Main_page();
		else if	(ctx.Match(k, Invk_random)) 						this.Random();
		else if	(ctx.Match(k, Invk_sandbox)) 						this.Sandbox();
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String Invk_main_page = "main_page", Invk_random = "random", Invk_sandbox = "sandbox";
}
