package gplx.xowa.addons.searchs.v1s; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.xowa.wikis.domains.*;
import gplx.xowa.addons.searchs.searchers.*;
public class Srch_qry {
	private final List_adp cmd_list = List_adp_.new_();
	public Srch_qry(byte[] search_raw, int page_idx, int page_len, Srch_ns_mgr ns_mgr, boolean async_db, Xow_domain_itm[] wiki_domains) {
		this.search_raw = search_raw; this.page_idx = page_idx; this.page_len = page_len; this.ns_mgr = ns_mgr; this.async_db = async_db; this.wiki_domains = wiki_domains;
		this.itms_bgn = page_idx * page_len;
		this.itms_end = itms_bgn + page_len;
		this.page_max = page_idx;	// default page_max to page_idx; adjust later when all results are known
		this.key = Bry_.Add_w_dlm(Byte_ascii.Pipe, search_raw, ns_mgr.To_hash_key());
		this.special_link_base_href = Bry_.Add(Bry_.new_a7("Special:Search/"), search_raw, Bry_.new_a7("?fulltext=y"));
	}
	public final byte[]				key;
	public final byte[]				search_raw;
	public final int					page_idx;
	public final int					page_len;
	public final int					itms_bgn;
	public final int					itms_end;
	public final Srch_ns_mgr			ns_mgr;
	public final Xow_domain_itm[]	wiki_domains;
	public final byte[]				special_link_base_href;
	public final boolean				async_db;
	public int page_max;
	public int			Cmds__len() {return cmd_list.Count();}
	public Xows_ui_cmd	Cmds__get_at(int i) {return (Xows_ui_cmd)cmd_list.Get_at(i);}
	public void			Cmds__add(Xows_ui_cmd cmd) {cmd_list.Add(cmd);}
}
