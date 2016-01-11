package gplx.xowa.apps.apis.xowa.usrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
import gplx.xowa.guis.views.*;
public class Xoapi_history implements GfoInvkAble {
	private Xoae_app app; private Xog_win_itm win;
	public void Ctor_by_app(Xoae_app app) {this.app = app;}
	public void Init_by_kit(Xoae_app app) {this.win = app.Gui_mgr().Browser_win();}
	public boolean Enabled() {return enabled;} private boolean enabled = true;
	public void Enabled_(boolean v) {enabled = v;}
	public void Goto_recent()		{win.Page__navigate_by_url_bar(app.Usere().History_mgr().Get_at_last());}
	public void Show()				{win.Page__navigate_by_url_bar("home/wiki/Special:XowaPageHistory");}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_enabled)) 							return Yn.To_str(this.Enabled());
		else if	(ctx.Match(k, Invk_enabled_)) 							Enabled_(m.ReadYn("v"));
		else if	(ctx.Match(k, Invk_goto_recent)) 						this.Goto_recent();
		else if	(ctx.Match(k, Invk_show)) 								this.Show();
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String Invk_enabled = "enabled", Invk_enabled_ = "enabled_", Invk_goto_recent = "goto_recent", Invk_show = "show";
}
