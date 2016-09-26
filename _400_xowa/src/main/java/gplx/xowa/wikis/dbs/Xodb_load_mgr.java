package gplx.xowa.wikis.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.core.primitives.*;
import gplx.xowa.wikis.nss.*;
import gplx.xowa.addons.wikis.ctgs.bldrs.*; import gplx.xowa.addons.wikis.ctgs.*; import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.addons.wikis.ctgs.htmls.catpages.*; import gplx.xowa.addons.wikis.ctgs.htmls.catpages.doms.*; import gplx.xowa.addons.wikis.ctgs.htmls.catpages.urls.*;
public interface Xodb_load_mgr {
	void Load_init					(Xowe_wiki wiki);
	void Load_page					(Xowd_page_itm rv, Xow_ns ns);
	boolean Load_by_id					(Xowd_page_itm rv, int id);
	void Load_by_ids				(Cancelable cancelable, List_adp rv, int bgn, int end);
	boolean Load_by_ttl				(Xowd_page_itm rv, Xow_ns ns, byte[] ttl);
	void Load_by_ttls				(Cancelable cancelable, Ordered_hash rv, boolean fill_idx_fields_only, int bgn, int end);
	boolean Load_ctg_v1				(Xoctg_catpage_ctg rv, byte[] ttl);
	void Load_ttls_for_all_pages	(Cancelable cancelable, List_adp rslt_list, Xowd_page_itm rslt_nxt, Xowd_page_itm rslt_prv, Int_obj_ref rslt_count, Xow_ns ns, byte[] key, int max_results, int min_page_len, int browse_len, boolean include_redirects, boolean fetch_prv_item);
	void Load_ttls_for_search_suggest(Cancelable cancelable, List_adp rslt_list, Xow_ns ns, byte[] key, int max_results, int min_page_len, int browse_len, boolean include_redirects, boolean fetch_prv_item);
	byte[] Find_random_ttl			(Xow_ns ns);
	void Clear();	// TEST:helper function
	byte[] Load_qid					(byte[] wiki_alias, byte[] ns_num, byte[] ttl);
	int Load_pid					(byte[] lang_key, byte[] pid_name);
	Xodb_page_rdr Get_page_rdr		(Xowe_wiki wiki);
}
