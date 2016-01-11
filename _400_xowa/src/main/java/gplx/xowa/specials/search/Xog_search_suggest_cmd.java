package gplx.xowa.specials.search; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*;
import gplx.core.primitives.*; import gplx.xowa.wikis.data.tbls.*;
public class Xog_search_suggest_cmd implements GfoInvkAble, Cancelable {
	private Xowe_wiki wiki; private byte[] search_bry; private Xosrh_page_mgr searcher; private int max_results, all_pages_extend, all_pages_min;
	public Xog_search_suggest_cmd(Xoae_app app, Xog_search_suggest_mgr mgr) {
		this.app = app; this.mgr = mgr;
	}	private Xoae_app app; Xog_search_suggest_mgr mgr; Bry_bfr tmp_bfr = Bry_bfr.reset_(255); List_adp rslts_1 = List_adp_.new_(), rslts_2 = List_adp_.new_();
	public void Init(Xowe_wiki wiki, byte[] search_bry, int max_results, byte search_mode, int all_pages_extend, int all_pages_min) {
		this.wiki = wiki; this.search_bry = search_bry; this.max_results = max_results;
		this.search_mode = search_mode; this.all_pages_extend = all_pages_extend; this.all_pages_min = all_pages_min;
		searcher = new Xosrh_page_mgr();
	}
	private byte search_mode;
	public byte[] Search_bry() {return search_bry;}
	public boolean Canceled() {return canceled;}
	public void Cancel() {this.canceled = true;} private boolean canceled;
	public void Cancel_reset() {this.canceled = false;}
	public boolean Working() {return working;} public void Working_(boolean v) {working = v;} private boolean working;
	public List_adp Results() {return rslts_2;}
	public void Search() {
		try {	// NOTE: must handle any errors in async mode
			canceled = false;
			working = true;
			boolean pass = false;
			switch (search_mode) {
				case Xog_search_suggest_mgr.Tid_search_mode_all_pages_v2:			pass = Search_by_all_pages_v2(); break;
				case Xog_search_suggest_mgr.Tid_search_mode_all_pages_v1:			pass = Search_by_all_pages_v1(); break;
				case Xog_search_suggest_mgr.Tid_search_mode_search:					pass = Search_by_search(); break;
			}
			if (!pass) return;
			GfoInvkAble_.InvkCmd(app.Gui_mgr().Kit().New_cmd_sync(mgr), Xog_search_suggest_mgr.Invk_notify);
			working = false;
		} 
		catch(Exception e) {
			app.Usr_dlg().Prog_many("", "", "error during search: err=~{0}", Err_.Message_gplx_full(e));
		}
	}
	private boolean Search_by_all_pages_v2() {
		rslts_2.Clear();
		Xoa_ttl search_ttl = Xoa_ttl.parse(wiki, search_bry); if (search_ttl == null) return false;
		byte[] search_ttl_bry = search_ttl.Page_db();
		wiki.Db_mgr().Load_mgr().Load_ttls_for_search_suggest(this, rslts_2, search_ttl.Ns(), search_ttl_bry, max_results, all_pages_min, all_pages_extend, true, false);
		return true;
	}
	private boolean Search_by_all_pages_v1() {
		rslts_2.Clear();
		Xowd_page_itm rslt_nxt = new Xowd_page_itm();
		Xowd_page_itm rslt_prv = new Xowd_page_itm();
		Xoa_ttl search_ttl = Xoa_ttl.parse(wiki, search_bry); if (search_ttl == null) return false;
		byte[] search_ttl_bry = search_ttl.Page_db();
		List_adp page_list = List_adp_.new_();
		wiki.Db_mgr().Load_mgr().Load_ttls_for_all_pages(this, page_list, rslt_nxt, rslt_prv, Int_obj_ref.zero_(), wiki.Ns_mgr().Ns_main(), search_ttl_bry, max_results, all_pages_min, all_pages_extend, true, false);
		Xowd_page_itm[] page_ary = (Xowd_page_itm[])page_list.To_ary_and_clear(Xowd_page_itm.class);
		int idx = 0, page_ary_len = page_ary.length;
		for (int i = 0; i < page_ary_len; i++) {
			Xowd_page_itm page = page_ary[i];
			if (page != null) {
				if (!Bry_.Has_at_bgn(page.Ttl_page_db(), search_ttl_bry)) continue;	// look-ahead may return other titles that don't begin with search; ignore
				if (page.Text_len() > all_pages_min) {
					rslts_2.Add(page);
					idx++;
				}
			}
			if (idx == max_results) break;
		}
		return true;
	}
	private boolean Search_by_search() {
		Xosrh_rslt_grp rv = searcher.Itms_per_page_(max_results).Search(tmp_bfr, wiki, search_bry, 0, searcher, this);
		if (canceled) {working = false; return false;}
		rslts_1.Clear();
		int len = rv.Itms_len();
		for (int i = 0; i < len; i++)
			rslts_1.Add(rv.Itms_get_at(i));
		if (canceled) {working = false; return false;}
		rslts_1.Sort_by(Xowd_page_itm_sorter.EnyLenDsc);
		if (canceled) {working = false; return false;}
		if (len > max_results) len = max_results;
		rslts_2.Clear();
		for (int i = 0; i < len; i++)
			rslts_2.Add(rslts_1.Get_at(i));
		if (canceled) {working = false; return false;}
		rslts_2.Sort_by(Xowd_page_itm_sorter.IdAsc);
		wiki.Db_mgr().Load_mgr().Load_by_ids(this, rslts_2, 0, len);
		rslts_2.Sort_by(Xowd_page_itm_sorter.TitleAsc);
		if (canceled) {working = false; return false;}
		return true;
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_search))		Search();
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}	public static final String Invk_search = "search";
}
