package gplx.xowa.addons.wikis.pages.randoms.specials; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.pages.*; import gplx.xowa.addons.wikis.pages.randoms.*;
import gplx.xowa.wikis.nss.*; import gplx.xowa.specials.*;
public class Rndm_root_special implements Xow_special_page {
	public void Special__gen(Xow_wiki wikii, Xoa_page pagei, Xoa_url url, Xoa_ttl ttl) {
		Xowe_wiki wiki = (Xowe_wiki)wikii; Xoae_page page = (Xoae_page)pagei;
		Xow_ns ns = wiki.Ns_mgr().Names_get_or_main(ttl.Rest_txt());
		// Rndm_addon.Get(wiki).Mgr().Regy().Get_rndm_page_by_ns(ns);
		byte[] random_ttl_bry = wiki.Db_mgr().Load_mgr().Find_random_ttl(ns);
		byte[] root_bry = Xoa_ttl.parse(wiki, random_ttl_bry).Root_txt();
		wiki.Data_mgr().Redirect(page, ns.Gen_ttl(root_bry));
	}

	public static final String SPECIAL_KEY = "RandomRootPage";
	public static final    byte[] Display_ttl = Bry_.new_a7("Random Root Page");
	public Xow_special_meta Special__meta() {return new Xow_special_meta(Xow_special_meta_.Src__mw, SPECIAL_KEY);}
	public static final    Xow_special_page Prototype = new Rndm_root_special();
	public Xow_special_page Special__clone() {return this;}
}
