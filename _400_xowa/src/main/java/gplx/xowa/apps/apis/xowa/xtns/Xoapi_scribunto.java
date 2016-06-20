package gplx.xowa.apps.apis.xowa.xtns; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
import gplx.xowa.xtns.scribunto.*; import gplx.xowa.xtns.scribunto.engines.*;
public class Xoapi_scribunto implements Gfo_invk {
	private Xoae_app app;
	public void Init_by_kit(Xoae_app app) {
		this.app = app;
	}
	public void Engine_(byte v)	{
		Scrib_xtn_mgr scrib_xtn = (Scrib_xtn_mgr)app.Xtn_mgr().Get_or_fail(Scrib_xtn_mgr.XTN_KEY);
		scrib_xtn.Engine_type_(v);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_engine_lua_))	 		Engine_(Scrib_engine_type.Type_lua);
		else if	(ctx.Match(k, Invk_engine_luaj_))	 		Engine_(Scrib_engine_type.Type_luaj);
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String
	  Invk_engine_lua_ = "engine_lua_", Invk_engine_luaj_ = "engine_luaj_"
	;
}
