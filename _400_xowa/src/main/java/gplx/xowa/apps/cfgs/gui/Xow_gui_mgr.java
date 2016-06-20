package gplx.xowa.apps.cfgs.gui; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.cfgs.*;
public class Xow_gui_mgr implements Gfo_invk {
	public Xocfg_html Cfg_browser() {return cfg_browser;} private Xocfg_html cfg_browser = new Xocfg_html();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_cfg_browser))			return cfg_browser;
		else	return Gfo_invk_.Rv_unhandled;
	}	private static final String Invk_cfg_browser = "cfg_browser";
}
