package gplx.xowa.guis; import gplx.*; import gplx.xowa.*;
import gplx.xowa.htmls.portal.*;
public class Xog_html_mgr implements GfoInvkAble {
	public Xog_html_mgr(Xoae_app app) {this.app = app; portal_mgr = new Xoa_portal_mgr(app);} private Xoae_app app;
	public Xoa_portal_mgr Portal_mgr() {return portal_mgr;} private Xoa_portal_mgr portal_mgr;
	public boolean Javascript_enabled() {return javascript_enabled;} private boolean javascript_enabled = true;
	private void Javascript_enabled_(boolean v) {
		javascript_enabled = v;
		app.Gui_mgr().Browser_win().Tab_mgr().Tabs_javascript_enabled_(v);
	}
	public String Auto_focus_id() {return auto_focus_id;} private String auto_focus_id = "";
	public byte[] Css_xtn() {return css_xtn;} public void Css_xtn_(byte[] v) {css_xtn = v;} private byte[] css_xtn = Bry_.Empty;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_javascript_enabled))			return Yn.To_str(javascript_enabled);
		else if	(ctx.Match(k, Invk_javascript_enabled_))		Javascript_enabled_(m.ReadYn("v"));
		else if	(ctx.Match(k, Invk_auto_focus_id_))				auto_focus_id = m.ReadStr("v");
		else if	(ctx.Match(k, Invk_css_xtn))					return css_xtn;
		else if	(ctx.Match(k, Invk_css_xtn_))					css_xtn = m.ReadBry("v");
		else if	(ctx.Match(k, Invk_portal))						return portal_mgr;
		else return GfoInvkAble_.Rv_unhandled;
		return this;
	}	private static final String Invk_javascript_enabled = "javascript_enabled", Invk_javascript_enabled_ = "javascript_enabled_", Invk_auto_focus_id_ = "auto_focus_id_", Invk_css_xtn = "css_xtn", Invk_css_xtn_ = "css_xtn_", Invk_portal = "portal";
}
