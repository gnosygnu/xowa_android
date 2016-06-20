package gplx.xowa.bldrs.setups.addons; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.setups.*;
public class Xoi_addon_mgr implements Gfo_invk {
	public Xoi_firefox_installer Firefox() {return firefox;} private Xoi_firefox_installer firefox = new Xoi_firefox_installer();
	public void Init_by_app(Xoae_app app) {
		firefox.Init_by_app(app);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_firefox)) 		return firefox;
		else	return Gfo_invk_.Rv_unhandled;
	}	private static final String Invk_firefox = "firefox";
}
