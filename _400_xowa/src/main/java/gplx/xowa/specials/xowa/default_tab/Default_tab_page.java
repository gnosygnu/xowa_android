package gplx.xowa.specials.xowa.default_tab; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*; import gplx.xowa.specials.xowa.*;
public class Default_tab_page implements Xows_page {
	public Xows_special_meta Special__meta() {return Xows_special_meta_.Itm__default_tab;}
	public void Special__gen(Xow_wiki wiki, Xoa_page pagei, Xoa_url url, Xoa_ttl ttl) {
		Xoae_page page = (Xoae_page)pagei;
		page.Data_raw_(Bry_.Empty);
		page.Html_data().Custom_html_(Bry_.Empty);
		page.Html_data().Custom_tab_name_(Tab_name_bry);
	}
	public static final    byte[] Tab_name_bry = Bry_.new_a7("New Tab");

	public Xows_page Special__clone() {return this;}
}
