package gplx.xowa.apps.apis.xowa.usrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
import gplx.xowa.guis.views.*;
public class Xoapi_logs implements GfoInvkAble {
	private Xoae_app app;
	public void Ctor_by_app(Xoae_app app) {this.app = app;}
	public void Init_by_kit(Xoae_app app) {}
	public boolean Enabled() {return app.Log_wtr().Enabled();}
	public void Enabled_(boolean v) {
		app.Log_wtr().Enabled_(v);
		if (!v)
			Io_mgr.Instance.DeleteFil_args(app.Log_wtr().Session_fil()).MissingFails_off().Exec();
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_enabled)) 							return Yn.To_str(this.Enabled());
		else if	(ctx.Match(k, Invk_enabled_)) 							Enabled_(m.ReadYn("v"));
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String Invk_enabled = "enabled", Invk_enabled_ = "enabled_";
}
