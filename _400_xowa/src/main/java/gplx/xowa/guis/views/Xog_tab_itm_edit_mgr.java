package gplx.xowa.guis.views; import gplx.*; import gplx.xowa.*; import gplx.xowa.guis.*;
import gplx.gfui.*; import gplx.gfui.controls.standards.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.htmls.*; import gplx.xowa.wikis.pages.*;
import gplx.xowa.wikis.nss.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Xog_tab_itm_edit_mgr {
	public static void Save(Xog_tab_itm tab, boolean quick_save) {
		if (tab.View_mode() != Xopg_page_.Tid_edit) return;	// exit if not edit; handles ctrl+s being pressed in read/html modes
		Xoae_page page = tab.Page(); Xowe_wiki wiki = tab.Wiki(); Xog_win_itm win_itm = tab.Tab_mgr().Win();
		byte[] new_text = Get_new_text(tab);
		if (page.Edit_mode() == Xoa_page_.Edit_mode_create) {
			int page_id = wiki.Db_mgr().Save_mgr().Data_create(page.Ttl(), new_text);
			page.Db().Page().Id_(page_id);
			page.Edit_mode_update_();	// set to update so that next save does not try to create
		}
		else {
			wiki.Db_mgr().Save_mgr().Data_update(page, new_text);
		}
		Invalidate(wiki);
		page.Db().Text().Text_bry_(new_text);
		wiki.Parser_mgr().Parse(page, true);			// refresh html
		if (wiki.Html__hdump_enabled()) wiki.Html__hdump_mgr().Save_mgr().Save(page);	// must go after wiki.Parse
		win_itm.Usr_dlg().Prog_one("", "", "saved page ~{0}", String_.new_u8(page.Ttl().Full_txt_raw()));	// NOTE: show message after Parse, b/c Parse will flash "Loading page"; DATE:2014-05-17
		if (!quick_save) {							// full_save; save page and go to read mode
			page.Html_data().Edit_preview_(Bry_.Empty);
			Xoae_page stack_page = tab.History_mgr().Cur_page(wiki);		// NOTE: must be to CurPage() else changes will be lost when going Bwd,Fwd
			stack_page.Db().Text().Text_bry_(page.Db().Text().Text_bry());	// NOTE: overwrite with "saved" changes
			stack_page.Wikie().Parser_mgr().Parse(page, true);				// NOTE: must reparse page if (a) Edit -> Read; or (b) "Options" save
			win_itm.Page__mode_(Xopg_page_.Tid_read);
			win_itm.Page__async__bgn(tab);
		}
//			wiki.Db_mgr().Hdump_mgr().Save(page);
	}
	public static void Preview(Xog_tab_itm tab) {
		if (tab.View_mode() != Xopg_page_.Tid_edit) return;	// exit if not edit; handles preview somehow being called?
		Xoae_page page = tab.Page(); Xowe_wiki wiki = tab.Wiki(); Xog_win_itm win_itm = tab.Tab_mgr().Win();
		Xog_html_itm html_itm = tab.Html_itm();

		byte[] new_text = Get_new_text(tab);
		Xoae_page new_page = Xoae_page.New(wiki, page.Ttl());
		new_page.Db().Page().Id_(page.Db().Page().Id());	// NOTE: page_id needed for sqlite (was not needed for xdat)
		new_page.Db().Text().Text_bry_(new_text);
		wiki.Parser_mgr().Parse(new_page, true);			// refresh html
		tab.Page_(new_page); new_page.Tab_data().Tab_(tab);			// replace old page with new_page; DATE:2014-10-09

		Bry_bfr tmp_bfr = wiki.Utl__bfr_mkr().Get_m001();
		Xoh_page_wtr_wkr wkr = wiki.Html_mgr().Page_wtr_mgr().Wkr(Xopg_page_.Tid_read);
		wkr.Write_body(tmp_bfr, wiki.Parser_mgr().Ctx(), Xoh_wtr_ctx.Basic, new_page);
		byte[] new_html = tmp_bfr.To_bry_and_rls();
		new_page.Html_data().Edit_preview_(new_html);

		Invalidate(wiki);
		win_itm.Page__mode_(Xopg_page_.Tid_edit);
		html_itm.Scroll_page_by_id_gui(Xog_html_itm.Elem_id__first_heading);// NOTE: was originally directly; changed to call on thread; DATE:2014-05-03
		win_itm.Page__async__bgn(tab);	// NOTE: needed to show images during preview; DATE:2014-06-21
	}
	public static void Rename(Xog_tab_itm tab) {
		if (tab.View_mode() != Xopg_page_.Tid_edit) return;	// exit if not edit; handles ctrl+r being pressed
		Xoae_page page = tab.Page(); Xowe_wiki wiki = tab.Wiki(); Xog_win_itm win_itm = tab.Tab_mgr().Win();
		if (Bry_.Eq(page.Ttl().Page_db(), wiki.Props().Main_page())) {
			win_itm.Usr_dlg().Warn_many("", "", "The Main Page cannot be renamed");
			win_itm.Kit().Ask_ok("", "", "The Main Page cannot be renamed");
			return;
		}
		byte[] new_text = Bry_.new_u8(tab.Html_itm().Get_elem_value(Xog_html_itm.Elem_id__xowa_edit_rename_box));
		if (Bry_.Len_eq_0(new_text)) return;		// no ttl given; exit
		new_text = Xoa_ttl.Replace_spaces(new_text);	// ttls cannot have spaces; only underscores
		Xoa_ttl new_ttl = Xoa_ttl.Parse(wiki, new_text);
		int new_ns_id = new_ttl.Ns().Id();
		if (new_ns_id != Xow_ns_.Tid__main) {
			win_itm.Usr_dlg().Warn_many("", "", "The new page name must remain in the same name"+"space");
			return;
		}
		wiki.Db_mgr().Save_mgr().Data_rename(page, new_ns_id, new_text);
		page.Ttl_(Xoa_ttl.Parse(wiki, Bry_.Add(page.Ttl().Ns().Name_db_w_colon(), new_text)));
		win_itm.Page__mode_(Xopg_page_.Tid_read);
		win_itm.Usr_dlg().Prog_one("", "", "renamed page to {0}", String_.new_u8(page.Ttl().Full_txt_raw()));
	}
	public static void Focus(Xog_win_itm win, String elem_focus_id) {
		Gfui_html html_box = win.Active_html_box();
		html_box.Focus();
		html_box.Html_js_eval_proc_as_str(Xog_js_procs.Doc__elem_focus, elem_focus_id);
	}
	public static void Debug(Xog_win_itm win, byte view_tid) {
		Xog_tab_itm tab = win.Tab_mgr().Active_tab(); Xoae_page page = tab.Page();
		Xowe_wiki wiki = tab.Wiki(); Xop_ctx ctx = wiki.Parser_mgr().Ctx();
		ctx.Defn_trace().Clear(); // TODO_OLD: move_me
		ctx.Defn_trace_(Xot_defn_trace_dbg.Instance);
		Xoa_ttl ttl = page.Ttl();
		Xoae_page new_page = Xoae_page.New(wiki, ttl);
		byte[] data = tab.Html_itm().Get_elem_value_for_edit_box_as_bry();
		new_page.Db().Text().Text_bry_(data);
		wiki.Parser_mgr().Parse(new_page, true);
		Bry_bfr bfr = win.App().Utl__bfr_mkr().Get_m001();
		bfr.Add(new_page.Root().Root_src());
		wiki.Parser_mgr().Ctx().Defn_trace().Print(data, bfr);
		new_page.Db().Text().Text_bry_(bfr.To_bry_and_rls());
		byte old = tab.View_mode();
		tab.View_mode_(view_tid);
		Xog_tab_itm_read_mgr.Show_page(tab, new_page, false);
		tab.History_mgr().Add(new_page);
		tab.View_mode_(old);
	}
	private static void Invalidate(Xowe_wiki wiki) {// invalidate everything on updates; especially needed for page transclusion; {{/my_subpage}} DATE:2014-04-10
		wiki.Parser_mgr().Scrib().Core().Term();
		wiki.Cache_mgr().Free_mem_all();
	}
	private static byte[] Get_new_text(Xog_tab_itm tab) {
		byte[] rv = tab.Html_itm().Get_elem_value_for_edit_box_as_bry();
		rv = Bry_.Trim(rv, 0, rv.length, false, true, Bry_.Trim_ary_ws);	// MW: trim all trailing ws; REF:EditPage.php!safeUnicodeInput; DATE:2014-04-25
		return rv;
	}
}
