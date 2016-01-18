package gplx.xowa.files.caches; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
public interface Xou_cache_finder {
	boolean Find(Xow_wiki wiki, byte[] page_url, Xof_fsdb_itm fsdb_itm);
	void Clear();
	void Add(Xof_fsdb_itm fsdb_itm);
}
