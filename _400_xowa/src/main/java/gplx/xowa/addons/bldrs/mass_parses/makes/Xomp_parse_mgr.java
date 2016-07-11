package gplx.xowa.addons.bldrs.mass_parses.makes; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.mass_parses.*;
import gplx.core.threads.*;
import gplx.xowa.langs.*;
class Xomp_parse_mgr {
	private final    Xomp_page_pool page_pool = new Xomp_page_pool();
	public Xomp_parse_mgr_cfg Cfg() {return cfg;} private final    Xomp_parse_mgr_cfg cfg = new Xomp_parse_mgr_cfg();		
	private int wkrs_done;
	public void Wkrs_done_add_1() {synchronized (page_pool) {++wkrs_done;}}
	public void Run(Xowe_wiki wiki) {
		// init pool
		cfg.Init(wiki);
		page_pool.Init(wiki, cfg.Num_pages_in_pool());

		// init threads
		int wkr_len = cfg.Num_wkrs();
		Xomp_parse_wkr[] wkrs = new Xomp_parse_wkr[wkr_len];
		for (int i = 0; i < wkr_len; ++i) {
			Xomp_parse_wkr wkr = new Xomp_parse_wkr(this, Clone_wiki(wiki), page_pool, i, cfg.Num_pages_per_wkr());
			wkrs[i] = wkr;
		}

		// start threads; done separately b/c thread issues when done right after init
		for (int i = 0; i < wkr_len; ++i) {
			Xomp_parse_wkr wkr = wkrs[i];
			Thread_adp_.Start_by_key("xomp." + Int_.To_str_fmt(i, "000"), Cancelable_.Never, wkr, Xomp_parse_wkr.Invk__exec);
		}

		// wait until wkrs are wkrs_done
		while (true) {
			synchronized (page_pool) {
				if (wkrs_done == wkr_len) break;
			}
			Thread_adp_.Sleep(1000);
		}
		page_pool.Rls();

		// print stats
		Bry_bfr bfr = Bry_bfr_.New();
		for (int i = 0; i < wkr_len; ++i) {
			wkrs[i].Bld_stats(bfr);
		}
		Gfo_usr_dlg_.Instance.Note_many("", "", bfr.To_str_and_clear());
	}
	private static Xowe_wiki Clone_wiki(Xowe_wiki wiki) {
		Xol_lang_itm lang = new Xol_lang_itm(wiki.App().Lang_mgr(), wiki.Lang().Key_bry());
		Xol_lang_itm_.Lang_init(lang);
		Xowe_wiki rv = new Xowe_wiki(wiki.Appe(), lang, wiki.Ns_mgr(), wiki.Domain_itm(), wiki.Fsys_mgr().Root_dir());
		rv.Init_by_wiki();
		return rv;
	}
}
