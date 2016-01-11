package gplx.xowa.apps; import gplx.*; import gplx.xowa.*;
import gplx.xowa.guis.views.*;
public class Xoa_cur implements GfoInvkAble {
	public Xoa_cur(Xoae_app app) {this.app = app;} private Xoae_app app;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_wiki)) {
			Xog_win_itm win = app.Gui_mgr().Browser_win();
			return win.Active_tab() == null ? GfoInvkAble_.Null : win.Active_page().Wikie(); // null check when called from mass html gen; DATE:2014-06-04
		}
		else if	(ctx.Match(k, Invk_win))			return app.Gui_mgr().Browser_win();
		else if	(ctx.Match(k, Invk_user))			return app.Usere();
		else return GfoInvkAble_.Rv_unhandled;
	}	private static final String Invk_wiki = "wiki", Invk_win = "win", Invk_user = "user";
}
