package gplx.xowa.drds;
import android.os.Message;

import org.wikipedia.concurrency.SaneAsyncTask;
import org.wikipedia.page.Page;
import org.wikipedia.page.PageTitle;
import org.wikipedia.search.SearchResults;
import org.wikipedia.Site;
import org.wikipedia.search.SearchResultsFragment;

import gplx.Bool_;
import gplx.Cancelable;
import gplx.Cancelable_;
import gplx.Int_;
import gplx.List_adp;
import gplx.List_adp_;
import gplx.String_;
import gplx.core.threads.Thread_adp_;
import gplx.xowa.addons.searchs.searchers.itms.Srch2_rslt_cbk;
import gplx.xowa.addons.searchs.searchers.itms.Srch2_rslt_row;
import gplx.xowa.addons.searchs.v1s.Srch_rslt_itm;
import gplx.xowa.addons.searchs.v1s.Srch_rslt_lnr;
import gplx.xowa.langs.Xoa_lang_mgr;

public class OfflineSearchTask extends SaneAsyncTask<SearchResults> implements Srch2_rslt_cbk {
    private final Site site;
    private final String search_str;
    private final SearchResultsFragment fragment;
    private final OfflineSearchHandler handler;
    private Cancelable cxl = Cancelable_.New_proxy();
    private final List_adp cached_results = List_adp_.new_();
    private long prv_time;
    private int prv_count;
    public OfflineSearchTask(Site site, SearchResultsFragment fragment, OfflineSearchHandler handler, String search_str) {
        super(SINGLE_THREAD);
        this.site = site;
        this.fragment = fragment;
        this.handler = handler;
        this.search_str = search_str;
    }
//    public SearchResults performTask2() throws Throwable {
//        String[] words = String_.Split(search_str, ' ');
//        String[] ary = OfflinePageLoadStrategy.xo_app_mgr.Search_titles(cxl, this, site.getDomain(), Make_search_str(words, Bool_.N));
//        SearchResults results = Fill_results(site, ary, 0, 250);
//        Message msg = handler.obtainMessage();
//        msg.what = OfflineSearchHandler.Msg__display;
//        msg.arg1 = 0;
//        msg.obj = results;
//        handler.dispatchMessage(msg);
//
//        ary = OfflinePageLoadStrategy.xo_app_mgr.Search_titles(cxl, this, site.getDomain(), Make_search_str(words, Bool_.Y));
//        results = Fill_results(site, ary, 0, 250);
//        return results;
//    }
    @Override public SearchResults performTask() throws Throwable {
        // String[] words = String_.Split(search_str, ' ');
        rslt_list.Clear();;
        Xod_app_mgr.Instance.Search_titles(cxl, this, site.getDomain(), Make_search_str(String_.Split(search_str, ' '), true));
        return null;
    }
    @Override public void cancel() {
        cxl.Cancel();
//        while (!cxl.Cancel_ackd())
//            Thread_adp_.Sleep(500);
        super.cancel();
    }
    @Override public void onBeforeExecute() {fragment.Task__bgn();}
    @Override public void onFinish(SearchResults results) {Send_update_message(true);}
    private void Send_update_message(boolean clear) {
        cached_results.Sort();
        SearchResults rv = new SearchResults();
        int count = cached_results.Count();
        for (int i = 0; i < count; ++i) {
            PageTitle pageTitle = (PageTitle)cached_results.Get_at(i);
            rv.getPageTitles().add(pageTitle);
        }

        Message msg = handler.obtainMessage();
        msg.what = OfflineSearchHandler.Msg__display;
        msg.obj = rv;
        msg.arg1 = clear ? 1 : 0;
        handler.dispatchMessage(msg);
    }
    public void Notify_rslt_found(Srch_rslt_itm new_row) {
        if (cached_results.Count() > 1000) { // cancel if too many
            this.cancel();
            this.Send_update_message(true);
            return;
        }
        PageTitle result = new PageTitle(String_.new_u8(new_row.page_ttl.Page_txt()), site);
        result.Search_score = new_row.page_score;
        cached_results.Add(result);
        int cur_count = cached_results.Count();
        long cur_time = System.currentTimeMillis();
        if ((cur_time - prv_time) >= 2000)                          // update again if 2 seconds since last update
            prv_time = cur_time;
        else if (prv_count < 10 || (cur_count - prv_count) >= 16)   // update again if < 10 results or more than 16 new results
            prv_count = cur_count;
        else                                                        // otherwise exit
            return;
        Send_update_message(false);
    }
    private static SearchResults Fill_results(Site site, String[] ary, int ary_bgn, int ary_end) {
        SearchResults rv = new SearchResults();
        int ary_len = ary.length;
        for (int i = ary_bgn; i < ary_end; ++i) {
            if (i >= ary_len) break;
            String itm = ary[i];
            rv.getPageTitles().add(new PageTitle(itm, site));
        }
        return rv;
    }
    private static String Make_search_str(String[] words, boolean wildcard) {
        String rv = "";
        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            if (String_.Len(word) < 3) continue;
            if (word.length() != 0) rv += " ";
            rv += word;
            if (wildcard && (i == words.length - 1)) rv += "*";
        }
        return rv;
    }

    private final List_adp rslt_list = List_adp_.new_();
    @Override
    public void On_rslt_found(Srch2_rslt_row rslt) {
        rslt_list.Add(rslt);
    }
    @Override public void On_rdr_done() {
        if (cached_results.Count() > 1000) { // cancel if too many
            this.cancel();
            this.Send_update_message(true);
            return;
        }
        rslt_list.Sort_by(Srch2_rslt_row__score__desc.Instance);
        int rslt_list_len = rslt_list.Len();
        for (int i = 0; i < rslt_list_len; ++i) {
            Srch2_rslt_row rslt = (Srch2_rslt_row)rslt_list.Get_at(i);
            PageTitle result = new PageTitle(String_.new_u8(rslt.Page_ttl_wo_ns), site);
            result.Search_score = rslt.Page_score;
            cached_results.Add(result);
        }
        rslt_list.Clear();;
        int cur_count = cached_results.Count();
        long cur_time = System.currentTimeMillis();
        if ((cur_time - prv_time) >= 2000)                          // update again if 2 seconds since last update
            prv_time = cur_time;
        else if (prv_count < 10 || (cur_count - prv_count) >= 16)   // update again if < 10 results or more than 16 new results
            prv_count = cur_count;
        else                                                        // otherwise exit
            return;
        Send_update_message(false);
    }
}
class Srch2_rslt_row__score__desc implements gplx.core.lists.ComparerAble {
    public int compare(Object lhsObj, Object rhsObj) {
        Srch2_rslt_row lhs = (Srch2_rslt_row)lhsObj;
        Srch2_rslt_row rhs = (Srch2_rslt_row)rhsObj;
        return -Int_.Compare(lhs.Page_score, rhs.Page_score);
    }
    public static final Srch2_rslt_row__score__desc Instance = new Srch2_rslt_row__score__desc();
}
