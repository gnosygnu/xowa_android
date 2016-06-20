package gplx.xowa.users; import gplx.*; import gplx.xowa.*;
public class Xou_security_mgr implements Gfo_invk {
	public Xou_security_mgr(Xoae_app app) {this.app = app;} private Xoae_app app;
	public boolean Web_access_enabled() {return app.Api_root().Net().Enabled();}
	public void Web_access_enabled_(boolean v) {app.Api_root().Net().Enabled_(v);}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_web_access_enabled))			return Yn.To_str(this.Web_access_enabled());
		else if	(ctx.Match(k, Invk_web_access_enabled_))		Web_access_enabled_(m.ReadYn("v"));
		return this;
	}
	public static final    String 
	  Invk_web_access_enabled = "web_access_enabled", Invk_web_access_enabled_ = "web_access_enabled_";
}
