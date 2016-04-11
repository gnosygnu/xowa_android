package gplx.xowa.xtns.translates; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.langs.*;
import gplx.xowa.specials.*;
public class Xop_mylanguage_page implements Xows_page {
	public Xows_special_meta Special__meta() {return Xows_special_meta_.Itm__my_language;}
	public void Special__gen(Xow_wiki wikii, Xoa_page pagei, Xoa_url url, Xoa_ttl ttl) {
		// Special:MyLanguage/Help:A -> Help:A/fr
		Xowe_wiki wiki = (Xowe_wiki)wikii; Xoae_page page = (Xoae_page)pagei;
		byte[] page_bry = ttl.Leaf_txt_wo_qarg(); 					// EX: Help:A
		byte[] lang_key = wiki.Appe().Usere().Lang().Key_bry();		// EX: fr
		byte[] trg_bry = page_bry;
		boolean lang_is_english = Bry_.Eq(lang_key, Xol_lang_itm_.Key_en); 
		if (!lang_is_english)
			trg_bry = Bry_.Add_w_dlm(Xoa_ttl.Subpage_spr, page_bry, lang_key);
		Xoae_page found_page = wiki.Data_mgr().Redirect(page, trg_bry);
		if (found_page.Missing() && !lang_is_english)	// foreign lang does not exist; default to english
			wiki.Data_mgr().Redirect(page, page_bry);
	}

	public Xows_page Special__clone() {return this;}
}
