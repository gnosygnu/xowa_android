package gplx.xowa.addons.bldrs.exports.merges; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*;
import gplx.dbs.*; import gplx.xowa.addons.bldrs.exports.utls.*;
public abstract class Merge2_wkr__heap_base implements Merge2_wkr {
	public Merge2_wkr__heap_base(Split_tbl tbl) {
		this.tbl = tbl;
		this.heap_mgr = new Merge2_heap_mgr(tbl);
	}
	public Split_tbl Tbl() {return tbl;} private final    Split_tbl tbl;
	protected Merge2_heap_mgr Heap_mgr() {return heap_mgr;} private final    Merge2_heap_mgr heap_mgr;
	public void Merge_data(Merge_ctx ctx, Merge_prog_wkr prog_wkr) {	// fires once per file
		this.Copy_to_heap(ctx, prog_wkr, heap_mgr, tbl);
		this.Copy_to_wiki(ctx, prog_wkr, heap_mgr, tbl);
	}
	protected abstract void Copy_to_heap(Merge_ctx ctx, Merge_prog_wkr prog_wkr, Merge2_heap_mgr heap_mgr, Split_tbl tbl);
	protected abstract void Copy_to_wiki(Merge_ctx ctx, Merge_prog_wkr prog_wkr, Merge2_heap_mgr heap_mgr, Split_tbl tbl);
	public abstract void Copy_to_wiki(Merge_ctx ctx, Merge_prog_wkr prog_wkr, String tbl_name, Dbmeta_fld_list src_flds, Db_conn src_conn, Merge2_trg_itm trg_db, String[] fld_pkeys);
}
