package gplx.xowa.addons.bldrs.exports.splits.files; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*; import gplx.xowa.addons.bldrs.exports.splits.*;
import gplx.dbs.*; import gplx.dbs.bulks.*; import gplx.xowa.wikis.data.tbls.*;	
import gplx.xowa.addons.bldrs.exports.splits.metas.*; import gplx.xowa.addons.bldrs.exports.splits.rslts.*;
public class Split_wkr__file implements Split_wkr {
	private Split_meta_wkr_base[] meta_wkrs;
	public void Split__init(Split_ctx ctx, Xow_wiki wiki, Db_conn wkr_conn) {
		Db_conn atr_conn = wiki.File__fsdb_core().File__atr_file__at(gplx.fsdb.meta.Fsm_mnt_mgr.Mnt_idx_main).Conn();
		new Split_init__file().Exec(ctx, wiki, wkr_conn, atr_conn);
		this.meta_wkrs = new Split_meta_wkr_base[]
		{ new Split_meta_wkr__bin(ctx, wiki.File__mnt_mgr().Mnts__get_main().Bin_mgr())	// NOTE: bin must go first b/c it sets trg_db
		, new Split_meta_wkr__fil(ctx, atr_conn)
		, new Split_meta_wkr__thm(ctx, atr_conn)
		, new Split_meta_wkr__org(ctx, atr_conn)
		};
	}
	public void Split__trg__1st__new(Split_ctx ctx, Db_conn wkr_conn) {
		Db_tbl_copy copy_mgr = new Db_tbl_copy();
		Db_conn atr_conn = ctx.Wiki().File__fsdb_core().File__atr_file__at(gplx.fsdb.meta.Fsm_mnt_mgr.Mnt_idx_main).Conn();
		copy_mgr.Copy_many(atr_conn, wkr_conn, "fsdb_dba", "fsdb_dbb", "fsdb_dir", "fsdb_mnt");
		copy_mgr.Copy_one (atr_conn, wkr_conn, "xowa_cfg", "xowa_cfg__file");
	}
	public void Split__trg__nth__new(Split_ctx ctx, Db_conn wkr_conn) {
		for (Split_meta_wkr_base wkr : meta_wkrs)
			wkr.On_nth_new(ctx, wkr_conn);
	}
	public void Split__trg__nth__rls(Split_ctx ctx, Db_conn trg_conn) {
		for (Split_meta_wkr_base wkr : meta_wkrs)
			wkr.On_nth_rls(ctx, trg_conn);
	}
	public void Split__pages_loaded(Split_ctx ctx, int ns_id, int score_bgn, int score_end) {
		for (Split_meta_wkr_base wkr : meta_wkrs)
			wkr.Load(ctx.Wkr_conn(), ctx.Page_mgr(), ns_id, score_bgn, score_end);
	}
	public void Split__exec(Split_ctx ctx, Split_rslt_mgr rslt_mgr, Xowd_page_itm page, int page_id) {
		Split_page_itm meta_page = ctx.Page_mgr().Get_by_or_null(page_id); if (meta_page == null) return;	// NOTE: pages may not have images
		for (Split_meta_wkr_base wkr : meta_wkrs)
			wkr.Save(ctx, rslt_mgr, meta_page);
	}
	public void Split__term(Split_ctx ctx) {}
}
