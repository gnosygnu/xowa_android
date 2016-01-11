package gplx.xowa.specials.randoms; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*;
import gplx.xowa.wikis.nss.*;
public class Xop_randomRootPage_page implements Xows_page {
	public Xows_special_meta Special_meta() {return Xows_special_meta_.Itm__random_root_page;}
	public void Special_gen(Xowe_wiki wiki, Xoae_page page, Xoa_url url, Xoa_ttl ttl) {
		Xow_ns ns = wiki.Ns_mgr().Names_get_or_main(ttl.Rest_txt());
		byte[] random_ttl_bry = wiki.Db_mgr().Load_mgr().Find_random_ttl(ns);
		byte[] root_bry = Xoa_ttl.parse(wiki, random_ttl_bry).Root_txt();
		wiki.Data_mgr().Redirect(page, ns.Gen_ttl(root_bry));
	}
}
