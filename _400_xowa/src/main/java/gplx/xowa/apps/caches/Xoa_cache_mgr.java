package gplx.xowa.apps.caches; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*;
public class Xoa_cache_mgr {
	public Wdata_doc_cache Doc_cache() {return doc_cache;} private Wdata_doc_cache doc_cache = new Wdata_doc_cache();
	public void Free_mem_all() {
		doc_cache.Free_mem_all();
	}
}
