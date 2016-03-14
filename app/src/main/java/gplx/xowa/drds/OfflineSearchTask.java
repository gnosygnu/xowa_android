package gplx.xowa.drds;
import android.os.Message;

import org.wikipedia.concurrency.SaneAsyncTask;
import org.wikipedia.page.PageTitle;
import org.wikipedia.search.SearchResults;
import org.wikipedia.Site;
import org.wikipedia.search.SearchResultsFragment;

import gplx.Int_;
import gplx.String_;
import gplx.List_adp; import gplx.List_adp_;
import gplx.Cancelable; import gplx.Cancelable_;
import gplx.core.threads.Thread_adp_;
import gplx.xowa.addons.searchs.searchers.rslts.Srch_rslt_cbk;
import gplx.xowa.addons.searchs.searchers.rslts.Srch_rslt_row;

public class OfflineSearchTask extends SaneAsyncTask<SearchResults> implements Srch_rslt_cbk {
    private final Site site;
    private final String search_str;
    private final SearchResultsFragment fragment;
    private final OfflineSearchHandler handler;
    private Cancelable cxl = Cancelable_.New_proxy();
    private final List_adp cached_results = List_adp_.new_();
    private long prv_time;
    private int total_count;
    private int rslts_count;
    private int found_count;
    private boolean new_search;
    public boolean done;
    public OfflineSearchTask(Site site, SearchResultsFragment fragment, OfflineSearchHandler handler, String search_str, int rslts_count, boolean new_search) {
        super(SINGLE_THREAD);
        this.site = site;
        this.fragment = fragment;
        this.handler = handler;
        this.search_str = search_str;
        this.rslts_count = rslts_count;
        this.found_count = 0;
        this.new_search = new_search;
    }
    @Override public SearchResults performTask() throws Throwable {
        Thread_adp_.Sleep(250);
        if (cxl.Canceled()) return null;
        Xod_app_mgr.Instance.Search_titles(cxl, this, site.getDomain(), search_str, rslts_count, rslts_count + 10);
        return null;
    }
    @Override public void cancel() {
        cxl.Cancel();
        super.cancel();
    }
    @Override public void onBeforeExecute() {fragment.Task__bgn(new_search);}
    @Override public void onFinish(SearchResults results) {Send_update_message(true); this.done = true;}
    @Override public void On_rslt_found(Srch_rslt_row rslt) {cached_results.Add(rslt);}
    @Override public void On_rdr_done(boolean lastrdr) {
        int cur_count = cached_results.Count();
        long cur_time = System.currentTimeMillis();
        if ((cur_time - prv_time) >= 2000)                               // update again if 2 seconds since last update
            prv_time = cur_time;
        else if (total_count < 10 || (cur_count - total_count) >= 16)   // update again if < 10 results or more than 16 new results
            total_count = cur_count;
        else                                                            // otherwise exit
            return;
        Send_update_message(false);
    }
    private void Send_update_message(boolean disable_progress) {
        cached_results.Sort_by(Srch_rslt_row__score__desc.Instance);
        SearchResults rv = new SearchResults();
        int cached_len = cached_results.Len();
        for (int i = 0; i < cached_len; ++i) {
            if (i + found_count < rslts_count) continue;
            Srch_rslt_row rslt = (Srch_rslt_row)cached_results.Get_at(i);
            PageTitle pageTitle = new PageTitle(String_.new_u8(rslt.Page_ttl_wo_ns), site);
            pageTitle.Search_score = rslt.Page_score;
            pageTitle.Display_text_xowa = String_.new_u8(rslt.Page_ttl_display);
            rv.getPageTitles().add(pageTitle);
        }
        found_count += cached_len;
        cached_results.Clear();

        Message msg = handler.obtainMessage();
        msg.what = OfflineSearchHandler.Msg__display;
        msg.obj = rv;
        msg.arg1 = disable_progress ? 1 : 0;
        handler.dispatchMessage(msg);
    }
}
class Srch_rslt_row__score__desc implements gplx.core.lists.ComparerAble {
    public int compare(Object lhsObj, Object rhsObj) {
        Srch_rslt_row lhs = (Srch_rslt_row)lhsObj;
        Srch_rslt_row rhs = (Srch_rslt_row)rhsObj;
        return -Int_.Compare(lhs.Page_score, rhs.Page_score);
    }
    public static final Srch_rslt_row__score__desc Instance = new Srch_rslt_row__score__desc();
}
