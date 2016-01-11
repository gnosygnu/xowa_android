package gplx.xowa.wikis.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
public interface Xodb_save_mgr {
	boolean Create_enabled(); void Create_enabled_(boolean v);
	boolean Update_modified_on_enabled(); void Update_modified_on_enabled_(boolean v);
	int Page_id_next(); void Page_id_next_(int v);
	void Data_create(Xoa_ttl ttl, byte[] text);
	void Data_update(Xoae_page page, byte[] text);
	void Data_rename(Xoae_page page, int trg_ns, byte[] trg_ttl);
	void Clear();
}
