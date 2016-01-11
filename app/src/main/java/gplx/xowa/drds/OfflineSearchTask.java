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
import gplx.String_;
import gplx.xowa.specials.search.Xows_db_row;
import gplx.xowa.specials.search.Xows_ui_async;

public class OfflineSearchTask extends SaneAsyncTask<SearchResults> implements Xows_ui_async {
    private final Site site;
    private final String search_str;
    private final SearchResultsFragment fragment;
    private final OfflineSearchHandler handler;
    private Cancelable cxl = Cancelable_.New_proxy();
    private final SearchResults cached_results;
    private long prv_time;
    public OfflineSearchTask(Site site, SearchResultsFragment fragment, OfflineSearchHandler handler, String search_str) {
        super(SINGLE_THREAD);
        this.site = site;
        this.fragment = fragment;
        this.handler = handler;
        this.cached_results = new SearchResults();
        this.search_str = search_str;
    }
    @Override
    public SearchResults performTask() throws Throwable {
        String[] words = String_.Split(search_str, ' ');
        String[] ary = OfflinePageLoadStrategy.xo_app_mgr.Search_titles(cxl, this, site.getDomain(), Make_search_str(words, Bool_.N));
        SearchResults results = Fill_results(site, ary, 0, 250);
        Message msg = handler.obtainMessage();
        msg.what = OfflineSearchHandler.Msg__display;
        msg.arg1 = 0;
        msg.obj = results;
        handler.dispatchMessage(msg);

        ary = OfflinePageLoadStrategy.xo_app_mgr.Search_titles(cxl, this, site.getDomain(), Make_search_str(words, Bool_.Y));
        results = Fill_results(site, ary, 0, 250);
        return results;
    }
    @Override public void cancel() {
        cxl.Cancel();
        super.cancel();
    }
    @Override public void onBeforeExecute() {fragment.Task__bgn();}
    @Override public void onFinish(SearchResults results) {
        Message msg = handler.obtainMessage();
        msg.what = OfflineSearchHandler.Msg__display;
        msg.arg1 = 1;
        msg.obj = results;
        handler.dispatchMessage(msg);
    }
    public void Add(Xows_db_row new_row) {
//        if (cached_results.getPageTitles().size() > 1000) return;
//        cached_results.getPageTitles().add(new PageTitle(String_.new_u8(new_row.Page_ttl().Page_url()), site));
//        long cur_time = System.currentTimeMillis();
//        if ((cur_time - prv_time) < 2000) return;
//        prv_time = cur_time;
//        SearchResults new_results = new SearchResults();
//        for (PageTitle pageTitle : cached_results.getPageTitles())
//            new_results.getPageTitles().add(pageTitle);
//        Message msg = handler.obtainMessage();
//        msg.what = OfflineSearchHandler.Msg__display;
//        msg.obj = new_results;
//        handler.dispatchMessage(msg);
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
            if (wildcard) rv += "*";
        }
        return rv;
    }
}
