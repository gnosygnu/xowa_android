package gplx.xowa.apps.apis.xowa.gui.browsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*; import gplx.xowa.apps.apis.xowa.gui.*;
import gplx.gfui.*; import gplx.xowa.guis.views.*;
public class Xoapi_prog_log implements Gfo_invk {
	public void Init_by_kit(Xoae_app app) {this.app = app;} private Xoae_app app;
	public void Show()									{app.Gui_mgr().Show_prog();}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_show)) 						this.Show();
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String Invk_show = "show";
}
