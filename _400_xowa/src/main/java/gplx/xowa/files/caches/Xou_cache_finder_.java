package gplx.xowa.files.caches; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
import gplx.xowa.files.repos.*;
public class Xou_cache_finder_ {
	public static final Xou_cache_finder Noop = new Xou_cache_finder_noop();
	public static Xou_cache_finder New_mem() {return new Xou_cache_finder_mem();}
	public static Xou_cache_finder New_db(Xou_cache_mgr cache_mgr) {return new Xou_cache_finder_db(cache_mgr);}
}
class Xou_cache_finder_noop implements Xou_cache_finder {
	public boolean Find(Xow_wiki wiki, byte[] page_url, Xof_fsdb_itm fsdb_itm) {
		fsdb_itm.Init_at_cache(false, 0, 0, Io_url_.Empty);
		return false;
	}
	public void Clear() {}
	public void Add(Xof_fsdb_itm fsdb_itm) {}
}
class Xou_cache_finder_mem implements Xou_cache_finder {
	private final Ordered_hash hash = Ordered_hash_.New_bry();
	private final Bry_bfr tmp_bfr = Bry_bfr.new_(255);
	private final Xof_img_size img_size = new Xof_img_size();
	private final Xof_url_bldr url_bldr = Xof_url_bldr.new_v2();
	public boolean Find(Xow_wiki wiki, byte[] page_url, Xof_fsdb_itm cur) {
		byte[] key = Xou_cache_itm.Key_gen(tmp_bfr, cur.Lnki_wiki_abrv(), cur.Lnki_ttl(), cur.Lnki_type(), cur.Lnki_upright(), cur.Lnki_w(), cur.Lnki_h(), cur.Lnki_time(), cur.Lnki_page(), cur.User_thumb_w());
		Xof_fsdb_itm mem = (Xof_fsdb_itm)hash.Get_by(key); 
		if (mem == null) {
			cur.Init_at_cache(false, 0, 0, Io_url_.Empty);
			return false;
		}
		Xof_repo_itm repo = wiki.File__repo_mgr().Get_trg_by_id_or_null(mem.Orig_repo_id(), mem.Lnki_ttl(), page_url);
		mem.Init_at_html(Xof_exec_tid.Tid_wiki_page, img_size, repo, url_bldr);
		cur.Init_at_cache(true, mem.Html_w(), mem.Html_h(), mem.Html_view_url());
		return true;
	}
	public void Clear() {}
	public void Add(Xof_fsdb_itm cur) {
		byte[] key = Xou_cache_itm.Key_gen(tmp_bfr, cur.Lnki_wiki_abrv(), cur.Lnki_ttl(), cur.Lnki_type(), cur.Lnki_upright(), cur.Lnki_w(), cur.Lnki_h(), cur.Lnki_time(), cur.Lnki_page(), cur.User_thumb_w());
		hash.Add(key, cur);
	}
}
class Xou_cache_finder_db implements Xou_cache_finder {
	private final Xou_cache_mgr cache_mgr;
	private final Xof_img_size img_size = new Xof_img_size(); private final Xof_url_bldr url_bldr = Xof_url_bldr.new_v2();
	public Xou_cache_finder_db(Xou_cache_mgr cache_mgr) {this.cache_mgr = cache_mgr;}
	public boolean Find(Xow_wiki wiki, byte[] page_url, Xof_fsdb_itm cur) {
		Xou_cache_itm cache_itm = cache_mgr.Get_or_null(cur); 
		if (cache_itm != null) {
			Xof_repo_itm repo = wiki.File__repo_mgr().Get_trg_by_id_or_null(cache_itm.Orig_repo_id(), cur.Lnki_ttl(), page_url);
			if (repo != null) {// unknown repo; shouldn't happen, but exit, else null ref
				cur.Init_at_orig((byte)cache_itm.Orig_repo_id(), repo.Wiki_domain(), cache_itm.Orig_ttl(), cache_itm.Orig_ext_itm(), cache_itm.Orig_w(), cache_itm.Orig_h(), Bry_.Empty);
				cur.Init_at_html(Xof_exec_tid.Tid_wiki_page, img_size, repo, url_bldr);
				if (Io_mgr.Instance.ExistsFil(cur.Html_view_url())) {
					cache_itm.Update_view_stats();
					cur.Init_at_cache(true, cur.Html_w(), cur.Html_h(), cur.Html_view_url());
					return true;
				}
			}
		}
		cur.Init_at_cache(false, 0, 0, Io_url_.Empty);
		return false;
	}
	public void Clear() {}
	public void Add(Xof_fsdb_itm cur) {}
}
