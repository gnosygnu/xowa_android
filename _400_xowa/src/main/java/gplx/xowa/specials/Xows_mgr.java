package gplx.xowa.specials; import gplx.*; import gplx.xowa.*;
import gplx.xowa.users.history.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.specials.*;
import gplx.xowa.specials.*;
import gplx.xowa.specials.allPages.*; import gplx.xowa.specials.nearby.*; import gplx.xowa.specials.randoms.*; import gplx.xowa.specials.statistics.*; import gplx.xowa.xtns.translates.*; import gplx.xowa.specials.movePage.*;
import gplx.xowa.specials.xowa.system_data.*; import gplx.xowa.specials.xowa.default_tab.*; import gplx.xowa.specials.xowa.popup_history.*; import gplx.xowa.addons.apps.file_browsers.*; import gplx.xowa.specials.xowa.diags.*;
import gplx.xowa.xtns.wdatas.specials.*;
import gplx.xowa.addons.apps.searchs.specials.*;
import gplx.xowa.users.data.*; import gplx.xowa.users.bmks.*;
public class Xows_mgr {
	private final    Hash_adp_bry hash;
	public Xows_mgr(Xowe_wiki wiki, Xol_lang_itm lang) {
		hash = Hash_adp_bry.ci_u8(lang.Case_mgr());
		page_allpages = new Xows_page_allpages(wiki);
		page_search = new Srch_special_page(wiki);
		page_random = new Xows_page_random(wiki);
		Evt_lang_changed(wiki.Lang());
	}
	public Xows_page_allpages			Page_allpages() {return page_allpages;} private final    Xows_page_allpages page_allpages;
	public Srch_special_page			Page_search() {return page_search;} private final    Srch_special_page page_search;
	public Xows_page_random				Page_random() {return page_random;} private final    Xows_page_random page_random;
	public Xop_randomRootPage_page		Page_randomRootPage() {return page_randomRootPage;} private final    Xop_randomRootPage_page page_randomRootPage = new Xop_randomRootPage_page();
	public Xou_history_html				Page_history() {return page_history;} private final    Xou_history_html page_history = new Xou_history_html();
	public Xoud_history_special			Page_history2() {return page_history2;} private final    Xoud_history_special page_history2 = new Xoud_history_special();
	public Nearby_mgr					Page_nearby() {return page_nearby;} private final    Nearby_mgr page_nearby = new Nearby_mgr();
	public Xop_mylanguage_page			Page_mylanguage() {return page_mylanguage;} private final    Xop_mylanguage_page page_mylanguage = new Xop_mylanguage_page();
	public Wdata_itemByTitle_page		Page_itemByTitle() {return page_itemByTitle;} private final    Wdata_itemByTitle_page page_itemByTitle = new Wdata_itemByTitle_page();
	public Xop_statistics_page			Page_statistics() {return page_statistics;} private final    Xop_statistics_page page_statistics = new Xop_statistics_page();
	public Move_page					Page_movePage() {return page_movePage;} private final    Move_page page_movePage = new Move_page();
	public System_data_page				Page_system_data() {return page_system_data;} private final    System_data_page page_system_data = new System_data_page();
	public Default_tab_page				Page_default_tab() {return page_default_tab;} private final    Default_tab_page page_default_tab = new Default_tab_page();
	public Popup_history_page			Page_popup_history() {return page_popup_history;} private final    Popup_history_page page_popup_history = new Popup_history_page();
	public Xows_bmk_page				Page_bmk() {return page_bmk;} private final    Xows_bmk_page page_bmk = new Xows_bmk_page();
	public Xows_diag_page				Page_diag() {return page_diag;} private final    Xows_diag_page page_diag = new Xows_diag_page();
	public void Evt_lang_changed(Xol_lang_itm lang) {
		hash.Clear();
		hash.Add_str_obj(Xows_special_meta_.Ttl__search					, page_search);
		hash.Add_str_obj(Xows_special_meta_.Ttl__all_pages				, page_allpages);
		hash.Add_str_obj("prefixindex"									, page_allpages);
		hash.Add_str_obj(Xows_special_meta_.Ttl__random					, page_random);
		hash.Add_str_obj("random"										, page_random);
		hash.Add_str_obj(Xows_special_meta_.Ttl__random_root_page		, page_randomRootPage);
		hash.Add_bry_obj(Xou_history_mgr.Ttl_name						, page_history);
		hash.Add_str_obj(Xows_special_meta_.Ttl__page_history			, page_history2);
		hash.Add_str_obj(Xows_special_meta_.Ttl__nearby					, page_nearby);
		hash.Add_str_obj(Xows_special_meta_.Ttl__my_language			, page_mylanguage);
		hash.Add_str_obj(Xows_special_meta_.Ttl__item_by_title			, page_itemByTitle);
		hash.Add_str_obj(Xows_special_meta_.Ttl__statistics				, page_statistics);
		hash.Add_str_obj(Xows_special_meta_.Ttl__move_page				, page_movePage);
		hash.Add_str_obj(Xows_special_meta_.Ttl__system_data			, page_system_data);
		hash.Add_str_obj(Xows_special_meta_.Ttl__default_tab			, page_default_tab);
		hash.Add_str_obj(Xows_special_meta_.Ttl__popup_history			, page_popup_history);
		hash.Add_str_obj(Xows_special_meta_.Ttl__bookmarks				, page_bmk);
		hash.Add_str_obj(Xows_special_meta_.Ttl__diag					, page_diag);
	}
	public void Special__gen(Xoa_app app, Xow_wiki wiki, Xoa_page page, Xoa_url url, Xoa_ttl ttl) {
		int slash_pos = Bry_find_.Find_fwd(ttl.Page_txt_wo_qargs(), Xoa_ttl.Subpage_spr);	// check for slash
		byte[] special_name = slash_pos == Bry_find_.Not_found
				? ttl.Base_txt_wo_qarg()							// no slash found; use base_txt; ignore qry args and just get page_names; EX: Search/Earth?fulltext=y; Allpages?from=Earth...
				: Bry_.Mid(ttl.Page_txt_wo_qargs(), 0, slash_pos);	// slash found; use root page; EX: Special:ItemByTitle/enwiki/Earth
		Xows_page special = app.Special_regy().Get_by_or_null(special_name);
		if (special == null) {		// special_name not in app_regy; try wiki_regy
			special = (Xows_page)hash.Get_by_bry(special_name);			
			if (special == null) {	// special_name not in wiki_regy; try alias;
				Xol_specials_itm special_itm = wiki.Lang().Specials_mgr().Get_by_alias(special_name);
				if (special_itm != null)
					special = (Xows_page)hash.Get_by_bry(special_itm.Special());
			}
		}
		else {
			special = special.Special__clone();
		}
		if (special != null) {	// special found; generate it;
			page.Revision_data().Modified_on_(DateAdp_.Now());
			special.Special__gen(wiki, page, url, ttl);
		}
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_search))				return page_search;
		else	return GfoInvkAble_.Rv_unhandled;
	}	private static final String Invk_search = "search";
}
