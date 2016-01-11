package gplx.xowa.wikis; import gplx.*; import gplx.xowa.*;
public interface Xoa_wiki_mgr extends GfoInvkAble {
	boolean		Has(byte[] key);
	int			Count();
	Xow_wiki	Get_at_i(int i);
	Xow_wiki	Get_by_key_or_null_i(byte[] key);
	Xow_wiki	Get_by_key_or_make_init_y(byte[] key);
	Xow_wiki	Get_by_key_or_make_init_n(byte[] key);
}
