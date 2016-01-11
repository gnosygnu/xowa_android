package gplx.xowa.wikis.data; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
public class Xow_page_fetcher_wiki implements Xow_page_fetcher {
	public Xow_page_fetcher Wiki_(Xowe_wiki v) {this.wiki = v; return this;} private Xowe_wiki wiki;
	public void Clear() {}
	public byte[] Get_by(int ns_id, byte[] ttl_bry) {
		Xoa_ttl ttl = Xoa_ttl.parse(wiki, ns_id, ttl_bry);
		Xoae_page page = wiki.Data_mgr().Get_page(ttl, false);	// go through data_mgr in case of redirects
		return page.Missing() ? null : page.Data_raw();
	}
}
