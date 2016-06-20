package gplx.xowa.apps.apis.xowa; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*;
import gplx.xowa.guis.views.*;
import gplx.xowa.apps.apis.xowa.apps.*; import gplx.xowa.apps.apis.xowa.envs.*; import gplx.xowa.apps.apis.xowa.startups.*;
public class Xoapi_app implements Gfo_invk {
	private Xog_win_itm win;
	public void Ctor_by_app(Xoae_app app) {
		fsys.Ctor_by_app(app);
	}
	public void Init_by_kit(Xoae_app app) {
		win = app.Gui_mgr().Browser_win();
	}
	public Xoapi_fsys		Fsys()		{return fsys;} private Xoapi_fsys fsys = new Xoapi_fsys();
	public void				Exit()		{win.App__exit();}
	public Xoapi_env		Env()		{return env;} private Xoapi_env env = new Xoapi_env();
	public Xoapi_startups	Startup()	{return startup;} private Xoapi_startups startup = new Xoapi_startups();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_exit)) 								this.Exit();
		else if	(ctx.Match(k, Invk_fsys)) 								return fsys;
		else if	(ctx.Match(k, Invk_startup)) 							return startup;
		else if	(ctx.Match(k, Invk_env)) 								return env;
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String Invk_exit = "exit", Invk_startup = "startup", Invk_env = "env", Invk_fsys = "fsys";
}
