package gplx.xowa.files.repos; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
public interface Xow_repo_mgr {
	Xof_repo_pair		Repos_get_at(int i);
	Xof_repo_pair		Repos_get_by_wiki(byte[] wiki);
	Xof_repo_pair[]		Repos_ary();
	Xof_repo_itm		Get_trg_by_id_or_null(int id, byte[] lnki_ttl, byte[] page_url);
	Xof_repo_itm		Get_src_by_id_or_null(int id, byte[] lnki_ttl, byte[] page_url);
}
