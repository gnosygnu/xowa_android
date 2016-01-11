package gplx.xowa.xtns.translates; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.langs.*;
import gplx.xowa.specials.*;
public class Xop_mylanguage_page implements Xows_page {
	public Xows_special_meta Special_meta() {return Xows_special_meta_.Itm__my_language;}
	public void Special_gen(Xowe_wiki wiki, Xoae_page page, Xoa_url url, Xoa_ttl ttl) {
		// Special:MyLanguage/Help:A -> Help:A/fr
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
}
