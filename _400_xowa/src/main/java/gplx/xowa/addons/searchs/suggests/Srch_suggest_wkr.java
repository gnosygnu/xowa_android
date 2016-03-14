package gplx.xowa.addons.searchs.suggests; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.xowa.addons.searchs.searchers.rslts.*;
class Srch_suggest_wkr {
	private final Srch_suggest_cfg cfg;
	private Srch_search_addon addon;
	private Srch_suggest_cmd cur_cmd;
	public Srch_suggest_wkr(Srch_suggest_cfg cfg) {this.cfg = cfg;}
	public void Cancel() {
		if (cur_cmd != null) cur_cmd.Cancel();
	}
	public void Search(Xowe_wiki wiki, byte[] search_raw, byte[] cbk_func) {
		if (	!cfg.Enabled()
			||	search_raw.length == 0
			||	(cur_cmd != null && Bry_.Eq(search_raw, cur_cmd.Search_raw))
			) return;
		if (addon == null) addon = Srch_search_addon.Get(wiki);
		this.Cancel();
		Srch_suggest_cbk cbk = new Srch_suggest_cbk(wiki.Appe(), cbk_func, search_raw, cfg.Rslts_max());
		this.cur_cmd = new Srch_suggest_cmd(addon, wiki, cbk, search_raw, cfg.Rslts_max());
		gplx.core.threads.Thread_adp_.invk_(gplx.xowa.apps.Xoa_thread_.Key_special_suggest, cur_cmd, Srch_suggest_cmd.Invk__search).Start();
	}
}
