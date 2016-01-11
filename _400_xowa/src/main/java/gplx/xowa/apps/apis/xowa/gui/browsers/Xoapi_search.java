package gplx.xowa.apps.apis.xowa.gui.browsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*; import gplx.xowa.apps.apis.xowa.gui.*;
import gplx.gfui.*; import gplx.xowa.guis.views.*;
public class Xoapi_search implements GfoInvkAble {
	public void Init_by_kit(Xoae_app app) {this.app = app;} private Xoae_app app;
	private GfuiTextBox Search_box() {return app.Gui_mgr().Browser_win().Search_box();}
	private Xog_win_itm Win() {return app.Gui_mgr().Browser_win();}
	public void Focus() {this.Search_box().Focus_select_all();}
	public void Exec()	{this.Win().Page__navigate_by_search();}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_focus)) 					this.Focus();
		else if	(ctx.Match(k, Invk_exec)) 					this.Exec();
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String Invk_focus = "focus";
	public static final String Invk_exec = "exec";
}
