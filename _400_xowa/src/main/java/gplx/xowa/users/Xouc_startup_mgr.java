package gplx.xowa.users; import gplx.*; import gplx.xowa.*;
public class Xouc_startup_mgr implements Gfo_invk {
	public Xouc_startup_mgr(Xou_cfg config) {this.config = config; window_mgr = new Xouc_window_mgr(config.User());} private Xou_cfg config;
	public Xouc_window_mgr Window_mgr() {return window_mgr;} private Xouc_window_mgr window_mgr;
	public String Page() {return page;} public Xouc_startup_mgr Page_(String v) {page = v; return this;} private String page = "xowa";
	public String Page_url() {
		byte v = Page_parse(page);
		switch (v) {
			case Page_home: return config.Pages_mgr().Home();
			case Page_last: return String_.new_u8(config.User().Appe().Gui_mgr().Browser_win().Active_page().Ttl().Raw());
			case Page_xowa: return Xouc_pages_mgr.Page_xowa;
			default: throw Err_.new_unhandled(page);
		}
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_page))			return page;
		else if	(ctx.Match(k, Invk_page_))			page = m.ReadStr("v");
		else if	(ctx.Match(k, Invk_custom_config))	return Custom_config();
		else if	(ctx.Match(k, Invk_custom_config_))	Custom_config_(m.ReadStr("v"));
		else if	(ctx.Match(k, Invk_window))			return window_mgr;
		return this;
	}
	public static final String Invk_window = "window", Invk_page = "page", Invk_page_ = "page_", Invk_custom_config = "custom_config", Invk_custom_config_ = "custom_config_";
	private byte Page_parse(String s) {
		if		(String_.Eq(s, "home")) return Page_home;
		else if	(String_.Eq(s, "last")) return Page_last;
		else if	(String_.Eq(s, "xowa")) return Page_xowa;
		else							return Page_xowa;
	}
	private String Custom_config() {
		if (custom_config == null) {	// NOTE: LoadFilStr never returns null, so this will only execute once
			Io_url custom_config_url = config.User().Fsys_mgr().App_data_cfg_custom_fil();
			custom_config = Io_mgr.Instance.LoadFilStr_args(custom_config_url).MissingIgnored_().Exec();				
		}
		return custom_config;
	}	private String custom_config;
	private void Custom_config_(String v) {
		Xoae_app app = config.User().Appe();
		Object rslt = app.Gfs_mgr().Run_str(v);
		if (rslt == Gfo_invk_.Rv_error) {
			app.Usr_dlg().Warn_many("", "", "custom script failed: ~{0}", v);
			return;
		}
		custom_config = v;
		// Io_url custom_config_url = config.User().Fsys_mgr().App_data_cfg_custom_fil();	// DELETE: no longer sync user_cfg to custom.gfs; already saved in user.gfs; DATE:2014-05-30
		// Io_mgr.Instance.SaveFilStr(custom_config_url, v);
	}
	public static final byte Page_home = 0, Page_last = 1, Page_xowa = 2;
}
