package gplx.xowa.addons.searchs.specials; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.core.threads.*;
import gplx.xowa.wikis.*; import gplx.xowa.wikis.domains.*; import gplx.xowa.files.gui.*; import gplx.xowa.guis.views.*;
import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.specials.*; import gplx.xowa.addons.searchs.htmls.*; import gplx.xowa.addons.searchs.searchers.*; import gplx.xowa.addons.searchs.searchers.rslts.*;
public class Srch_special_cmd implements GfoInvkAble, Cancelable, Srch_rslt_cbk, Xog_tab_close_lnr {
	private final Srch_special_searcher mgr; private final Srch_qry qry;
	private final Xoae_page page; private final Xog_tab_close_mgr tab_close_mgr; private final Xog_js_wkr js_wkr;
	private final boolean async; private Srch_rslt_cbk__html rslt_cbk; 
	private final Srch_rslt_list cur_rslts = new Srch_rslt_list();
	public Srch_special_cmd(Srch_special_searcher mgr, Srch_qry qry, Xow_wiki wiki, Xoae_page page, Xog_tab_close_mgr tab_close_mgr, Xog_js_wkr js_wkr, byte[] key, Srch_rslt_list rslts_list) {
		this.mgr = mgr; this.qry = qry; this.wiki = wiki; this.page = page; this.tab_close_mgr = tab_close_mgr; this.js_wkr = js_wkr;
		this.key = key; this.rslts_list = rslts_list;
		this.async = wiki.App().Mode().Tid_is_gui() && qry.Async_db;			
	}
	public final byte[] key;
	public final Xow_wiki wiki;
	public final Srch_rslt_list rslts_list;
	public boolean Canceled() {return canceled;} private boolean canceled;
	public void Cancel() {
		Xoa_app_.Usr_dlg().Prog_many("", "", "search canceled: key=~{0}", key);
		canceled = true;
		this.Hide_cancel_btn();
	}
	public boolean Search() {
		boolean rv = false, fill_from_cache = true;
		if (!rslts_list.Done() && (qry.Slab_end > rslts_list.Len())) {
			if (async) {
				fill_from_cache = false; // NOTE: do not retrieve cached results to page, else ui_async cmd will add out of order; DATE:2015-04-24
				if (rslt_cbk == null) rslt_cbk = new Srch_rslt_cbk__html(this, new Srch_html_row_bldr(new gplx.xowa.htmls.core.htmls.utls.Xoh_lnki_bldr(wiki.App(), wiki.App().Html__href_wtr())), js_wkr, qry.Slab_len, wiki.Domain_bry());
				Thread_adp_.invk_(gplx.xowa.apps.Xoa_thread_.Key_special_search_db, this, Invk_search_db).Start();
			}
			else
				Search_db();
			rv = true;
		}
		if (fill_from_cache) {
			qry.Slab_idx_max = rslts_list.Len() / qry.Slab_len;
		}
		return rv;
	}
	public void Search_db() {
		tab_close_mgr.Add(this);
		if (async) {
			while (!page.Html_data().Mode_wtxt_shown())	// NOTE:must check for wtxt_shown else async can happen first, and then be overwritten by wtxt; DATE:2015-04-26
				Thread_adp_.Sleep(100);
			int slab_bgn = qry.Slab_bgn;
			int rslts_len = rslts_list.Len();
			for (int i = 0; i < qry.Slab_len; ++i) {
				if (i + slab_bgn >= rslts_len) break;
				rslt_cbk.Set(i, rslts_list.Get_at(i + slab_bgn));
			}
		}
		cur_rslts.Clear();
		Srch_search_mgr search_mgr = Srch_search_addon.Get(wiki).Search_mgr();
		search_mgr.Search(rslts_list, this, this, wiki, qry);
		rslts_list.Sort();

		mgr.Search__done(this);
		if (this.Canceled()) return; 	// NOTE: must check else throws SWT exception
		this.Hide_cancel_btn();
		if (rslts_list.Done())
			qry.Slab_idx_max = rslts_list.Len() / qry.Slab_len;
		Xoa_app_.Usr_dlg().Prog_many("", "", "");
	}
	private void Hide_cancel_btn()			{Thread_adp_.invk_(gplx.xowa.apps.Xoa_thread_.Key_special_search_cancel, this, Invk_hide_cancel).Start();}
	private void Hide_cancel_btn_async()	{js_wkr.Html_atr_set("xowa_cancel_" + wiki.Domain_str(), "style", "display:none;");}
	public void On_rslt_found(Srch_rslt_row rslt_row) {
		cur_rslts.Add(rslt_row);
	}
	public void On_rdr_done(boolean last_rdr) {
		cur_rslts.Sort();
		int cur_rslts_len = cur_rslts.Len();
		int rslts_list_len = rslts_list.Len();
		for (int i = 0; i < cur_rslts_len; ++i) {
			if ((rslts_list_len - cur_rslts_len + i) < qry.Slab_bgn)
				continue;
			Srch_rslt_row row = cur_rslts.Get_at(i);
			rslt_cbk.On_rslt_found(row);
		}
		cur_rslts.Clear();
	}
	public boolean When_close(Xog_tab_itm tab, Xoa_url url) {
		if (url != Xoa_url.Null) {	// not called by close_tab (Ctrl+W)
			byte[] cancel_arg = url.Qargs_mgr().Get_val_bry_or(Srch_qarg_mgr.Bry__cancel, null);
			if (cancel_arg != null)	return true; // cancel arg exists; assume tab is not being closed; note that cancel will be processed by Xows_page__special; DATE:2015-04-30
		}
		this.Cancel();
		return true;
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_search_db))		Search_db();
		else if	(ctx.Match(k, Invk_hide_cancel))	Hide_cancel_btn_async();
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String Invk_search_db = "search_db", Invk_hide_cancel = "hide_cancel";
}
/*
NOTE:show_existing. code needed to show A1
EX: search="A*": "A" has 400 words; "A1" has 1;
. search 1-20 returns 20 words for A and 1 word for A1.
.. the 1st A word has a len of 999 and the 20th A word has a length of 900;
.. A1 has a length of 799
. search 21-40 returns 20 words for A
.. the 21st word has a len of 899 and the 40th has a len of 800
.. A1 should show up briefly, and then get pushed off screen by 21-40
. search 61-40 returns 20 words for A
.. A1 must show up
*/
