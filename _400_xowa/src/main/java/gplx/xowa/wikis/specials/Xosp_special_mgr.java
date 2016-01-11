package gplx.xowa.wikis.specials; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.core.net.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.specials.*;
import gplx.xowa.specials.*; import gplx.xowa.specials.xowa.file_browsers.*;
import gplx.xowa.htmls.*;
public class Xosp_special_mgr {
	private final Xowv_wiki wiki;
	private final Hash_adp_bry hash;
	public Xosp_special_mgr(Xowv_wiki wiki) {
		this.wiki = wiki;
		// hash.Add_str_obj(Xows_special_meta_.Key__statistics				, page_statistics);
		this.hash = Hash_adp_bry.cs();
	}
	public void Get_by_ttl(Xoh_page rv, Gfo_url url, Xoa_ttl ttl) {
		Xosp_fbrow_rslt rslt = Xosp_fbrow_special.Gen(url.Qargs(), wiki.Appv().Wiki_mgr());
		rv.Init(wiki, null, ttl, -1);
		rv.Body_(rslt.Html_body());
		rv.Html_head_xtn_(rslt.Html_head());
	}
	public void Get_by_url(Xow_wiki wiki, Xoa_page page, Xoa_url url, Xoa_ttl ttl) {
		int slash_pos = Bry_find_.Find_fwd(ttl.Page_txt_wo_qargs(), Xoa_ttl.Subpage_spr);	// check for slash
		byte[] special_name = slash_pos == Bry_find_.Not_found
				? ttl.Base_txt_wo_qarg()							// no slash found; use base_txt; ignore qry args and just get page_names; EX: Search/Earth?fulltext=y; Allpages?from=Earth...
				: Bry_.Mid(ttl.Page_txt_wo_qargs(), 0, slash_pos);	// slash found; use root page; EX: Special:ItemByTitle/enwiki/Earth
		Object o = hash.Get_by_bry(special_name);
		if (o == null) {
			Xol_specials_itm special_itm = wiki.Lang().Specials_mgr().Get_by_alias(special_name);
			if (special_itm != null)
				o = hash.Get_by_bry(special_itm.Special());
		}
		if (o != null) {
			// Xows_page special = (Xows_page)o;
			// page.Revision_data().Modified_on_(DateAdp_.Now());
			// special.Special_gen(wiki, page, url, ttl);
		}
	}
}
