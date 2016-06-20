package org.wikipedia.page.bottomcontent;

import android.graphics.Paint;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.wikipedia.page.PageActivityLongPressHandler;
import org.wikipedia.page.PageLongPressHandler;
import org.wikipedia.page.PageTitle;
import org.wikipedia.R;
import org.wikipedia.WikipediaApp;
import org.wikipedia.analytics.SuggestedPagesFunnel;
import org.wikipedia.bridge.CommunicationBridge;
import org.wikipedia.history.HistoryEntry;
import org.wikipedia.page.LinkHandler;
import org.wikipedia.page.LinkMovementMethodExt;
import org.wikipedia.page.Page;
import org.wikipedia.page.PageActivity;
import org.wikipedia.page.PageFragment;
import org.wikipedia.page.SuggestionsTask;
import org.wikipedia.search.SearchResults;
import org.wikipedia.util.log.L;
import org.wikipedia.views.ConfigurableListView;
import org.wikipedia.views.GoneIfEmptyTextView;
import org.wikipedia.views.ObservableWebView;
import org.wikipedia.views.ConfigurableTextView;

import java.util.List;

import static org.wikipedia.util.L10nUtil.getStringForArticleLanguage;
import static org.wikipedia.util.L10nUtil.formatDateRelative;
import static org.wikipedia.util.UriUtil.visitInExternalBrowser;

