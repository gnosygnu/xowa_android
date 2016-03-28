package gplx.xowa.addons.searchs.searchers.cbks; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.core.js.*;
import gplx.xowa.addons.searchs.searchers.*; import gplx.xowa.addons.searchs.searchers.rslts.*;
public class Srch_rslt_cbk__suggest_box implements Srch_rslt_cbk, GfoInvkAble {
	private final    Js_wtr js_wtr = new Js_wtr();
	private final    Xoae_app app;
	private final    byte[] cbk_func;
	private final    byte[] search_raw;
	public Srch_rslt_cbk__suggest_box(Xoae_app app, byte[] cbk_func, byte[] search_raw) {
		this.app = app; this.cbk_func = cbk_func;
		this.search_raw = search_raw;
	}
	public void On_cancel() {}
	public void On_rslts_found(Srch_search_qry qry, Srch_rslt_list rslts_list, int rslts_bgn, int rslts_end) {
		if (!rslts_list.Rslts_are_enough && !rslts_list.Rslts_are_done) return;
		js_wtr.Func_init(cbk_func);
		js_wtr.Prm_bry(search_raw);
		js_wtr.Prm_spr();
		js_wtr.Ary_init();
		int rslts_len = rslts_list.Len();
		for (int i = 0; i < qry.Slab_end; i++) {
			if (i >= rslts_len) break;	// rslts_end will overshoot actual rslts_len; check for out of bounds and exit; EX: default suggest will have rslts_end of 25, but "earth time" will retrieve 15 results
			Srch_rslt_row row = rslts_list.Get_at(i);
			js_wtr.Ary_bry(row.Page_ttl.Full_txt_w_ttl_case());
			js_wtr.Ary_bry(row.Page_ttl_display(Bool_.Y));
		}
		js_wtr.Ary_term();
		js_wtr.Func_term();
		GfoInvkAble_.InvkCmd(app.Gui_mgr().Kit().New_cmd_sync(this), Srch_rslt_cbk__suggest_box.Invk__notify);
	}
	private void Notify() {
		app.Gui_mgr().Browser_win().Active_html_box().Html_js_eval_script(js_wtr.To_str_and_clear());
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__notify)) Notify();
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	public static final String Invk__notify = "notify";
}		
