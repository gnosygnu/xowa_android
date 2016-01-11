package gplx.xowa.apps.apis.xowa.gui.browsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*; import gplx.xowa.apps.apis.xowa.gui.*;
import gplx.gfui.*; import gplx.xowa.guis.views.*; import gplx.core.envs.*;
public class Xoapi_url implements GfoInvkAble {
	public void Init_by_kit(Xoae_app app) {this.app = app;} private Xoae_app app;
	private GfuiTextBox Url_box() {return app.Gui_mgr().Browser_win().Url_box();}
	public void Focus()					{this.Url_box().Focus_select_all();}
	public void Exec()					{Exec_wkr(Bool_.N, this.Url_box().Text());}
	public void Exec_by_paste()			{Exec_wkr(Bool_.N, ClipboardAdp_.GetText());}
	public void Exec_new_tab_by_paste() {Exec_wkr(Bool_.Y, ClipboardAdp_.GetText());}
	public void Restore() {
		Xog_tab_itm tab = app.Gui_mgr().Browser_win().Active_tab(); if (tab == Xog_tab_itm_.Null) return;
		this.Url_box().Text_(tab.Page().Url().To_str());
	}
	private void Exec_wkr(boolean new_tab, String urls_text) {
		if (Op_sys.Cur().Tid_is_wnt())
			urls_text = String_.Replace(urls_text, Op_sys.Wnt.Nl_str(), Op_sys.Lnx.Nl_str());
		String[] urls = String_.Split(String_.Trim(urls_text), Op_sys.Lnx.Nl_str());
		int urls_len = urls.length;
		if (urls_len == 0) return;
		if (urls_len == 1) {
			String url = urls[0];
			this.Url_box().Text_(url);
			app.Gui_mgr().Browser_win().Page__navigate_by_url_bar(url);
		}
		else {
			for (int i = 0; i < urls_len; i++) {
				String url = urls[i];
				if (String_.Has_at_bgn(url, "\"") &&  String_.Has_at_bgn(url, "\""))
					url = String_.Mid(url, 1, String_.Len(url) - 1);
				app.Gui_mgr().Browser_win().Tab_mgr().Tabs_new_link(url, false);
			}
		}
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_focus)) 						this.Focus();
		else if	(ctx.Match(k, Invk_exec)) 						this.Exec();
		else if	(ctx.Match(k, Invk_exec_by_paste)) 				this.Exec_by_paste();
		else if	(ctx.Match(k, Invk_exec_new_tab_by_paste)) 		this.Exec_new_tab_by_paste();
		else if	(ctx.Match(k, Invk_restore)) 					this.Restore();
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String Invk_focus = "focus", Invk_exec_by_paste = "exec_by_paste", Invk_exec_new_tab_by_paste = "exec_new_tab_by_paste", Invk_restore = "restore";
	public static final String Invk_exec = "exec";
}
