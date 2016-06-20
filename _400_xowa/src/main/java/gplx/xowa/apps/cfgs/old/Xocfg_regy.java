package gplx.xowa.apps.cfgs.old; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.cfgs.*;
public class Xocfg_regy implements Gfo_invk {
	public Xocfg_regy(Xoae_app app) {
		app_cfg = new Xocfg_root(app, Xocfg_root_.Tid_app);
	}
	public Xocfg_root App() {return app_cfg;} private Xocfg_root app_cfg;
	public Xocfg_root Get_or_null(String key) {
		if		(String_.Eq(key, Key_app))		return app_cfg;
		else									throw Err_.new_unhandled(key);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_get))				return Get_or_null(m.ReadStrOr("v", Key_app));
		else	return Gfo_invk_.Rv_unhandled;
	}
	private static final String Invk_get = "get";
	public static final String Key_app = null;
}
