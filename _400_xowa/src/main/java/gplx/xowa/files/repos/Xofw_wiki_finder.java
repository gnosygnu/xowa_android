package gplx.xowa.files.repos; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
import gplx.xowa.wikis.*; import gplx.xowa.files.*; import gplx.xowa.files.repos.*;
public interface Xofw_wiki_finder {
	void Find(List_adp repo_pairs, Xof_xfer_itm file);
	boolean Locate(Xofw_file_finder_rslt rv, List_adp repo_pairs, byte[] ttl_bry);
}
