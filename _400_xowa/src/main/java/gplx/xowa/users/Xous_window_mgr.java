package gplx.xowa.users; import gplx.*; import gplx.xowa.*;
import gplx.xowa.guis.views.*;
import gplx.xowa.apps.apis.xowa.startups.tabs.*;
public class Xous_window_mgr implements GfoInvkAble {
	public Xous_window_mgr(Xoue_user user) {
		this.user = user;
	}
	public Xoue_user User() {return user;} private Xoue_user user;
	public Rect_ref Rect() {if (rect == null) rect = Rect_new(); return rect;} Rect_ref rect;
	public boolean Maximized() {return maximized;} private boolean maximized = false;
	public void Save_window(gplx.gfui.GfuiWin win) {
		Xoae_app app = user.Appe();
		gplx.xowa.apps.cfgs.Xoa_cfg_mgr cfg_mgr = app.Cfg_mgr();
		if (user.Cfg_mgr().Startup_mgr().Window_mgr().Mode_tid() == Xouc_window_mgr.Mode_tid_previous) {
			cfg_mgr.Set_by_app("app.user.session.window.maximized"	, Yn.To_str(win.Maximized()));
			cfg_mgr.Set_by_app("app.user.session.window.rect"		, win.Rect().Xto_str());
		}
		Xoapi_startup_tabs startup_tabs = app.Api_root().App().Startup().Tabs();
		if (startup_tabs.Type() == Xoapi_startup_tabs_tid_.Tid_previous) {
			cfg_mgr.Set_by_app("xowa.api.app.startup.tabs.previous"	, Calc_previous_tabs(app.Gui_mgr().Browser_win().Tab_mgr()));
		}
		cfg_mgr.Set_by_app("xowa.api.app.env.version_previous"	, Xoa_app_.Version);
		app.Api_root().Html().Page().Toggle_mgr().Save(cfg_mgr);
		cfg_mgr.Db_save_txt();
	}
	private String Calc_previous_tabs(Xog_tab_mgr tab_mgr) {
		Bry_bfr bfr = Bry_bfr.new_();
		int len = tab_mgr.Tabs_len();
		for (int i = 0; i < len; ++i) {
			if (i != 0) bfr.Add_byte_nl();
			Xog_tab_itm tab = tab_mgr.Tabs_get_at(i);
			bfr.Add_str_u8(tab.Page().Url().To_str());
		}
		return bfr.To_str_and_clear();
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_rect))					return rect;
		else if	(ctx.Match(k, Invk_rect_))					rect = Rect_ref.parse(m.ReadStr("v"));
		else if	(ctx.Match(k, Invk_maximized))				return Yn.To_str(maximized);
		else if	(ctx.Match(k, Invk_maximized_))				maximized = m.ReadYn("v");
		return this;
	}	public static final String Invk_rect = "rect", Invk_rect_ = "rect_", Invk_maximized = "maximized", Invk_maximized_ = "maximized_";
	private static Rect_ref Rect_new() {	// NOTE: lazy initialization, else will fail for non-X11 environments; DATE:2014-03-02
		gplx.gfui.SizeAdp size = Xouc_window_mgr.Screen_maximized_calc();
		return new Rect_ref(0, 0, size.Width(), size.Height());
	}
}
