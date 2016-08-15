package gplx.xowa.htmls.modules.popups; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.modules.*;
import gplx.core.brys.fmtrs.*;
import gplx.xowa.apps.apis.xowa.html.modules.*;
public class Xow_popup_html_mkr {
	private Xoae_app app; private Xowe_wiki wiki;
	public Bry_fmtr Fmtr_popup()		{return fmtr_popup;}			private Bry_fmtr fmtr_popup				= Bry_fmtr.keys_(Xoapi_popups.Dflt_html_fmtr_popup_keys);
	public Bry_fmtr Fmtr_viewed()		{return fmtr_viewed;}			private Bry_fmtr fmtr_viewed			= Bry_fmtr.keys_(Xoapi_popups.Dflt_html_fmtr_viewed_keys);
	public Bry_fmtr Fmtr_wiki()			{return fmtr_wiki;}				private Bry_fmtr fmtr_wiki				= Bry_fmtr.keys_(Xoapi_popups.Dflt_html_fmtr_wiki_keys);
	public Bry_fmtr Fmtr_next_sect()	{return fmtr_next_sect;}		private Bry_fmtr fmtr_next_sect			= Bry_fmtr.keys_(Xoapi_popups.Dflt_html_fmtr_next_sect_keys);
	public void Output_js_clean_(boolean v) {output_js_clean = v;} private boolean output_js_clean = true;
	public void Output_tidy_(boolean v) {output_tidy = v;} private boolean output_tidy = true;
	public void Ctor(Xoae_app app, Xowe_wiki wiki) {
		this.wiki = wiki; this.app = app;
		wiki.Eval_mgr().Eval_mgr_(fmtr_popup, fmtr_viewed, fmtr_wiki, fmtr_next_sect);
	}
	public byte[] Bld(Xowe_wiki cur_wiki, Xoae_page page, Xow_popup_itm popup_itm, Bry_bfr wrdx_bfr) {
		if (output_js_clean)	cur_wiki.Html_mgr().Js_cleaner().Clean_bfr(wiki, page.Ttl(), wrdx_bfr, 0);
		if (output_tidy)		cur_wiki.Html_mgr().Tidy_mgr().Exec_tidy(wrdx_bfr, Bool_.Y, page.Url_bry_safe());
		byte[] hdom_bry = wrdx_bfr.To_bry_and_clear();
		String page_url = wrdx_bfr.Add(page.Wiki().Domain_bry()).Add(gplx.xowa.htmls.hrefs.Xoh_href_.Bry__wiki).Add(gplx.langs.htmls.encoders.Gfo_url_encoder_.Href
			.Encode(page.Ttl().Full_db()))	// NOTE: was page.Url().Raw(), but that doesn't work for Special:Search; PAGE:en.w:Earth and "Quotations"; DATE:2014-06-29
			.To_str_and_clear()
		;
		fmtr_popup.Bld_bfr_many
		( wrdx_bfr
		, hdom_bry
		, wiki.Lang().Dir_ltr_bry()
		, page_url
		, String_.new_u8(page.Ttl().Full_txt_w_ttl_case())
		, popup_itm.Popup_id()
		, Xow_popup_html_bldr_.Bld_fmtr_wiki(fmtr_wiki, wrdx_bfr, cur_wiki.Domain_bry(), page.Wiki().Domain_bry()) // NOTE: use cur_wiki, not page_wiki; DATE:2014-06-28
		, gplx.core.ios.Io_size_.To_str(page.Db().Text().Text_bry().length)
		, page.Db().Page().Modified_on().XtoStr_fmt_yyyy_MM_dd_HH_mm_ss()
		, Xow_popup_html_bldr_.Bld_fmtr_viewed(fmtr_viewed, app, wiki, wrdx_bfr, page.Ttl())
		, app.Fsys_mgr().Root_dir().To_http_file_bry()
		);
		return wrdx_bfr.To_bry_and_clear();
	}
}
class Xow_popup_html_bldr_ {
	public static byte[] Bld_fmtr_wiki(Bry_fmtr fmtr, Bry_bfr wrdx_bfr, byte[] wiki_domain, byte[] page_domain) {
		return Bry_.Eq(wiki_domain, page_domain)
			? Bry_.Empty	// same domain; return "";
			: fmtr.Bld_bry_many(wrdx_bfr, page_domain);
	}
	public static byte[] Bld_fmtr_viewed(Bry_fmtr fmtr, Xoae_app app, Xowe_wiki wiki, Bry_bfr wrdx_bfr, Xoa_ttl ttl) {
		byte[] view_time_item = Bry_.Empty;
		gplx.xowa.users.history.Xou_history_itm history_itm = app.Usere().History_mgr().Get_or_null(wiki.Domain_bry(), ttl.Full_txt_w_ttl_case());
		if (history_itm != null)
			view_time_item = fmtr.Bld_bry_many(wrdx_bfr, history_itm.View_end().XtoStr_fmt_yyyy_MM_dd_HH_mm_ss());
		return view_time_item;
	}
}
