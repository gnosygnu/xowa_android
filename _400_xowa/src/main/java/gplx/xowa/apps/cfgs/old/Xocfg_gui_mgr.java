package gplx.xowa.apps.cfgs.old; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.cfgs.*;
public class Xocfg_gui_mgr implements Gfo_invk {
	public Xocfg_gui_mgr(Xoae_app app) {bnd_mgr = new Xocfg_bnd_mgr(app);}
	public Xocfg_tab_mgr Tab_mgr() {return tab_mgr;} private Xocfg_tab_mgr tab_mgr = new Xocfg_tab_mgr();
	public Xocfg_bnd_mgr Bnd_mgr() {return bnd_mgr;} private Xocfg_bnd_mgr bnd_mgr;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_tabs))				return tab_mgr;
		else if	(ctx.Match(k, Invk_bnds))				return bnd_mgr;
		else	return Gfo_invk_.Rv_unhandled;
	}
	private static final String Invk_tabs = "tabs", Invk_bnds = "bnds";
}
