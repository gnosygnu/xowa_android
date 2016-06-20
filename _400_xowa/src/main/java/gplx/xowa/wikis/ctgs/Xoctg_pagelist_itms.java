package gplx.xowa.wikis.ctgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.core.brys.fmtrs.*;
import gplx.xowa.htmls.*; import gplx.xowa.htmls.hrefs.*; import gplx.xowa.htmls.core.htmls.*; import gplx.xowa.htmls.core.wkrs.lnkis.htmls.*; import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.wikis.nss.*;
import gplx.xowa.users.history.*;
public class Xoctg_pagelist_itms implements gplx.core.brys.Bfr_arg {
	private Xoh_href_wtr href_wtr; private Xou_history_mgr history_mgr; private Bry_fmtr fmtr_itm;
	public void Init_app(Xoae_app app, Bry_fmtr fmtr_itm) {
		this.href_wtr = app.Html__href_wtr();
		this.history_mgr = app.Usere().History_mgr();
		this.fmtr_itm = fmtr_itm;
	} 
	public void Init_wiki(Xowe_wiki wiki, Xoh_wtr_ctx hctx) {this.wiki = wiki; this.hctx = hctx;} private Xowe_wiki wiki; private Xoh_wtr_ctx hctx;
	public void Itms_clear() 				{itms.Clear();} private List_adp itms = List_adp_.New();	
	public void Itms_add(Xowd_page_itm page) 	{itms.Add(page);}	
	public void Bfr_arg__add(Bry_bfr bfr) {
		int len = itms.Count();
		for (int i = 0; i < len; i++) {
			Xowd_page_itm page = (Xowd_page_itm)itms.Get_at(i);
			Xoa_ttl ttl = Xoa_ttl.parse(wiki, Xow_ns_.Tid__category, page.Ttl_page_db());
			byte[] lnki_cls = hctx.Mode_is_hdump() ? Bry_.Empty : Xoh_lnki_wtr.Lnki_cls_visited(history_mgr, wiki.Domain_bry(), ttl.Page_txt());	// NOTE: must be ttl.Page_txt() in order to match Xou_history_mgr.Add
			byte[] lnki_href = href_wtr.Build_to_bry(wiki, ttl);
			byte[] lnki_ttl = ttl.Full_txt_w_ttl_case();
			byte[] lnki_text = ttl.Page_txt();
			fmtr_itm.Bld_bfr_many(bfr, lnki_cls, lnki_href, lnki_ttl, lnki_text);
		}
	}
}
