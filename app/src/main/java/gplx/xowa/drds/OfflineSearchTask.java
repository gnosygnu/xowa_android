package gplx.xowa.drds;
import android.os.Message;
import android.webkit.JavascriptInterface;

import org.wikipedia.concurrency.SaneAsyncTask;
import org.wikipedia.page.PageTitle;
import org.wikipedia.search.SearchResults;
import org.wikipedia.Site;
import org.wikipedia.search.SearchResultsFragment;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import gplx.Bool_;
import gplx.Int_;
import gplx.String_;
import gplx.List_adp; import gplx.List_adp_;
import gplx.Cancelable; import gplx.Cancelable_;
import gplx.Tfds;
import gplx.core.consoles.Console_adp__sys;
import gplx.core.threads.Thread_adp_;
import gplx.xowa.addons.wikis.searchs.searchers.Srch_search_qry;
import gplx.xowa.addons.wikis.searchs.searchers.rslts.Srch_rslt_cbk;
import gplx.xowa.addons.wikis.searchs.searchers.rslts.Srch_rslt_list;
import gplx.xowa.addons.wikis.searchs.searchers.rslts.Srch_rslt_row;

public class OfflineSearchTask extends SaneAsyncTask<SearchResults> implements Srch_rslt_cbk {
    private final Site site;
    private final String search_str;
    private final SearchResultsFragment fragment;
//    private final OfflineSearchHandler handler;
    private Cancelable cxl = Cancelable_.New_proxy();
    private int rslts_count;
    private long search_time_bgn;
    private boolean new_search;
    public boolean rslts_are_done;
    public boolean rslts_are_enough;
    public OfflineSearchTask(Site site, SearchResultsFragment fragment, String search_str, int rslts_count, boolean new_search) {
        super(SINGLE_THREAD);
        this.site = site;
        this.fragment = fragment;
        this.search_str = search_str;
        this.rslts_count = rslts_count;
        this.new_search = new_search;
        this.search_time_bgn = System.nanoTime();
    }
    @Override public SearchResults performTask() throws Throwable {
        if (cxl.Canceled()) return null;
//        Console_adp__sys.Instance.Write_str_w_nl("starting:" + search_str);
        Xod_app_mgr.Instance.Search_titles(cxl, this, site.getDomain(), search_str, rslts_count, rslts_count + 10);
        return null;
    }
    @Override public void cancel() {
        cxl.Cancel();
        super.cancel();
    }
    @Override public void onBeforeExecute() {fragment.Task__bgn(new_search);}
    @Override public void onFinish(SearchResults results) {}
    @Override public void On_cancel() {}
    @Override public void On_rslts_found(Srch_search_qry qry, Srch_rslt_list rslts_list, int rslts_bgn, int rslts_end) {
//        if (!String_.Eq(search_str, String_.new_u8(qry.Phrase.Orig))) return;
//        if (!String_.Eq(search_str, handler.search_term)) return;
        this.rslts_are_enough = rslts_list.Rslts_are_enough;
        this.rslts_are_done = rslts_list.Rslts_are_done;
        int rslts_len = rslts_list.Len();
        final SearchResults rv = new SearchResults();
        rv.SearchTerm = search_str;
//        Console_adp__sys.Instance.Write_str_w_nl("filling:" + String_.new_u8(qry.Phrase.Orig));
        for (int i = rslts_bgn; i < rslts_end; ++i) {
            if (i >= rslts_len) continue;
            if (i + rslts_bgn < qry.Slab_bgn) continue;
            Srch_rslt_row rslt = (Srch_rslt_row) rslts_list.Get_at(i);
            PageTitle pageTitle = new PageTitle(String_.new_u8(rslt.Page_ttl_wo_ns), site);
            pageTitle.Search_score = rslt.Page_score;
            pageTitle.Display_text_xowa = String_.new_u8(rslt.Page_ttl_display(Bool_.Y));
//            Console_adp__sys.Instance.Write_str_w_nl(pageTitle.Display_text_xowa);
            rv.getPageTitles().add(pageTitle);
        }
        final boolean disable_progress = (rslts_are_enough || rslts_are_done);
        fragment.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragment.Task__end(rv, search_time_bgn, search_str, disable_progress);
            }
        });

//        Message msg = handler.obtainMessage();
//        msg.what = OfflineSearchHandler.Msg__display;
//        msg.obj = rv;
//        msg.arg1 = (rslts_are_enough || rslts_are_done) ? 1 : 0; // disable_progress
//        handler.dispatchMessage(msg);
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
