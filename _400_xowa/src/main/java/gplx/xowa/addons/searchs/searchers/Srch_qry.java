package gplx.xowa.addons.searchs.searchers; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.xowa.wikis.domains.*; import gplx.xowa.addons.searchs.specials.*;
public class Srch_qry {
	private final List_adp cmd_list = List_adp_.new_();
	public Srch_qry(Srch_ns_mgr ns_mgr, byte[] search_raw, int slab_idx, int slab_bgn, int slab_end, boolean async_db, Xow_domain_itm[] wiki_domains) {
		this.Search_raw = search_raw; this.Ns_mgr = ns_mgr; this.Async_db = async_db; this.Wiki_domains = wiki_domains;
		this.Slab_bgn = slab_bgn;
		this.Slab_end = slab_end;
		this.Slab_len = slab_end - slab_bgn; 
		this.Slab_idx = slab_idx == -1 ? slab_bgn / Slab_len : slab_idx;
		this.Slab_idx_max = Slab_idx;	// default Slab_idx_max to Slab_idx; adjust later when all results are known
		this.Key = Bry_.Add(search_raw, Byte_ascii.Pipe_bry, ns_mgr.To_hash_key());
		this.Special_link_base_href = Bry_.Add(Bry_.new_a7("Special:Search/"), search_raw, Bry_.new_a7("?fulltext=y"));
	}
	public final byte[]				Key;
	public final Srch_ns_mgr			Ns_mgr;
	public final byte[]				Search_raw;
	public final int					Slab_idx;
	public final int					Slab_len;
	public final int					Slab_bgn;
	public final int					Slab_end;
	public int							Slab_idx_max = Int_.Max_value;
	public final Xow_domain_itm[]	Wiki_domains;
	public final byte[]				Special_link_base_href;
	public final boolean				Async_db;
	public int							Cmds__len() {return cmd_list.Count();}
	public Srch_special_cmd				Cmds__get_at(int i) {return (Srch_special_cmd)cmd_list.Get_at(i);}
	public void							Cmds__add(Srch_special_cmd cmd) {cmd_list.Add(cmd);}
}
