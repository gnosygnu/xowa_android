package gplx.xowa.addons.searchs.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.dbs.*; import gplx.dbs.qrys.*; import gplx.fsdb.*; import gplx.fsdb.meta.*; import gplx.xowa.addons.sqlite_utils.bldrs.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
import gplx.xowa.wikis.data.*; import gplx.xowa.wikis.data.tbls.*; import gplx.xowa.addons.searchs.dbs.*;
public class Srch_link_tier_cmd extends Xob_cmd__base implements Xob_cmd {
	public int Word_count_max = 10000; public int Tier_count_max = 128;
	public Srch_link_tier_cmd(Xob_bldr bldr, Xowe_wiki wiki) {super(bldr, wiki);}
	@Override public String Cmd_key() {return Xob_cmd_keys.Key_search_link_tier;}
	@Override public void Cmd_run() {
		// init
		if (!gplx.core.envs.Env_.Mode_testing()) wiki.Init_assert();
		Srch_db_mgr search_db_mgr = Srch_search_addon.Get(wiki).Db_mgr();
		Xowd_page_tbl page_tbl = wiki.Data__core_mgr().Db__core().Tbl__page();

		int link_tbls_len = search_db_mgr.Tbl__link__len();
		for (int k = 0; k < link_tbls_len; ++k) {
			Srch_link_tbl link_tbl = search_db_mgr.Tbl__link__get_at(k);
			// main logic; assign tiers to each word's page in batches of 128
			Db_attach_mgr attach_mgr = new Db_attach_mgr(page_tbl.conn, new Db_attach_itm("link_db", link_tbl.conn));
			Db_stmt select_all_stmt = link_tbl.Select_all__stmt(attach_mgr, page_tbl);
			Db_stmt update_tier_stmt = link_tbl.Update_tier__stmt();
			List_adp link_rows = List_adp_.new_();
			int link_word_id = 0;
			try {
				while (true) {// loop until no more rows which is denoted by "link_word_id = -1;"
					// for each word, read 10,000 search_links into memory; note need to only have 1 conn open to avoid locking issues
					link_word_id = link_tbl.Select_all__exec(Cancelable_.Never, link_rows, select_all_stmt, page_tbl, Word_count_max, link_word_id);
					// usr_dlg.Prog_many("", "", "updating page_score_tier; word_id=~{0}", link_word_id);
					int len = link_rows.Count(); int prv_word_id = -1, prv_ns_id = -1, cur_tier_id = -1, cur_tier_count = -1, cur_tier_cutoff = -1;

					// loop over each search_link and update tier
					link_tbl.conn.Txn_bgn("link_tier__gen");
					int update_count = 0;
					for (int i = 0; i < len; ++i) {
						Srch_link_row link_row = (Srch_link_row)link_rows.Get_at(i);
						int cur_word_id = link_row.Word_id;
						if (prv_word_id != cur_word_id) {	// if word_id has changed, reset ns
							prv_word_id  = cur_word_id;
							prv_ns_id = -1;
						}
						int cur_ns_id = link_row.Page_ns;
						if (prv_ns_id != cur_ns_id) {		// if ns_id has changed, reset tier
							prv_ns_id  = cur_ns_id;
							cur_tier_id = 0;
							cur_tier_count = 0;
							cur_tier_cutoff = -1;
						}
						if (cur_tier_id != 0) {
							update_tier_stmt.Clear().Val_int(link_tbl.fld_page_score_tier, cur_tier_id).Crt_int(link_tbl.fld_word_id, link_row.Word_id).Crt_int(link_tbl.fld_page_id, link_row.Page_id).Exec_update();
							++update_count;
							if (update_count % 10000 == 0)		usr_dlg.Prog_many("", "", "updating page_score_tier; word_id=~{0} count=~{1}", cur_word_id, update_count);
						}
						++cur_tier_count;
						if (cur_tier_count >= Tier_count_max) {	// if tier filled, go to next tier
							if (cur_tier_cutoff == -1)
								cur_tier_cutoff = link_row.Page_rank;
							else {
								if (link_row.Page_rank < cur_tier_cutoff) {
									++cur_tier_id;
									cur_tier_count = 0;
								}
							}
						}
					}
					link_tbl.conn.Txn_end();
					if (link_word_id == -1) break;
				}
			}
			finally {select_all_stmt.Rls(); update_tier_stmt.Rls(); attach_mgr.Detach();}
		}
	}
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__word_count_max_))		Word_count_max = m.ReadInt("v");
		else if	(ctx.Match(k, Invk__tier_count_max_))		Tier_count_max = m.ReadInt("v");
		else												return GfoInvkAble_.Rv_unhandled;
		return this;
	}	private static final String Invk__word_count_max_ = "word_count_max_", Invk__tier_count_max_ = "tier_count_max_";
}
