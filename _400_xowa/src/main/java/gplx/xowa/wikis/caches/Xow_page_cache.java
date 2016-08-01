package gplx.xowa.wikis.caches; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
public class Xow_page_cache {
	private final    Xowe_wiki wiki;
	private final    Hash_adp_bry cache = Hash_adp_bry.cs();	// NOTE: wiki titles are not case-sensitive when ns is "1st-letter" (EX: w:earth an w:Earth); in these cases, two entries will be stored
	public Xow_page_cache(Xowe_wiki wiki) {this.wiki = wiki;}
	public byte[] Get_or_load_as_src(Xoa_ttl ttl) {
		Xow_page_cache_itm rv = Get_or_load_as_itm(ttl);
		return rv == null ? null : rv.Wtxt__direct();
	}
	public void Add(byte[] ttl_full_db, Xow_page_cache_itm itm) {
		cache.Add(ttl_full_db, itm);
	}
	public Xow_page_cache_itm Get_or_load_as_itm(Xoa_ttl ttl) {
		byte[] ttl_full_db = ttl.Full_db();
		Xow_page_cache_itm rv = (Xow_page_cache_itm)cache.Get_by_bry(ttl_full_db);
		if		(rv == Xow_page_cache_itm.Missing) return null;
		else if (rv == null) {
			Xoae_page page = wiki.Data_mgr().Load_page_by_ttl(ttl);	// NOTE: do not call Db_mgr.Load_page; need to handle redirects
			if (page.Db().Page().Exists()) {
				rv = new Xow_page_cache_itm(page.Ttl(), page.Db().Text().Text_bry(), page.Redirect_trail().Itms__get_at_0th_or_null());
				synchronized (this) {	// LOCK:high-usage;DATE:2016-07-14
					cache.Add_bry_obj(ttl_full_db, rv);
				}
			}
			else {
				synchronized (this) {	// LOCK:high-usage;DATE:2016-07-14
					cache.Add_bry_obj(ttl_full_db, Xow_page_cache_itm.Missing);
					rv = null;
				}
			}
		}
		return rv;
	}
	public Xow_page_cache_itm Get_or_load_as_itm_2(Xoa_ttl ttl) {	// NOTE: same as Get_or_load_as_itm, but handles redirects to missing pages; DATE:2016-05-02
		byte[] ttl_full_db = ttl.Full_db();
		Xow_page_cache_itm rv = (Xow_page_cache_itm)cache.Get_by_bry(ttl_full_db);
		if		(rv == Xow_page_cache_itm.Missing) return null;
		else if (rv == null) {
			Xoae_page page = wiki.Data_mgr().Load_page_by_ttl(ttl);	// NOTE: do not call Db_mgr.Load_page; need to handle redirects
			if (	page.Db().Page().Exists()				// page exists
				||	page.Redirect_trail().Itms__len() > 0 ) {		// page redirects to missing page; note that page.Missing == true and page.Redirected_src() != null; PAGE: en.w:Shah_Rukh_Khan; DATE:2016-05-02
				rv = new Xow_page_cache_itm(page.Ttl(), page.Db().Text().Text_bry(), page.Redirect_trail().Itms__get_at_0th_or_null());
				synchronized (this) {	// LOCK:high-usage;DATE:2016-07-14
					cache.Add_bry_obj(ttl_full_db, rv);
				}
			}
			else {
				synchronized (this) {	// LOCK:high-usage;DATE:2016-07-14
					cache.Add_bry_obj(ttl_full_db, Xow_page_cache_itm.Missing);
					rv = null;
				}
			}
		}
		return rv;
	}
	public void Free_mem_all() {
		synchronized (this) {	// LOCK:app-level; DATE:2016-07-06
			cache.Clear();
		}
	}
}
