package gplx.xowa.apps.apis.xowa; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*;
public class Xoapi_net implements Gfo_invk, Gfo_evt_itm {
	public Xoapi_net() {this.ev_mgr = new Gfo_evt_mgr(this);}
	public Gfo_evt_mgr Evt_mgr() {return ev_mgr;} private Gfo_evt_mgr ev_mgr;
	public void Init_by_kit(Xoae_app app) {
	}
	public boolean Enabled() {return enabled;} private boolean enabled = true;
	public void Enabled_(boolean v) {
		this.enabled = v;
		gplx.core.ios.IoEngine_system.Web_access_enabled = v;
		Gfo_evt_mgr_.Pub_val(this, gplx.xowa.guis.menus.dom.Xog_mnu_evt_mgr.Evt_selected_changed, v);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_enabled))	 			return this.Enabled();
		else if	(ctx.Match(k, Invk_enabled_n_))	 			this.Enabled_(Bool_.N);
		else if	(ctx.Match(k, Invk_enabled_y_))	 			this.Enabled_(Bool_.Y);
		else if	(ctx.Match(k, Invk_enabled_x_)) 			this.Enabled_(this.Enabled());
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String
	  Invk_enabled = "enabled", Invk_enabled_n_ = "enabled_n_", Invk_enabled_y_ = "enabled_y_", Invk_enabled_x_ = "enabled_x_"
	;
	public static final String Evt_enabled_changed = "enabled_changed";
}
