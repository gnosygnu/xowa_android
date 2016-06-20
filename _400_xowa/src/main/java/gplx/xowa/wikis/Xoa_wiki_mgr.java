package gplx.xowa.wikis; import gplx.*; import gplx.xowa.*;
public interface Xoa_wiki_mgr extends Gfo_invk {
	int				Count();
	boolean			Has(byte[] key);
	Xow_wiki		Get_at(int idx);
	Xow_wiki		Get_by_or_null(byte[] key);
	Xow_wiki		Get_by_or_make_init_y(byte[] key);
	Xow_wiki		Get_by_or_make_init_n(byte[] key);
	void			Add(Xow_wiki wiki);
	Xow_wiki		Make(byte[] domain_bry, Io_url wiki_root_dir);
	Xow_wiki		Import_by_url(Io_url fil);
}
