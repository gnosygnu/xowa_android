package gplx.xowa.addons.searchs.suggests; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.core.js.*;
import gplx.xowa.addons.searchs.searchers.rslts.*;
class Srch_suggest_cbk implements Srch_rslt_cbk, GfoInvkAble {
	private final List_adp found = List_adp_.new_();
	private final Js_wtr js_wtr = new Js_wtr();
	private final Xoae_app app;
	private final byte[] cbk_func;
	private final byte[] search_raw;
	private final int rslts_max;
	private boolean done = false;
	public Srch_suggest_cbk(Xoae_app app, byte[] cbk_func, byte[] search_raw, int rslts_max) {
		this.app = app; this.cbk_func = cbk_func;
		this.search_raw = search_raw;
		this.rslts_max = rslts_max;
	}
	public void On_rslt_found(Srch_rslt_row rslt) {found.Add(rslt);}
	public void On_rdr_done(boolean last_rdr) {// EX: receiveSuggestions('search_word', ['result_1', 'result_2']);
		if (done) return;
		int len = found.Count();
		if (!last_rdr) return;
		done = true;
		found.Sort();
		js_wtr.Func_init(cbk_func);
		js_wtr.Prm_bry(search_raw);
		js_wtr.Prm_spr();
		js_wtr.Ary_init();
		for (int i = 0; i < len; i++) {
			if (i > rslts_max) break;
			Srch_rslt_row row = (Srch_rslt_row)found.Get_at(i);
			js_wtr.Ary_bry(Xoa_ttl.Replace_unders(row.Page_ttl.Full_db()));
			js_wtr.Ary_bry(row.Page_ttl_display);
		}
		js_wtr.Ary_term();
		js_wtr.Func_term();
		GfoInvkAble_.InvkCmd(app.Gui_mgr().Kit().New_cmd_sync(this), Srch_suggest_cbk.Invk__notify);
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
