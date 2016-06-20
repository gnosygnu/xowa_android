package gplx.xowa.apps.apis.xowa.html.skins; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*; import gplx.xowa.apps.apis.xowa.html.*;
public class Xoapi_skin_app_base implements Gfo_invk {
	public void Init_by_kit(Xoae_app app) {
	}
	public boolean Sidebar_home_enabled() {return sidebar_home_enabled;} public void Sidebar_home_enabled_(boolean v) {sidebar_home_enabled = v;} private boolean sidebar_home_enabled;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_sidebar_home_enabled))	 	return Yn.To_str(sidebar_home_enabled);
		else if	(ctx.Match(k, Invk_sidebar_home_enabled_))	 	sidebar_home_enabled = m.ReadYn("v");
		return this;
	}
	private static final String Invk_sidebar_home_enabled = "sidebar_home_enabled", Invk_sidebar_home_enabled_ = "sidebar_home_enabled_";
}
