package gplx.xowa.apps.apis.xowa.gui.browsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*; import gplx.xowa.apps.apis.xowa.gui.*;
import gplx.gfui.*; import gplx.xowa.guis.views.*;
public class Xoapi_prog implements Gfo_invk {
	public void Init_by_kit(Xoae_app app) {this.app = app;} private Xoae_app app;
	private Xog_win_itm Win()		{return app.Gui_mgr().Browser_win();}
	public boolean Show_short_url()	{return show_short_url;} private boolean show_short_url;
	public void Focus()				{this.Win().Prog_box().Focus();}
	
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_focus)) 					this.Focus();
		else if	(ctx.Match(k, Invk_show_short_url)) 		return Yn.To_str(show_short_url);
		else if	(ctx.Match(k, Invk_show_short_url_)) 		show_short_url = m.ReadBool("v");
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String Invk_focus = "focus", Invk_show_short_url = "show_short_url", Invk_show_short_url_ = "show_short_url_";
}
