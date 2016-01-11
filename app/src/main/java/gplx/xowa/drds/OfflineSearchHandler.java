package gplx.xowa.drds;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.wikipedia.Site;
import org.wikipedia.WikipediaApp;
import org.wikipedia.page.PageTitle;
import org.wikipedia.search.SearchResults;
import org.wikipedia.search.SearchResultsFragment;

public class OfflineSearchHandler extends Handler {
    private final WikipediaApp app;
    private final SearchResultsFragment fragment;
    private long search_time_bgn;
    private String search_term;

    public OfflineSearchHandler(WikipediaApp app, SearchResultsFragment fragment) {
        super(Looper.getMainLooper());
        this.app = app; this.fragment = fragment;
    }
    @Override public void handleMessage(final Message msg) {
        switch (msg.what) {
            case Msg__search:
                this.search_term = (String)msg.obj;
                this.search_time_bgn = System.nanoTime();
                OfflineSearchTask offlineSearchTask = new OfflineSearchTask(Xod_app_mgr.Instance.Cur_site(app), fragment, this, search_term);
                fragment.cancelSearchTask();
                fragment.curSearchTask = offlineSearchTask;
                offlineSearchTask.execute();
                break;
            case Msg__display:
                final SearchResults results = (SearchResults) msg.obj;
                fragment.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fragment.Task__end(results, search_time_bgn, search_term, msg.arg1 == 1);
                    }
                });
                break;
        }
    }
    public static final int Msg__search = 0, Msg__display = 1;
}
