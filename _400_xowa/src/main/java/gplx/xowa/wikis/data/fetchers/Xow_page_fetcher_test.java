package gplx.xowa.wikis.data.fetchers; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.data.*;
import gplx.xowa.wikis.data.tbls.*;
public class Xow_page_fetcher_test implements Xow_page_fetcher {
	public Xow_page_fetcher Wiki_(Xowe_wiki v) {return this;}
	public void Clear() {pages.Clear();}	private Hash_adp pages = Hash_adp_.New();
	public void Add(int ns_id, byte[] ttl, byte[] text) {
		Xowd_page_itm page = new Xowd_page_itm().Ns_id_(ns_id).Ttl_page_db_(ttl).Text_(text);
		pages.Add(Make_key(ns_id, ttl), page);
	}
	public byte[] Get_by(int ns_id, byte[] ttl) {
		Xowd_page_itm rv = (Xowd_page_itm)pages.Get_by(Make_key(ns_id, ttl));
		return rv == null ? null : rv.Text();
	}
	String Make_key(int ns_id, byte[] ttl) {return Int_.To_str(ns_id) + "|" + String_.new_u8(ttl);}
}
