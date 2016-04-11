package gplx.xowa.addons.apps.searchs.searchers.cbks; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*; import gplx.xowa.addons.apps.searchs.*; import gplx.xowa.addons.apps.searchs.searchers.*;
import gplx.gfui.*;
import gplx.xowa.addons.apps.searchs.searchers.rslts.*;
import gplx.xowa.apps.apis.xowa.addons.searchs.*;
public class Srch_rslt_cbk__url_bar implements Srch_rslt_cbk, GfoInvkAble {
	private final    Xoae_app app;
	private final    GfuiComboBox url_bar;
	private final    Xoapi_url_bar url_bar_api;
	private String[] cbo_ary;
	private boolean rslts_finished;
	private int rslts_in_this_pass;
	private boolean rslts_shown = false;
	public Srch_rslt_cbk__url_bar(Xoae_app app, GfuiComboBox url_bar, Xoapi_url_bar url_bar_api) {
		this.app = app; this.url_bar = url_bar; this.url_bar_api = url_bar_api;
	}
	public void On_cancel() {}
	public void On_rslts_found(Srch_search_qry qry, Srch_rslt_list rslts_list, int rslts_bgn, int rslts_end) {
		int rslts_len = rslts_list.Len();
		this.rslts_finished = rslts_list.Rslts_are_enough || rslts_list.Rslts_are_done;

		// get # of items for drop-down; note special logic to reduce blinking
		rslts_in_this_pass = rslts_end - rslts_bgn;
		if (	rslts_in_this_pass == 0		// no new results; 
			&&	rslts_bgn != 0				// if first one, still update; blanks out results from previous try;
			&&	!rslts_finished)			// if rslts_finished, still update to force cbo to "shrink"
			return;							// exit now else will "blink" when refreshing;
		int max_rslts = url_bar_api.Max_results();
		int cbo_len = max_rslts;			// force cbo_len to be max_rslts; reduces "blinking" when typing by keeping visible area to same size
		if (rslts_list.Rslts_are_done) {	// "shrink" cbo_len to rslts_len; EX: 10 wanted; 2 returned; shrink to 2 rows;
			cbo_len = rslts_len;
		}

		// fill cbo_ary with rslts from search, while "blanking" out rest
		this.cbo_ary = new String[cbo_len];
		for (int i = 0; i < cbo_len; ++i) {
			String cbo_itm = "";
			if (i >= max_rslts) break;
			if (i < rslts_len) {
				Srch_rslt_row rslt = rslts_list.Get_at(i);
				cbo_itm = String_.new_u8(rslt.Page_ttl_display(Bool_.N));
			}
			cbo_ary[i] = cbo_itm;
		}

		GfoInvkAble_.InvkCmd(app.Gui_mgr().Kit().New_cmd_sync(this), Srch_rslt_cbk__url_bar.Invk__items__update); // NOTE: needs to be sync, b/c page_wkr and link_wkr must execute in order; EX:"Portal:Science" does not show; DATE:2016-03-24
	}
	private void Items__update() {
		url_bar.Items__update(cbo_ary);
		if (!url_bar.List_visible()							// rslt_list not visible
			&& !rslts_shown									// auto-dropdown hasn't happened yet
			&& (rslts_in_this_pass > 0 || rslts_finished)	// at least 1 rslt, or search done
			) {
			rslts_shown = true;	// only auto-show dropdown once; allows user to close drop-down and not have it continually flashing
			url_bar.List_visible_(Bool_.Y);
		}
		Xoa_app_.Usr_dlg().Prog_none("", "", "");
		if (rslts_finished) {
			if (cbo_ary.length == 0)
				url_bar.List_visible_(Bool_.N);
			else
				url_bar.Items__size_to_fit(cbo_ary.length);
		}
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__items__update))			Items__update();
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}	private static final String Invk__items__update = "items__update";
}		
