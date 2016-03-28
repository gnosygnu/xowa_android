package gplx.xowa.addons.searchs.dbs.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.dbs.*;
import gplx.xowa.bldrs.*;
public class Srch_bldr_wkr implements Xob_page_wkr {
	private final    Xowe_wiki wiki;
	private final    Srch_temp_tbl_wkr temp_tbl_wkr = new Srch_temp_tbl_wkr(); 
	public Srch_bldr_wkr(Xob_bldr bldr, Xowe_wiki wiki) {this.wiki = wiki;}
	public String Wkr_key() {return Xob_cmd_keys.Key_text_search_wkr;}
	public void Wkr_bgn(Xob_bldr bldr) {
		temp_tbl_wkr.Init(Bool_.N, wiki);
	}
	public void Wkr_run(gplx.xowa.wikis.data.tbls.Xowd_page_itm page) {
		try {temp_tbl_wkr.Exec_by_wkr(page.Id(), page.Ttl_page_db());}
		catch (Exception e) {Gfo_usr_dlg_.Instance.Warn_many("", "", "search:error: page=~{0} err=~{1}", page.Ttl_page_db(), Err_.Message_gplx_full(e));}
	}
	public void Wkr_end() {
		temp_tbl_wkr.Term();
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {return this;}
}
