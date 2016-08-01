package gplx.xowa.xtns.wbases; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
public class Wbase_doc_mgr {
	private final    Xoae_app app;
	private final    Wdata_wiki_mgr wbase_mgr;
	private final    Wbase_qid_mgr qid_mgr;
	private final    gplx.xowa.apps.caches.Wdata_doc_cache hash;
	public Wbase_doc_mgr(Xoae_app app, Wdata_wiki_mgr wbase_mgr, Wbase_qid_mgr qid_mgr) {
		this.app = app; this.wbase_mgr = wbase_mgr; this.qid_mgr = qid_mgr;
		this.hash = app.Cache_mgr().Doc_cache();
	}
	public void Enabled_(boolean v) {this.enabled = v;} private boolean enabled;
	public void Clear() {
		synchronized (hash) {	// LOCK:app-level
			hash.Clear();
		}
	}
	public void Add(byte[] full_db, Wdata_doc page) {	// TEST:
		synchronized (hash) {	// LOCK:app-level
			if (hash.Get_or_null(full_db) == null)
				hash.Add(full_db, page);
		}
	}	
	public Wdata_doc Get_by_ttl_or_null(Xowe_wiki wiki, Xoa_ttl ttl) {
		byte[] qid_bry = qid_mgr.Get_or_null(wiki, ttl);	// EX: "enwiki","Earth" -> "Q2"
		return qid_bry == null ? null : this.Get_by_bry_or_null(qid_bry);
	}
	public Wdata_doc Get_by_xid_or_null(byte[] xid) {return Get_by_bry_or_null(Prepend_property_if_needed(xid));}// scribunto passes either p1 or q1; convert p1 to "Property:P1"
	public Wdata_doc Get_by_bry_or_null(byte[] full_db) {	// must be correct format; EX:"Q2" or "Property:P1"
		Wdata_doc rv = hash.Get_or_null(full_db);
		if (rv == null) {
			byte[] page_src = Load_or_null(full_db); if (page_src == null) return null;	// page not found
			synchronized (hash) {	// LOCK:app-level; both hash and jdoc_parser
				rv = new Wdata_doc(full_db, wbase_mgr, wbase_mgr.Jdoc_parser().Parse(page_src));
			}
			Add(full_db, rv);
		}
		return rv;
	}
	private byte[] Load_or_null(byte[] full_db) {
		if (!enabled) return null;
		Xoa_ttl page_ttl = Xoa_ttl.Parse(wbase_mgr.Wdata_wiki(), full_db); if (page_ttl == null) {app.Usr_dlg().Warn_many("", "", "invalid qid for ttl: qid=~{0}", full_db); return null;}
		Xoae_page page_itm = wbase_mgr.Wdata_wiki().Data_mgr().Load_page_by_ttl(page_ttl);
		return page_itm.Db().Page().Exists() ? page_itm.Db().Text().Text_bry() : null;
	}

	private static byte[] Prepend_property_if_needed(byte[] bry) {
		int len = bry == null ? 0 : bry.length;
		return	len > 1
			&&	Byte_ascii.Case_lower(bry[0]) == Byte_ascii.Ltr_p
			&&	Byte_ascii.Is_num(bry[1])
			?	Bry_.Add(Wdata_wiki_mgr.Ns_property_name_bry, Byte_ascii.Colon_bry, bry)	// if ttl starts with "p#", prepend "Property:" to get "Property:P#"; NOTE: do not ucase P b/c it breaks a test; DATE:2014-02-18
			:	bry;
	}
}
