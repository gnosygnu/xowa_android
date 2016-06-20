package gplx.xowa.apps.cfgs.gui; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.cfgs.*;
public class Xocfg_gui_mgr implements Gfo_invk {
	public Xocfg_gui_mgr(Xoae_app app) {
		win_cfg = new Xocfg_win(app);
	}
	public Xocfg_win Win() {return win_cfg;} private Xocfg_win win_cfg;
	public Xocfg_html Html() {return html_cfg;} private Xocfg_html html_cfg = new Xocfg_html();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_win))			return win_cfg;
		else if	(ctx.Match(k, Invk_html))			return html_cfg;
		else	return Gfo_invk_.Rv_unhandled;
	}
	private static final String Invk_win = "win", Invk_html = "html";
}
