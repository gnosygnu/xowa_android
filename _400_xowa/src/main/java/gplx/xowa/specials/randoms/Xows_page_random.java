package gplx.xowa.specials.randoms; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*;
import gplx.xowa.wikis.nss.*;
public class Xows_page_random implements Xows_page {
	public Xows_page_random(Xowe_wiki wiki) {}
	public Rndm_mgr Mgr() {return mgr;} private final Rndm_mgr mgr = new Rndm_mgr();
	public Xows_special_meta Special_meta() {return Xows_special_meta_.Itm__random;}
	public void Special_gen(Xowe_wiki wiki, Xoae_page page, Xoa_url url, Xoa_ttl ttl) {
		Xow_ns ns = wiki.Ns_mgr().Names_get_or_main(ttl.Rest_txt());
		byte[] random_ttl_bry = wiki.Db_mgr().Load_mgr().Find_random_ttl(ns);
		wiki.Data_mgr().Redirect(page, ns.Gen_ttl(random_ttl_bry));
	}
}
