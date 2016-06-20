package gplx.xowa.users; import gplx.*; import gplx.xowa.*;
public class Xouc_pages_mgr implements Gfo_invk {
	public Xouc_pages_mgr(Xou_cfg config) {}
	public String Home() {return home;} public Xouc_pages_mgr Home_(String v) {home = v; return this;} private String home = Page_xowa;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_home))			return home;
		else if	(ctx.Match(k, Invk_home_))			home = m.ReadStr("v");
		return this;
	}	public static final String Invk_home = "home", Invk_home_ = "home_";
	public static final String Page_xowa = "home/wiki/Main_Page";
}
