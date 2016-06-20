package gplx.xowa.addons.bldrs.exports.merges; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*;
import gplx.dbs.*; import gplx.xowa.addons.bldrs.exports.utls.*;
public class Merge2_wkr__heap_one extends Merge2_wkr__heap_base {
	public Merge2_wkr__heap_one(Split_tbl tbl) {super(tbl);}
	@Override protected void Copy_to_heap(Merge_ctx ctx, Merge_prog_wkr prog_wkr, Merge2_heap_mgr heap_mgr, Split_tbl tbl) {
		// init
		String tbl_name = tbl.Tbl_name();
		Dbmeta_fld_list flds = tbl.Flds();
		Merge2_heap_db heap_db = heap_mgr.Cur();
		if (heap_db == null) heap_db = heap_mgr.Make_itm(ctx.Wiki(), -1);

		// copy
		Merge_wkr_utl.Merge_by_sql(prog_wkr, "merging " + tbl_name, tbl_name, flds, ctx.Pack_conn(), heap_db, -1, Bool_.N);
	}
	@Override protected void Copy_to_wiki(Merge_ctx ctx, Merge_prog_wkr prog_wkr, Merge2_heap_mgr heap_mgr, Split_tbl tbl) {
		if (ctx.Heap__copy_to_wiki())
			heap_mgr.Copy_to_wiki(ctx, prog_wkr, this);
	}
	@Override public void Copy_to_wiki(Merge_ctx ctx, Merge_prog_wkr prog_wkr, String tbl_name, Dbmeta_fld_list trg_flds, Db_conn src_conn, Merge2_trg_itm trg_db, String[] fld_pkeys) {
		Merge_wkr_utl.Merge_by_sql(prog_wkr, "merging " + tbl_name, tbl_name, trg_flds, src_conn, trg_db, -1, Bool_.Y);
	}
}
