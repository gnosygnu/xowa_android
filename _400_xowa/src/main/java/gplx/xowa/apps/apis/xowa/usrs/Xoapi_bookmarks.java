package gplx.xowa.apps.apis.xowa.usrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
import gplx.xowa.guis.history.*; import gplx.xowa.guis.views.*;
import gplx.xowa.users.bmks.*;
import gplx.xowa.wikis.*;
public class Xoapi_bookmarks implements Gfo_invk {
	private Xoae_app app; private Xog_win_itm win;
	public void Ctor_by_app(Xoae_app app) {this.app = app;}
	public void Init_by_kit(Xoae_app app) {this.win = app.Gui_mgr().Browser_win();}
	public boolean Enabled() {return enabled;} private boolean enabled = true;
	public void Enabled_(boolean v) {enabled = v;}
	public boolean Delete_confirm() {return delete_confirm;} private boolean delete_confirm = true;
	public void Show() {win.Page__navigate_by_url_bar("home/wiki/Special:XowaBookmarks");}
	public String Add(String url_str) {
		if (!enabled) return app.Html__bridge_mgr().Msg_bldr().To_json_str__empty();
		Xoa_url url = null;
		if (url_str == null) {
			Xog_tab_itm tab = win.Active_tab(); if (tab == Xog_tab_itm_.Null) return app.Html__bridge_mgr().Msg_bldr().Clear().Notify_pass_("bookmark added").To_json_str();	// called by http_server; return success
			url = tab.Page().Url();
		}
		else
			url = app.User().Wikii().Utl__url_parser().Parse(Bry_.new_u8(url_str));
		app.User().User_db_mgr().Bmk_mgr().Itms__add(Xoud_bmk_mgr.Owner_root, url);
		String msg = "bookmark added: " + String_.new_u8(url.Page_bry());
		String rv = app.Html__bridge_mgr().Msg_bldr().Clear().Notify_pass_(msg).To_json_str();
		win.Active_tab().Html_box().Html_js_eval_proc_as_str("xowa.cmds.exec_by_str", "xowa.notify", "{\"text\":\"" + msg + "\",\"status\":\"success\"}");
		return rv;
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_enabled)) 							return Yn.To_str(enabled);
		else if	(ctx.Match(k, Invk_enabled_)) 							enabled = m.ReadYn("v");
		else if	(ctx.Match(k, Invk_delete_confirm)) 					return Yn.To_str(delete_confirm);
		else if	(ctx.Match(k, Invk_delete_confirm_)) 					delete_confirm = m.ReadYn("v");
		else if	(ctx.Match(k, Invk_add)) 								return this.Add(m.ReadStrOr("v", null));
		else if	(ctx.Match(k, Invk_show)) 								this.Show();
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String
	  Invk_enabled = "enabled", Invk_enabled_ = "enabled_"
	, Invk_delete_confirm = "delete_confirm", Invk_delete_confirm_ = "delete_confirm_"
	, Invk_add = "add", Invk_show = "show";
}
