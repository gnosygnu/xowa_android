package gplx.xowa.addons.bldrs.mass_parses.parses; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.mass_parses.*;
import gplx.dbs.*;
class Xomp_page_pool {
	private final    Object thread_lock = new Object();
	private final    Xomp_page_pool_loader loader;
	private final    int num_pages_per_wkr;
	private List_adp pool = List_adp_.New(); private int pool_idx = 0, pool_len = 0;
	public Xomp_page_pool(Xomp_page_pool_loader loader, int num_pages_per_wkr) {
		this.loader = loader; this.num_pages_per_wkr = num_pages_per_wkr;
	}
	public boolean Empty() {synchronized (thread_lock) {return empty;}} private boolean empty = false;
	public void Get_next(List_adp wkr_list) {
		synchronized (thread_lock) {
			// pool already marked exhausted by another wkr; return;
			if (empty) return;
			int wkr_end = pool_idx + num_pages_per_wkr;

			// need pages to fulfill request
			if (wkr_end > pool_len) {
				this.pool = loader.Load(pool, pool_idx, pool_len);
				this.pool_idx = 0;
				this.pool_len = pool.Len();
				if (pool_len == 0) {	// no more pages; return;
					empty = true;
					return;
				}
				wkr_end = num_pages_per_wkr;	// recalc wkr_end
			}

			// reset wkr_end; needed for very last set
			if (wkr_end >= pool_len)
				wkr_end = pool_len;

			// add pages to wkr_list
			for (int i = pool_idx; i < wkr_end; ++i) {
				Xomp_page_itm page = (Xomp_page_itm)pool.Get_at(i);
				wkr_list.Add(page);
			}
			pool_idx = wkr_end;
		}
	}
	public void Rls() {
		loader.Conn().Rls_conn();
	}
}
