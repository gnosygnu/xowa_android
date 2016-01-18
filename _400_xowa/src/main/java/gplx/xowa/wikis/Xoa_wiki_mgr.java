package gplx.xowa.wikis; import gplx.*; import gplx.xowa.*;
public interface Xoa_wiki_mgr extends GfoInvkAble {
	int				Count();
	boolean			Has(byte[] key);
	Xow_wiki		Get_at(int idx);
	Xow_wiki		Get_by_or_null(byte[] key);
	Xow_wiki		Get_by_or_make_init_y(byte[] key);
	Xow_wiki		Get_by_or_make_init_n(byte[] key);
	void			Add(Xow_wiki wiki);
}
