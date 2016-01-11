package gplx.xowa.apps.apis.xowa.html; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
import gplx.xowa.apps.apis.xowa.html.skins.*;
public class Xoapi_skins implements GfoInvkAble {
	private Hash_adp hash = Hash_adp_.new_();
	public Xoapi_skins() {
		server.Sidebar_home_enabled_(true);
		hash.Add("desktop", desktop);
		hash.Add("server", server);
	}
	public Xoapi_skin_app_base Desktop() {return desktop;} private Xoapi_skin_app_base desktop = new Xoapi_skin_app_base();
	public Xoapi_skin_app_base Server () {return server ;} private Xoapi_skin_app_base server  = new Xoapi_skin_app_base();
	private GfoInvkAble Get(String key) {return (GfoInvkAble)hash.Get_by(key);}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_get))	 			return Get(m.ReadStr("v"));
		return this;
	}
	private static final String Invk_get = "get";
}