public class BottomContentHandler implements BottomContentInterface,
                                                ObservableWebView.OnScrollChangeListener,
                                                ObservableWebView.OnContentHeightChangedListener {
    private static final String TAG = "BottomContentHandler";
    private final PageFragment parentFragment;
    private final CommunicationBridge bridge;
    private final WebView webView;
    private final LinkHandler linkHandler;
    private PageTitle pageTitle;
    private final PageActivity activity;
    private final WikipediaApp app;
    private float displayDensity;
    private boolean firstTimeShown = false;

    private View bottomContentContainer;
    private TextView pageLastUpdatedText;
    private TextView pageLicenseText;
    private View readMoreContainer;
    private ConfigurableListView readMoreList;

    private SuggestedPagesFunnel funnel;
    private SearchResults readMoreItems;

    public BottomContentHandler(PageFragment parentFragment,
                                CommunicationBridge bridge, ObservableWebView webview,
                                LinkHandler linkHandler, ViewGroup hidingView) {
        this.parentFragment = parentFragment;
        this.bridge = bridge;
        this.webView = webview;
        this.linkHandler = linkHandler;
        activity = (PageActivity) parentFragment.getActivity();
        app = (WikipediaApp) activity.getApplicationContext();
        displayDensity = activity.getResources().getDisplayMetrics().density;

        bottomContentContainer = hidingView;
        webview.addOnScrollChangeListener(this);
        webview.addOnContentHeightChangedListener(this);

        /* XOWA: comment out bottom-panel; DATE:2016-02-06
        pageLastUpdatedText = (TextView) bottomContentContainer.findViewById(R.id.page_last_updated_text);
        pageLicenseText = (TextView) bottomContentContainer.findViewById(R.id.page_license_text);
        readMoreContainer = bottomContentContainer.findViewById(R.id.read_more_container);
        readMoreList = (ConfigurableListView) bottomContentContainer.findViewById(R.id.read_more_list);
        */

        /* XOWA: comment out bottom-panel; DATE:2016-02-06
        TextView pageExternalLink = (TextView) bottomContentContainer.findViewById(R.id.page_external_link);
        pageExternalLink.setPaintFlags(pageExternalLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        pageExternalLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visitInExternalBrowser(activity, Uri.parse(pageTitle.getMobileUri()));
            }
        });
        PageLongPressHandler.ListViewContextMenuListener contextMenuListener = new LongPressHandler(activity);
        new PageLongPressHandler(activity, readMoreList, HistoryEntry.SOURCE_INTERNAL_LINK,
                contextMenuListener);
        // set up pass-through scroll functionality for the ListView
        readMoreList.setOnTouchListener(new View.OnTouchListener() {
            private int touchSlop = ViewConfiguration.get(readMoreList.getContext())
                                                     .getScaledTouchSlop();
            private boolean slopReached;
            private boolean doingSlopEvent;
            private boolean isPressed = false;
            private float startY;
            private float amountScrolled;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked() & MotionEvent.ACTION_MASK;
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        isPressed = true;
                        startY = event.getY();
                        amountScrolled = 0;
                        slopReached = false;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (isPressed && !doingSlopEvent) {
                            int contentHeight = (int)(webView.getContentHeight() * displayDensity);
                            int maxScroll = contentHeight - webView.getScrollY()
                                            - webView.getHeight();
                            int scrollAmount = Math.min((int) (startY - event.getY()), maxScroll);
                            // manually scroll the WebView that's underneath us...
                            webView.scrollBy(0, scrollAmount);
                            amountScrolled += scrollAmount;
                            if (Math.abs(amountScrolled) > touchSlop && !slopReached) {
                                slopReached = true;
                                // send an artificial Move event that scrolls it by an amount
                                // that's greater than the touch slop, so that the currently
                                // highlighted item is unselected.
                                MotionEvent moveEvent = MotionEvent.obtain(event);
                                moveEvent.setLocation(event.getX(), event.getY() + touchSlop * 2);
                                doingSlopEvent = true;
                                readMoreList.dispatchTouchEvent(moveEvent);
                                doingSlopEvent = false;
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        isPressed = false;
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        */
        // preload the display density, since it will be used in a lot of places
        displayDensity = activity.getResources().getDisplayMetrics().density;
        // hide ourselves by default
        hide();
    }

    @Override
    public void onScrollChanged(int oldScrollY, int scrollY, boolean isHumanScroll) {
        if (bottomContentContainer.getVisibility() == View.GONE) {
            return;
        }
        int contentHeight = (int)(webView.getContentHeight() * displayDensity);
        int bottomOffset = contentHeight - scrollY - webView.getHeight();
        int bottomHeight = bottomContentContainer.getHeight();
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) bottomContentContainer.getLayoutParams();
        if (bottomOffset > bottomHeight) {
            if (params.bottomMargin != -bottomHeight) {
                params.bottomMargin = -bottomHeight;
                params.topMargin = 0;
                bottomContentContainer.setLayoutParams(params);
                bottomContentContainer.setVisibility(View.INVISIBLE);
            }
        } else {
            params.bottomMargin = -bottomOffset;
            params.topMargin = -bottomHeight;
            bottomContentContainer.setLayoutParams(params);
            if (bottomContentContainer.getVisibility() != View.VISIBLE) {
                bottomContentContainer.setVisibility(View.VISIBLE);
            }
            if (!firstTimeShown && readMoreItems != null) {
                firstTimeShown = true;
// funnel.logSuggestionsShown(pageTitle, readMoreItems.getPageTitles()); // XOWA.READMORE
            }
        }
    }

    @Override
    public void onContentHeightChanged(int contentHeight) {
        if (bottomContentContainer.getVisibility() != View.VISIBLE) {
            return;
        }
        // trigger a manual scroll event to update our position
        onScrollChanged(webView.getScrollY(), webView.getScrollY(), false);
    }

    /**
     * Hide the bottom content entirely.
     * It can only be shown again by calling beginLayout()
     */
    public void hide() {
        bottomContentContainer.setVisibility(View.GONE);
    }

    public void beginLayout() {
        firstTimeShown = false;
        setupAttribution();
        if (parentFragment.getPage().couldHaveReadMoreSection()) {
            preRequestReadMoreItems(activity.getLayoutInflater());
        } else {
            bottomContentContainer.findViewById(R.id.read_more_container).setVisibility(View.GONE);
            layoutContent();
        }
    }

    private void layoutContent() {
        /* XOWA: comment out bottom-panel; DATE:2016-02-06
        if (!parentFragment.isAdded()) {
            return;
        }
        bottomContentContainer.setVisibility(View.INVISIBLE);
        // keep trying until our layout has a height...
        if (bottomContentContainer.getHeight() == 0) {
            final int postDelay = 50;
            bottomContentContainer.postDelayed(new Runnable() {
                @Override
                public void run() {
                    layoutContent();
                }
            }, postDelay);
            return;
        }

        // calculate the height of the listview, based on the number of items inside it.
        ListAdapter adapter = readMoreList.getAdapter();
        if (adapter != null && adapter.getCount() > 0) {
            View item = View.inflate(readMoreList.getContext(), R.layout.item_page_list_entry, null);
            item.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            ViewGroup.LayoutParams params = readMoreList.getLayoutParams();
            params.height = adapter.getCount() * item.getMeasuredHeight();
            readMoreList.setLayoutParams(params);
        }

        readMoreList.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        bottomContentContainer.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        // pad the bottom of the webview, to make room for ourselves
        int totalHeight = bottomContentContainer.getMeasuredHeight();
        JSONObject payload = new JSONObject();
        try {
            payload.put("paddingBottom", (int)(totalHeight / displayDensity));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        bridge.sendMessage("setPaddingBottom", payload);
        // ^ sending the padding event will guarantee a ContentHeightChanged event to be triggered,
        // which will update our margin based on the scroll offset, so we don't need to do it here.
        */
    }

    private void setupAttribution() {
        Page page = parentFragment.getPage();
        /* XOWA: comment out bottom-panel; DATE:2016-02-06
        pageLicenseText.setText(Html.fromHtml(activity.getString(R.string.content_license_html)));
        pageLicenseText.setMovementMethod(new LinkMovementMethodExt(linkHandler));
        */

        // Don't display last updated message for main page or file pages, because it's always wrong
        if (page.isMainPage() || page.isFilePage()) {
        // pageLastUpdatedText.setVisibility(View.GONE); // XOWA: comment out bottom-panel; DATE:2016-02-06
        } else {
            /*XOWA: comment out bottom-panel; DATE:2016-02-06
            String lastUpdatedHtml = "<a href=\"" + page.getTitle().getUriForAction("history")
                    + "\">" + activity.getString(R.string.last_updated_text,
                    formatDateRelative(page.getPageProperties().getLastModified())
                            + "</a>");
            pageLastUpdatedText.setText(Html.fromHtml(lastUpdatedHtml));
            pageLastUpdatedText.setMovementMethod(new LinkMovementMethodExt(linkHandler));
            pageLastUpdatedText.setVisibility(View.VISIBLE);
            */
            Update_meta(page);
        }
    }
    private void Update_meta(Page page) {   // XOWA
        String browser = "";
        String updated = "";
        String license = "";
        if (!page.getTitle().isSpecial()) {  // Special should not show "View page in browser" and "Last updated"; DATE: 2016-06-14
            browser = "<a href=\"" + page.getTitle().getUriForAction("view") + "\"/>" + activity.getString(R.string.page_view_in_browser) + "</a>";
            updated = "<a href=\"" + page.getTitle().getUriForAction("history")+ "\">" + activity.getString(R.string.last_updated_text, formatDateRelative(page.getPageProperties().getLastModified())+ "</a>");
            license = activity.getString(R.string.content_license_html);
        }
        JSONObject wrapper = new JSONObject();
        try {
            // whatever we pass to this event will be passed back to us by the WebView!
            wrapper.put("browser", browser);
            wrapper.put("license", license);
            wrapper.put("updated", updated);
        } catch (JSONException e) {
            L.logRemoteErrorIfProd(e);
        }
        bridge.sendMessage("xowa__meta__update", wrapper);
    }
    private void preRequestReadMoreItems(final LayoutInflater layoutInflater) {
        if (parentFragment.getPage().isMainPage()) {
            new MainPageReadMoreTopicTask(activity) {
                @Override
                public void onFinish(PageTitle myTitle) {
                    requestReadMoreItems(layoutInflater, myTitle);
                }

                @Override
                public void onCatch(Throwable caught) {
                    // Read More titles are expendable.
                    Log.w(TAG, "Error while getting Read More topic for main page.", caught);
                    // but lay out the bottom content anyway:
                    layoutContent();
                }
            }.execute();
        } else {
            requestReadMoreItems(layoutInflater, pageTitle);
        }
    }

    private void requestReadMoreItems(final LayoutInflater layoutInflater,
                                      final PageTitle myTitle) {
        if (myTitle == null || TextUtils.isEmpty(myTitle.getPrefixedText())) {
            hideReadMore();
            layoutContent();
            return;
        }
        final int maxResultItems = 3;
        final int numRequestItems = 5;
        new SuggestionsTask(app.getAPIForSite(myTitle.getSite()), myTitle.getSite(),
                myTitle.getPrefixedText(), numRequestItems, maxResultItems,
                (int)(parentFragment.getActivity().getResources().getDimension(R.dimen.leadImageWidth) / displayDensity), false) {
            @Override
            public void onFinish(SearchResults results) {
                readMoreItems = results;
                if (!readMoreItems.getPageTitles().isEmpty()) {
                    // If there are results, set up section and make sure it's visible
                    setUpReadMoreSection(layoutInflater, readMoreItems);
                    showReadMore();
                } else {
                    // If there's no results, just hide the section
                    hideReadMore();
                }
                layoutContent();
            }

            @Override
            public void onCatch(Throwable caught) {
                // Read More titles are expendable.
                Log.w(TAG, "Error while fetching Read More titles.", caught);
                // but lay out the bottom content anyway:
                layoutContent();
            }
        }.execute();
    }

    private void hideReadMore() {
//        readMoreContainer.setVisibility(View.GONE);
    }

    private void showReadMore() {
//        if (parentFragment.isAdded()) {   // XOWA
//            ((ConfigurableTextView) readMoreContainer.findViewById(R.id.read_more_header))
//                    .setText(getStringForArticleLanguage(parentFragment.getTitle(), R.string.read_more_section),
//                                     pageTitle.getSite().getLanguageCode());
//        }
//        readMoreContainer.setVisibility(View.VISIBLE);
    }

    public PageTitle getTitle() {
        return pageTitle;
    }

    public void setTitle(PageTitle newTitle) {
        pageTitle = newTitle;
        funnel = new SuggestedPagesFunnel(app, true);
    }

    private void setUpReadMoreSection(LayoutInflater layoutInflater, final SearchResults results) {
// XOWA.READMORE
//        final ReadMoreAdapter adapter = new ReadMoreAdapter(layoutInflater, results.getPageTitles());
//        readMoreList.setAdapter(adapter, pageTitle.getSite().getLanguageCode());
//        readMoreList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                PageTitle title = (PageTitle) adapter.getItem(position);
//                HistoryEntry historyEntry = new HistoryEntry(title, HistoryEntry.SOURCE_INTERNAL_LINK);
//                activity.displayNewPage(title, historyEntry);
//                funnel.logSuggestionClicked(pageTitle, results.getPageTitles(), position);
//            }
//        });
//        adapter.notifyDataSetChanged();
    }

    private class LongPressHandler extends PageActivityLongPressHandler
            implements PageLongPressHandler.ListViewContextMenuListener {
        private int lastPosition;
        LongPressHandler(@NonNull PageActivity activity) {
            super(activity);
        }

        @Override
        public PageTitle getTitleForListPosition(int position) {
            lastPosition = position;
            return (PageTitle) readMoreList.getAdapter().getItem(position);
        }

        @Override
        public void onOpenLink(PageTitle title, HistoryEntry entry) {
            super.onOpenLink(title, entry);
            funnel.logSuggestionClicked(pageTitle, readMoreItems.getPageTitles(), lastPosition);
        }

        @Override
        public void onOpenInNewTab(PageTitle title, HistoryEntry entry) {
            super.onOpenInNewTab(title, entry);
            funnel.logSuggestionClicked(pageTitle, readMoreItems.getPageTitles(), lastPosition);
        }
    }

    private final class ReadMoreAdapter extends BaseAdapter {
        private final LayoutInflater inflater;
        private final List<PageTitle> results;

        private ReadMoreAdapter(LayoutInflater inflater, List<PageTitle> results) {
            this.inflater = inflater;
            this.results = results;
        }

        @Override
        public int getCount() {
            return results == null ? 0 : results.size();
        }

        @Override
        public Object getItem(int position) {
            return results.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_page_list_entry, parent, false);
            }
            TextView pageTitleText = (TextView) convertView.findViewById(R.id.page_list_item_title);
            PageTitle result = (PageTitle) getItem(position);
            pageTitleText.setText(result.getDisplayText());

            GoneIfEmptyTextView descriptionText = (GoneIfEmptyTextView) convertView.findViewById(R.id.page_list_item_description);
            descriptionText.setText(result.getDescription());

            ImageView imageView = (ImageView) convertView.findViewById(R.id.page_list_item_image);
            String thumbnail = result.getThumbUrl();
            if (!app.isImageDownloadEnabled() || thumbnail == null) {
                Picasso.with(parent.getContext())
                        .load(R.drawable.ic_pageimage_placeholder)
                        .into(imageView);
            } else {
                Picasso.with(parent.getContext())
                        .load(thumbnail)
                        .placeholder(R.drawable.ic_pageimage_placeholder)
                        .error(R.drawable.ic_pageimage_placeholder)
                        .into(imageView);
            }

            return convertView;
        }
    }
}
