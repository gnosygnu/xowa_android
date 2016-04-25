package gplx.xowa.addons.apps.searchs.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*; import gplx.xowa.addons.apps.searchs.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
public class Srch_bldr_wkr implements Xob_page_wkr {
	private final    Xowe_wiki wiki;
	private final    Srch_temp_tbl_wkr temp_tbl_wkr = new Srch_temp_tbl_wkr(); 
	public Srch_bldr_wkr(Xob_bldr bldr, Xowe_wiki wiki) {this.wiki = wiki;}
	public String Page_wkr__key() {return Xob_cmd_keys.Key_text_search_wkr;}
	public void Page_wkr__bgn() {
		temp_tbl_wkr.Init(Bool_.N, wiki);
	}
	public void Page_wkr__run(gplx.xowa.wikis.data.tbls.Xowd_page_itm page) {
		try {temp_tbl_wkr.Exec_by_wkr(page.Id(), page.Ttl_page_db());}
		catch (Exception e) {Gfo_usr_dlg_.Instance.Warn_many("", "", "search:error: page=~{0} err=~{1}", page.Ttl_page_db(), Err_.Message_gplx_full(e));}
	}
	public void Page_wkr__run_cleanup() {}
	public void Page_wkr__end() {
		temp_tbl_wkr.Term();
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {return this;}
}
