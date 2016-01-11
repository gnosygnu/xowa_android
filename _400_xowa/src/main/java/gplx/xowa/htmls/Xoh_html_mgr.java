package gplx.xowa.htmls; import gplx.*; import gplx.xowa.*;
import gplx.xowa.htmls.core.htmls.tidy.*; import gplx.xowa.htmls.js.*; import gplx.xowa.htmls.skins.*;
import gplx.xowa.parsers.xndes.*;
public class Xoh_html_mgr implements GfoInvkAble {
	public Xoh_html_mgr(Xoae_app app) {
		js_cleaner = new Xoh_js_cleaner(app);
	}
	public void Init_by_app(Xoae_app app) {
		tidy_mgr.Init_by_app(app);
	}
	public Xop_xatr_whitelist_mgr Whitelist_mgr() {return whitelist_mgr;} private final Xop_xatr_whitelist_mgr whitelist_mgr = new Xop_xatr_whitelist_mgr().Ini();
	public Xoh_page_mgr Page_mgr() {return page_mgr;} private final Xoh_page_mgr page_mgr = new Xoh_page_mgr();
	public Xoh_tidy_mgr Tidy_mgr() {return tidy_mgr;} private final Xoh_tidy_mgr tidy_mgr = new Xoh_tidy_mgr();
	public Xoh_js_cleaner Js_cleaner() {return js_cleaner;} private final Xoh_js_cleaner js_cleaner;
	public Xoh_skin_mgr Skin_mgr() {return skin_mgr;} private final Xoh_skin_mgr skin_mgr = new Xoh_skin_mgr();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_page))	return page_mgr;
		else if	(ctx.Match(k, Invk_tidy))	return tidy_mgr;
		else if	(ctx.Match(k, Invk_skins))	return skin_mgr;
		else	return GfoInvkAble_.Rv_unhandled;
	}	private static final String Invk_page = "page", Invk_tidy = "tidy", Invk_skins = "skins";
}
