package gplx.xowa.apps.apis.xowa.gui; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
import gplx.xowa.apps.cfgs.gui.*; import gplx.xowa.htmls.*;
public class Xoapi_font implements Gfo_invk {
	private Xoae_app app;
	public void Init_by_kit(Xoae_app app) {
		this.app = app;
	}
	public void Increase() {Adj(1);}
	public void Decrease() {Adj(-1);}
	public void Reset() {Set(false, Xoh_page_mgr.Font_size_default, Xocfg_win.Font_size_default);}
	public void Adj(int adj) {
		float html_font_size = app.Html_mgr().Page_mgr().Font_size() + adj;
		float gui_font_size = app.Gui_mgr().Win_cfg().Font().Size() + adj; // (html_font_size * .75f) - 4;	// .75f b/c 16px = 12 pt; -4 b/c gui font is currently 4 pt smaller 
		if (html_font_size < 1 || gui_font_size < 1) return;
		Set(true, html_font_size, gui_font_size);
	}
	private void Set(boolean enabled, float html_font_size, float gui_font_size) {
		if (html_font_size <= 0) return;	// font must be positive
		app.Html_mgr().Page_mgr().Font_enabled_(enabled);
		app.Html_mgr().Page_mgr().Font_size_(html_font_size);
		app.Cfg_mgr().Set_by_app("app.html.page.font_enabled", "y");
		app.Cfg_mgr().Set_by_app("app.html.page.font_size", Float_.To_str(app.Html_mgr().Page_mgr().Font_size()));
		app.Gui_mgr().Win_cfg().Font().Size_(gui_font_size);
		app.Cfg_mgr().Set_by_app("app.gui.win_opts.font.size", Float_.To_str(gui_font_size));
		app.Cfg_mgr().Db_save_txt();
		app.Gui_mgr().Browser_win().Page__reload();	// NOTE: force reload; needed if viewing home/wiki/Options/HTML, else Font size won't update
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_increase))	 		this.Increase();
		else if	(ctx.Match(k, Invk_decrease))	 		this.Decrease();
		else if	(ctx.Match(k, Invk_reset))	 			this.Reset();
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String
	  Invk_increase = "increase", Invk_decrease = "decrease", Invk_reset = "reset"
	;
}
