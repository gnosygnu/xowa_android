package gplx.xowa.guis.menus; import gplx.*; import gplx.xowa.*; import gplx.xowa.guis.*;
import gplx.xowa.guis.menus.dom.*;
import gplx.xowa.langs.*;
public class Xog_menu_mgr implements GfoInvkAble {
	private Xoae_app app;
	public Xog_menu_mgr(Xoa_gui_mgr gui_mgr) {
		menu_bldr = new Xog_mnu_bldr();
		regy = new Xog_mnu_regy(gui_mgr);
		popup_mnu_mgr = new Xog_popup_mnu_mgr(gui_mgr, this);
		window_mnu_mgr = new Xog_window_mnu_mgr(gui_mgr, this);
	}
	public Xog_mnu_regy Regy() {return regy;} private Xog_mnu_regy regy;
	public Xog_popup_mnu_mgr Popup() {return popup_mnu_mgr;} private Xog_popup_mnu_mgr popup_mnu_mgr;
	public Xog_window_mnu_mgr Window() {return window_mnu_mgr;} private Xog_window_mnu_mgr window_mnu_mgr;
	public Xog_mnu_bldr Menu_bldr() {return menu_bldr;} private Xog_mnu_bldr menu_bldr;
	public void Init_by_app(Xoae_app app) {
		this.app = app;
		regy.Init_by_app(app);
	}
	public void Init_by_kit() {
		try {
			if (!app.Mode().Tid_is_gui()) return;	// NOTE: do not try to initialize menu if http_server; will fail in headless mode when it tries to load SWT images; DATE:2015-03-27
			popup_mnu_mgr.Init_by_kit();
			window_mnu_mgr.Init_by_kit();
			Lang_changed(app.Usere().Lang());
		}
		catch (Exception e) {	// ignore errors while loading custom menus, else fatal error; DATE:2014-07-01
			app.Usr_dlg().Warn_many("", "", "error while loading menus; err=~{0}", Err_.Message_gplx_full(e));
		}
	}
	public void Lang_changed(Xol_lang_itm lang) {
		window_mnu_mgr.Lang_changed(lang);
		popup_mnu_mgr.Lang_changed(lang);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_contexts))			return popup_mnu_mgr;
		else if	(ctx.Match(k, Invk_windows))			return window_mnu_mgr;
		else	return GfoInvkAble_.Rv_unhandled;
	}	private static final String Invk_contexts = "contexts", Invk_windows = "windows";
}
