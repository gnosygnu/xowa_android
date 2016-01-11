package gplx.xowa.guis.views; import gplx.*; import gplx.xowa.*; import gplx.xowa.guis.*;
import gplx.gfui.*; import gplx.xowa.apps.cfgs.old.*; import gplx.xowa.apps.apis.xowa.gui.browsers.*; import gplx.xowa.specials.*;
import gplx.xowa.apps.urls.*;
public class Xog_tab_mgr implements GfoEvObj {
	private Ordered_hash tab_regy = Ordered_hash_.New(); private int tab_uid = 0;
	public Xog_tab_mgr(Xog_win_itm win) {
		this.win = win;
		ev_mgr = GfoEvMgr.new_(this);
	}
	public GfoEvMgr EvMgr() {return ev_mgr;} private GfoEvMgr ev_mgr;
	public Xog_win_itm Win() {return win;} private Xog_win_itm win;
	public Gfui_tab_mgr Tab_mgr() {return tab_mgr;} private Gfui_tab_mgr tab_mgr;
	public byte Html_load_tid() {return html_load_tid;} private byte html_load_tid;
	public boolean Html_load_tid__url() {return html_load_tid == Gxw_html_load_tid_.Tid_url;}
	public void Init_by_kit(Gfui_kit kit) {
		tab_mgr = kit.New_tab_mgr("xowa.tab_mgr", win.Win_box());
		active_tab = Xog_tab_itm_.Null;
		tab_mgr.Btns_selected_color_(ColorAdp_.White);
		tab_mgr.Btns_unselected_color_(ColorAdp_.LightGray);
		GfoEvMgr_.SubSame_many(tab_mgr, this, Gfui_tab_mgr.Evt_tab_selected, Gfui_tab_mgr.Evt_tab_closed, Gfui_tab_mgr.Evt_tab_switched);
		Xocfg_tab_btn_mgr btn_mgr = win.App().Cfg_regy().App().Gui_mgr().Tab_mgr().Btn_mgr();
		Btns_place_on_top_(btn_mgr.Place_on_top());
		Btns_curved_(btn_mgr.Curved());
		Btns_close_visible_(btn_mgr.Close_visible());
		Btns_unselected_close_visible_(btn_mgr.Unselected_close_visible());
		Btns_height_(btn_mgr.Height());
		GfoEvMgr_.SubSame_many(btn_mgr, this
		, Xocfg_tab_btn_mgr.Evt_place_on_top_changed, Xocfg_tab_btn_mgr.Evt_height_changed, Xocfg_tab_btn_mgr.Evt_curved_changed
		, Xocfg_tab_btn_mgr.Evt_close_visible_changed, Xocfg_tab_btn_mgr.Evt_unselected_close_visible_changed
		, Xocfg_tab_btn_mgr.Evt_text_min_chars_changed, Xocfg_tab_btn_mgr.Evt_text_max_chars_changed
		, Xocfg_tab_btn_mgr.Evt_hide_if_one_changed
		);
		html_load_tid = win.App().Api_root().Gui().Browser().Html().Load_tid();
		GfoEvMgr_.SubSame_many(win.App().Api_root().Gui().Browser().Html(), this
		, Xoapi_html_box.Evt_load_tid_changed
		);
	}
	public Xog_tab_itm Active_tab() {return active_tab;} private Xog_tab_itm active_tab;
	public Xog_tab_itm Active_tab_assert() {
		if (active_tab == Xog_tab_itm_.Null) this.Tabs_new_dflt(true);
		return active_tab;
	}
	public boolean Active_tab_is_null() {return active_tab == Xog_tab_itm_.Null;}
	private void Btns_place_on_top_(boolean v) {tab_mgr.Btns_place_on_top_(v);}
	private void Btns_curved_(boolean v) {tab_mgr.Btns_curved_(v);}
	private void Btns_height_(int v) {tab_mgr.Btns_height_(v);}
	private void Btns_close_visible_(boolean v) {tab_mgr.Btns_close_visible_(v);}
	private void Btns_unselected_close_visible_(boolean v) {tab_mgr.Btns_unselected_close_visible_(v);}
	private void Btns_text_recalc() {
		int len = this.Tabs_len();
		for (int i = 0; i < len; i++) {
			Xog_tab_itm tab_itm = this.Tabs_get_at(i);
			tab_itm.Tab_name_();
		}
	}
	public int Tabs_len() {return tab_regy.Count();}
	public Xog_tab_itm Tabs_new_init(Xowe_wiki wiki, Xoae_page page) {return this.Tabs_new(true, true, wiki, page);}
	public Xog_tab_itm Tabs_get_at(int i) {return (Xog_tab_itm)tab_regy.Get_at(i);}
	public Xog_tab_itm Tabs_new_dflt() {return Tabs_new_dflt(false);}
	public Xog_tab_itm Tabs_new_dflt(boolean focus) {
		boolean active_tab_is_null = this.Active_tab_is_null();
		Xowe_wiki cur_wiki = active_tab_is_null ? win.App().Usere().Wiki() : active_tab.Wiki();
		Xoa_ttl ttl = Xoa_ttl.parse(cur_wiki, Xows_special_meta_.Itm__default_tab.Ttl_bry());
		Xoa_url url = cur_wiki.Utl__url_parser().Parse_by_urlbar_or_null(ttl.Full_db_as_str()); if (url == null) throw Err_.new_("url", "invalid url", "url", url);
		Xog_tab_itm rv = Tabs_new(focus, active_tab_is_null, cur_wiki, Xoae_page.New(cur_wiki, ttl));
		rv.Page_update_ui();
		rv.Show_url_bgn(url);
		return rv;
	}
	private Xog_tab_itm Tabs_new(boolean focus, boolean active_tab_is_null, Xowe_wiki wiki, Xoae_page page) {
		String tab_key = "tab_" + Int_.To_str(tab_uid++); int tab_idx = tab_regy.Count();
		Gfui_tab_itm_data tab_data = new Gfui_tab_itm_data(tab_key, tab_idx);
		Xog_tab_itm rv = new Xog_tab_itm(this, tab_data, wiki, page);
		Gfui_tab_itm tab_box = tab_mgr.Tabs_add(tab_data);
		rv.Make_html_box(tab_uid, tab_box, win, tab_mgr);
		tab_box.Subs_add(rv.Html_itm().Html_box());
		tab_regy.Add(tab_key, rv);
		if (	focus
			||	active_tab_is_null // NOTE: must select 1st tab, else nothing will show in tab box
			) {
			tab_mgr.Tabs_select_by_idx(rv.Tab_idx());
			active_tab = rv;
		}
		Tabs_hide_if_one_chk();
		return rv;
	}
	public void Tabs_new_dupe(boolean focus) {
		if (this.Active_tab_is_null()) return;
		String url = active_tab.Page().Url().To_str();
		Tabs_new_dflt(focus);
		win.Page__navigate_by_url_bar(url);
	}
	public void Tabs_javascript_enabled_(boolean v) {
		int len = tab_regy.Count();
		for (int i = 0; i < len; i++) {
			Xog_tab_itm tab = Tabs_get_by_idx_or_warn(i);
			tab.Html_itm().Js_enabled_(v);
		}
	}
	private void Tabs_selected(String key) {
		Xog_tab_itm tab = Tabs_get_by_key_or_warn(key); if (tab == null) return;
		active_tab = tab;
		Xoae_page page = tab.Page();
		Xog_tab_itm_read_mgr.Update_selected_tab(win, page.Url(), page.Ttl());
		tab.Html_itm().Tab_selected(page);
	}
	public void Tabs_close_cur() {
		if (this.Active_tab_is_null()) return;
		Tabs__pub_close(active_tab);
		tab_mgr.Tabs_close_by_idx(active_tab.Tab_idx());
		Xog_tab_itm cur_tab = this.Active_tab();			// get new current tab for line below
		if (cur_tab != null) cur_tab.Html_box().Focus();	// NOTE: needed to focus tab box else tab button will be focused; DATE:2014-07-13
	}
	public void Tabs_close_others() {this.Tabs_close_to_bgn(); this.Tabs_close_to_end();}
	public void Tabs_close_to_bgn() {if (Active_tab_is_null()) return; Tabs_close_rng(0							, active_tab.Tab_idx());}
	public void Tabs_close_to_end() {if (Active_tab_is_null()) return; Tabs_close_rng(active_tab.Tab_idx() + 1	, tab_regy.Count());}
	public void Tabs_close_rng(int bgn, int end) {
		for (int i = bgn; i < end; i++) {
			Xog_tab_itm tab = Tabs_get_at(bgn);
			if (!Tabs__pub_close(tab)) return;
		}
		for (int i = bgn; i < end; i++)
			tab_mgr.Tabs_close_by_idx(bgn);	// NOTE: close at bgn, not at i, b/c each close will remove a tab from collection
	}
	public boolean Tabs__pub_close_all() {return Tabs__pub_close_rng(0, this.Tabs_len());}
	public boolean Tabs__pub_close_rng(int bgn, int end) {
		boolean rv = true;
		for (int i = bgn; i < end; i++) {
			Xog_tab_itm tab = Tabs_get_at(i);
			boolean close_allowed = Tabs__pub_close(tab);
			if (!close_allowed) rv = false;
		}
		return rv;
	}
	public boolean Tabs__pub_close(Xog_tab_itm tab) {
		return tab.Page().Tab_data().Close_mgr().When_close(tab, Xoa_url.Null);
	}
	public void Tabs_close_undo() {
		if (closed_undo_list.Count() == 0) return;
		String url = (String)List_adp_.Pop(closed_undo_list);
		Tabs_new_dflt(true);
		win.Page__navigate_by_url_bar(url);
	}
	private List_adp closed_undo_list = List_adp_.new_();
	private void Tabs_closed(String key) {
		Xog_tab_itm itm = Tabs_get_by_key_or_warn(key); if (itm == null) return;
		itm.Html_box().Html_dispose();
		closed_undo_list.Add(itm.Page().Url().To_str());
		tab_regy.Del(key);
		if (tab_regy.Count() == 0) {
			active_tab = Xog_tab_itm_.Null;
			Xog_tab_itm_read_mgr.Update_selected_tab_blank(win);
		}
		else
			Tabs_recalc_idx();
		Tabs_hide_if_one_chk();
	}
	private Xog_tab_itm Tabs_get_by_key_or_warn(String key) {
		Xog_tab_itm rv = (Xog_tab_itm)tab_regy.Get_by(key); if (rv == null) win.App().Usr_dlg().Warn_many("", "", "tab.selected could not find tab; key={0}", key);
		return rv;
	}
	private Xog_tab_itm Tabs_get_by_idx_or_warn(int idx) {
		Xog_tab_itm rv = (Xog_tab_itm)tab_regy.Get_at(idx); if (rv == null) win.App().Usr_dlg().Warn_many("", "", "tab.selected could not find tab; idx={0}", idx);
		return rv;
	}
	private void Tabs_recalc_idx() {
		int len = tab_regy.Count();
		for (int i = 0; i < len; i++) {
			Xog_tab_itm itm = Tabs_get_by_idx_or_warn(i);
			itm.Tab_idx_(i);
		}
	}
	public void Tabs_select(boolean fwd) {
		if (this.Active_tab_is_null()) return;
		int new_idx = TabBox_.Cycle(fwd, active_tab.Tab_idx(), tab_regy.Count());
		tab_mgr.Tabs_select_by_idx(new_idx);
	}
	public void Tabs_select_by_idx(int v) {
		if (v >= tab_regy.Count()) return;
		tab_mgr.Tabs_select_by_idx(v);
	}
	public void Tabs_move(boolean fwd) {
		if (this.Active_tab_is_null()) return;
		int src_idx = active_tab.Tab_idx();
		int trg_idx = TabBox_.Cycle(fwd, src_idx, tab_regy.Count());
		tab_mgr.Tabs_switch(src_idx, trg_idx);
	}
	private void Tabs_switched(String src_key, String trg_key) {
		Xog_tab_itm src_itm = Tabs_get_by_key_or_warn(src_key);
		Xog_tab_itm trg_itm = Tabs_get_by_key_or_warn(trg_key);
		src_itm.Switch_mem(trg_itm);
		active_tab = trg_itm;	// NOTE: src_itm initiated switch, but trg_itm is now active b/c everything in src_itm has now been reparented to trg_itm; DATE:2014-05-12
	}
	public void Tabs_new_link(boolean focus, String link) {
		if (String_.Len_eq_0(link)) {
			if (this.Active_tab_is_null()) return;
			link = active_tab.Html_itm().Html_selected_get_active_or_selection();
		}
		if (String_.Len_eq_0(link)) {win.App().Usr_dlg().Prog_many("", "", "no link or text selected"); return;}
		Tabs_new_link(link, focus);
	}
	public void Tabs_new_link(String link, boolean focus) {
		Xowe_wiki wiki = active_tab.Wiki();
		Xog_tab_itm new_tab = Tabs_new(focus, false, wiki, Xoae_page.New(wiki, active_tab.Page().Ttl()));	// NOTE: do not use ttl from link, else middle-clicking pages with anchors won't work; DATE:2015-05-03
		Xoa_url url = wiki.Utl__url_parser().Parse_by_urlbar_or_null(link);	if (url == null) return; // NOTE: link must be of form domain/wiki/page; DATE:2014-05-27			
		new_tab.Show_url_bgn(url);
		if (focus)
			tab_mgr.Tabs_select_by_idx(new_tab.Tab_idx());
	}
	private void Tabs_hide_if_one_chk() {
		gplx.xowa.apps.cfgs.old.Xocfg_tab_btn_mgr btn_mgr = win.App().Cfg_regy().App().Gui_mgr().Tab_mgr().Btn_mgr();
		if (!btn_mgr.Hide_if_one()) return;
		if (tab_regy.Count() > 1) {
			if (tab_mgr.Btns_height() != btn_mgr.Height())
				Btns_height_(btn_mgr.Height());
		}
		else {
			if (tab_mgr.Btns_height() != 0)
				Btns_height_(0);
		}
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_tabs_new_dflt__at_dflt__focus_y))						Tabs_new_dflt(Bool_.Y);
		else if	(ctx.Match(k, Invk_tabs_new_link__at_dflt__focus_n))						Tabs_new_link(Bool_.N, m.ReadStrOr("v", null));
		else if	(ctx.Match(k, Invk_tabs_new_link__at_dflt__focus_y))						Tabs_new_link(Bool_.Y, m.ReadStrOr("v", null));
		else if	(ctx.Match(k, Gfui_tab_mgr.Evt_tab_selected))								Tabs_selected(m.ReadStr("key"));
		else if	(ctx.Match(k, Gfui_tab_mgr.Evt_tab_closed))									Tabs_closed(m.ReadStr("key"));
		else if	(ctx.Match(k, Gfui_tab_mgr.Evt_tab_switched))								Tabs_switched(m.ReadStr("src"), m.ReadStr("trg"));
		else if	(ctx.Match(k, Invk_tabs_close_cur))											Tabs_close_cur();
		else if	(ctx.Match(k, Invk_tabs_select_bwd))										Tabs_select(Bool_.N);
		else if	(ctx.Match(k, Invk_tabs_select_fwd))										Tabs_select(Bool_.Y);
		else if	(ctx.Match(k, Invk_tabs_switch_cur_bwd))									Tabs_move(Bool_.N);
		else if	(ctx.Match(k, Invk_tabs_switch_cur_fwd))									Tabs_move(Bool_.Y);
		else if	(ctx.Match(k, Xocfg_tab_btn_mgr.Evt_place_on_top_changed))					Btns_place_on_top_(m.ReadBool("v"));
		else if	(ctx.Match(k, Xocfg_tab_btn_mgr.Evt_curved_changed))						Btns_curved_(m.ReadBool("v"));
		else if	(ctx.Match(k, Xocfg_tab_btn_mgr.Evt_height_changed))						Btns_height_(m.ReadInt("v"));
		else if	(ctx.Match(k, Xocfg_tab_btn_mgr.Evt_close_visible_changed))					Btns_close_visible_(m.ReadBool("v"));
		else if	(ctx.Match(k, Xocfg_tab_btn_mgr.Evt_unselected_close_visible_changed))		Btns_unselected_close_visible_(m.ReadBool("v"));
		else if	(ctx.Match(k, Xocfg_tab_btn_mgr.Evt_text_min_chars_changed))				Btns_text_recalc();
		else if	(ctx.Match(k, Xocfg_tab_btn_mgr.Evt_text_max_chars_changed))				Btns_text_recalc();
		else if	(ctx.Match(k, Xoapi_html_box.Evt_load_tid_changed))							html_load_tid = m.ReadByte("v");
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	public static final String
	  Invk_tabs_select_fwd		= "tabs_select_fwd"		, Invk_tabs_select_bwd = "tabs_select_bwd"
	, Invk_tabs_switch_cur_fwd	= "tabs_switch_cur_fwd"	, Invk_tabs_switch_cur_bwd = "tabs_switch_cur_bwd"
	, Invk_tabs_new_dflt__at_dflt__focus_y = "tabs_new_dflt__at_dflt__focus_y"
	, Invk_tabs_new_link__at_dflt__focus_n = "tabs_new_link__at_dflt__focus_n"
	, Invk_tabs_new_link__at_dflt__focus_y = "tabs_new_link__at_dflt__focus_y"
    , Invk_tabs_close_cur		= "tabs_close_cur"
	;
}
