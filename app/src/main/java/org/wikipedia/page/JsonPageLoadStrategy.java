package org.wikipedia.page;

import android.support.annotation.Nullable;
import org.wikipedia.R;
import org.wikipedia.WikipediaApp;
import org.wikipedia.bridge.CommunicationBridge;
import org.wikipedia.editing.EditHandler;
import org.wikipedia.editing.EditSectionActivity;
import org.wikipedia.history.HistoryEntry;
import org.wikipedia.history.SaveHistoryTask;
import org.wikipedia.page.bottomcontent.BottomContentHandler;
import org.wikipedia.page.bottomcontent.BottomContentInterface;
import org.wikipedia.page.leadimages.LeadImagesHandler;
import org.wikipedia.server.PageLead;
import org.wikipedia.server.PageRemaining;
import org.wikipedia.server.ServiceError;
import org.wikipedia.pageimages.PageImage;
import org.wikipedia.pageimages.PageImagesTask;
import org.wikipedia.savedpages.LoadSavedPageTask;
import org.wikipedia.search.SearchBarHideHandler;
import org.wikipedia.util.DimenUtil;
import org.wikipedia.util.L10nUtil;
import org.wikipedia.util.PageLoadUtil;
import org.wikipedia.util.ResourceUtil;
import org.wikipedia.util.log.L;
import org.wikipedia.views.ObservableWebView;
import org.wikipedia.views.SwipeRefreshLayoutWithScroll;

import org.mediawiki.api.json.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit.RetrofitError;
import retrofit.client.Response;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.wikipedia.util.L10nUtil.getStringsForArticleLanguage;
import static org.wikipedia.util.DimenUtil.calculateLeadImageWidth;

/**
 * Our old page load strategy, which uses the JSON MW API directly and loads a page in multiple steps:
 * First it loads the lead section (sections=0).
 * Then it loads the remaining sections (sections=1-).
 * <p/>
 * This class tracks:
 * - the states the page loading goes through,
 * - a backstack of pages and page positions visited,
 * - and many handlers.
 */
public class JsonPageLoadStrategy implements PageLoadStrategy {
    private static final String TAG = "JsonPageLoad";
    private static final String BRIDGE_PAYLOAD_SAVED_PAGE = "savedPage";

    public static final int STATE_NO_FETCH = 1;
    public static final int STATE_INITIAL_FETCH = 2;
    public static final int STATE_COMPLETE_FETCH = 3;

    private int state = STATE_NO_FETCH;

    /**
     * List of lightweight history items to serve as the backstack for this fragment.
     * Since the list consists of Parcelable objects, it can be saved and restored from the
     * savedInstanceState of the fragment.
     */
    @NonNull private List<PageBackStackItem> backStack;

    @NonNull private final SequenceNumber sequenceNumber = new SequenceNumber();

    /**
     * The y-offset position to which the page will be scrolled once it's fully loaded
     * (or loaded to the point where it can be scrolled to the correct position).
     */
    private int stagedScrollY;
    private int sectionTargetFromIntent;
    private String sectionTargetFromTitle;

    /**
     * Whether to write the page contents to cache as soon as it's loaded.
     */
    private boolean cacheOnComplete = true;

    private interface ErrorCallback {
        void call(@Nullable Throwable error);
    }

    private ErrorCallback networkErrorCallback;

    // copied fields
    private PageViewModel model;
    private PageFragment fragment;
    private CommunicationBridge bridge;
    private PageActivity activity;
    private ObservableWebView webView;
    private SwipeRefreshLayoutWithScroll refreshView;
    private WikipediaApp app;
    private LeadImagesHandler leadImagesHandler;
    private SearchBarHideHandler searchBarHideHandler;
    private EditHandler editHandler;

    private BottomContentInterface bottomContentHandler;

    JsonPageLoadStrategy() {
        backStack = new ArrayList<>();
    }

    @Override
    public void setBackStack(@NonNull List<PageBackStackItem> backStack) {
        this.backStack = backStack;
    }

    @Override
    public void setup(PageViewModel model,
                      PageFragment fragment,
                      SwipeRefreshLayoutWithScroll refreshView,
                      ObservableWebView webView,
                      CommunicationBridge bridge,
                      SearchBarHideHandler searchBarHideHandler,
                      LeadImagesHandler leadImagesHandler) {
        this.model = model;
        this.fragment = fragment;
        activity = (PageActivity) fragment.getActivity();
        this.app = (WikipediaApp) activity.getApplicationContext();
        this.refreshView = refreshView;
        this.webView = webView;
        this.bridge = bridge;
        this.searchBarHideHandler = searchBarHideHandler;
        this.leadImagesHandler = leadImagesHandler;
    }

    @Override
    public void onActivityCreated(@NonNull List<PageBackStackItem> backStack) {
        setupSpecificMessageHandlers();

        this.backStack = backStack;

        // if we already have pages in the backstack (whether it's from savedInstanceState, or
        // from being stored in the activity's fragment backstack), then load the topmost page
        // on the backstack.
        loadPageFromBackStack();
    }

    private void setupSpecificMessageHandlers() {
        bridge.addListener("onBeginNewPage", new SynchronousBridgeListener() {
            @Override
            public void onMessage(JSONObject messagePayload) {
                try {
                    stagedScrollY = messagePayload.getInt("stagedScrollY");
                    loadPageOnWebViewReady(Cache.valueOf(messagePayload.getString("cachePreference")));
                } catch (JSONException e) {
                    L.logRemoteErrorIfProd(e);
                }
            }
        });
        bridge.addListener("requestSection", new SynchronousBridgeListener() {
            @Override
            public void onMessage(JSONObject messagePayload) {
                try {
                    displayNonLeadSection(messagePayload.getInt("index"),
                            messagePayload.optBoolean(BRIDGE_PAYLOAD_SAVED_PAGE, false));
                } catch (JSONException e) {
                    L.logRemoteErrorIfProd(e);
                }
            }
        });
        bridge.addListener("pageLoadComplete", new SynchronousBridgeListener() {
            @Override
            public void onMessage(JSONObject messagePayload) {
                // Do any other stuff that should happen upon page load completion...
                activity.updateProgressBar(false, true, 0);

                // trigger layout of the bottom content
                // Check to see if the page title has changed (e.g. due to following a redirect),
                // because if it has then the handler needs the new title to make sure it doesn't
                // accidentally display the current article as a "read more" suggestion
                bottomContentHandler.setTitle(model.getTitle());
                bottomContentHandler.beginLayout();
            }
        });

        bottomContentHandler = new BottomContentHandler(fragment, bridge, webView,
                fragment.getLinkHandler(),
                (ViewGroup) fragment.getView().findViewById(R.id.bottom_content_container));
    }

    @Override
    public void backFromEditing(Intent data) {
        //Retrieve section ID from intent, and find correct section, so where know where to scroll to
        sectionTargetFromIntent = data.getIntExtra(EditSectionActivity.EXTRA_SECTION_ID, 0);
        //reset our scroll offset, since we have a section scroll target
        stagedScrollY = 0;
    }

    @Override
    public void onDisplayNewPage(boolean pushBackStack, Cache cachePreference, int stagedScrollY) {
        if (pushBackStack) {
            // update the topmost entry in the backstack, before we start overwriting things.
            updateCurrentBackStackItem();
            pushBackStack();
        }

        state = STATE_NO_FETCH;

        // increment our sequence number, so that any async tasks that depend on the sequence
        // will invalidate themselves upon completion.
        sequenceNumber.increase();

        if (cachePreference == Cache.NONE) {
            // If this is a refresh, don't clear the webview contents
            this.stagedScrollY = stagedScrollY;
            loadPageOnWebViewReady(cachePreference);
        } else {
            // kick off an event to the WebView that will cause it to clear its contents,
            // and then report back to us when the clearing is complete, so that we can synchronize
            // the transitions of our native components to the new page content.
            // The callback event from the WebView will then call the loadPageOnWebViewReady()
            // function, which will continue the loading process.
            leadImagesHandler.hide();
            bottomContentHandler.hide();
            activity.getSearchBarHideHandler().setFadeEnabled(false);
            try {
                JSONObject wrapper = new JSONObject();
                // whatever we pass to this event will be passed back to us by the WebView!
                wrapper.put("sequence", sequenceNumber.get());
                wrapper.put("cachePreference", cachePreference.name());
                wrapper.put("stagedScrollY", stagedScrollY);
                bridge.sendMessage("beginNewPage", wrapper);
            } catch (JSONException e) {
                L.logRemoteErrorIfProd(e);
            }
        }
    }

    private void performActionForState(int forState) {
        if (!fragment.isAdded()) {
            return;
        }
        switch (forState) {
            case STATE_NO_FETCH:
                activity.updateProgressBar(true, true, 0);
                loadLeadSection(sequenceNumber.get());
                break;
            case STATE_INITIAL_FETCH:
                loadRemainingSections(sequenceNumber.get());
                break;
            case STATE_COMPLETE_FETCH:
                editHandler.setPage(model.getPage());
                // kick off the lead image layout
                leadImagesHandler.beginLayout(new LeadImagesHandler.OnLeadImageLayoutListener() {
                    @Override
                    public void onLayoutComplete(int sequence) {
                        if (!fragment.isAdded() || !sequenceNumber.inSync(sequence)) {
                            return;
                        }
                        searchBarHideHandler.setFadeEnabled(leadImagesHandler.isLeadImageEnabled());
                        // when the lead image layout is complete, load the lead section and
                        // the other sections into the webview.
                        displayLeadSection();
                        displayNonLeadSectionForUnsavedPage(1);
                    }
                }, sequenceNumber.get());
                break;
            default:
                // This should never happen
                throw new RuntimeException("Unknown state encountered " + state);
        }
    }

    private void setState(int state) {
        if (!fragment.isAdded()) {
            return;
        }
        this.state = state;
        activity.supportInvalidateOptionsMenu();

        // FIXME: Move this out into a PageComplete event of sorts
        if (state == STATE_COMPLETE_FETCH) {
            fragment.setupToC(model, isFirstPage());

            //add the page to cache!
            if (cacheOnComplete) {
                app.getPageCache().put(model.getTitleOriginal(), model.getPage(),
                        new PageCache.CachePutListener() {
                            @Override
                            public void onPutComplete() {
                            }

                            @Override
                            public void onPutError(Throwable e) {
                                Log.e(TAG, "Failed to add page to cache.", e);
                            }
                        });
            }
        }
    }

    @Override
    public boolean isLoading() {
        return state != STATE_COMPLETE_FETCH;
    }

    private void loadPageOnWebViewReady(Cache cachePreference) {
        // stage any section-specific link target from the title, since the title may be
        // replaced (normalized)
        sectionTargetFromTitle = model.getTitle().getFragment();

        L10nUtil.setupDirectionality(model.getTitle().getSite().getLanguageCode(), Locale.getDefault().getLanguage(),
                bridge);

        // TODO: Fix possible race condition when navigating from history fragment
        if (model.getCurEntry().getSource() == HistoryEntry.SOURCE_SAVED_PAGE) {
            state = STATE_NO_FETCH;
            loadSavedPage(new ErrorCallback() {
                @Override
                public void call(Throwable error) {
                    /*
                    If anything bad happens during loading of a saved page, then simply bounce it
                    back to the online version of the page, and re-save the page contents locally when it's done.
                     */
                    Log.d("LoadSavedPageTask", "Error loading saved page: " + error.getMessage());
                    error.printStackTrace();
                    fragment.refreshPage(true);
                }
            });
        } else {
            switch (cachePreference) {
                case PREFERRED:
                    loadPageFromCache(new ErrorCallback() {
                        @Override
                        public void call(Throwable cacheError) {
                            loadPageFromNetwork(new ErrorCallback() {
                                @Override
                                public void call(final Throwable networkError) {
                                    loadSavedPage(new ErrorCallback() {
                                        @Override
                                        public void call(Throwable savedError) {
                                            fragment.commonSectionFetchOnCatch(networkError);
                                        }
                                    });
                                }
                            });
                        }
                    });
                    break;
                case FALLBACK:
                    loadPageFromNetwork(new ErrorCallback() {
                        @Override
                        public void call(final Throwable networkError) {
                            loadPageFromCache(new ErrorCallback() {
                                @Override
                                public void call(Throwable cacheError) {
                                    loadSavedPage(new ErrorCallback() {
                                        @Override
                                        public void call(Throwable savedError) {
                                            fragment.commonSectionFetchOnCatch(networkError);
                                        }
                                    });
                                }
                            });
                        }
                    });
                    break;
                case NONE:
                default:
                    // This is a refresh, don't clear contents in this case
                    loadPageFromNetwork(new ErrorCallback() {
                        @Override
                        public void call(Throwable networkError) {
                            fragment.commonSectionFetchOnCatch(networkError);
                        }
                    });
                    break;
            }
        }
    }

    private void loadPageFromCache(final ErrorCallback errorCallback) {
        app.getPageCache()
                .get(model.getTitleOriginal(), sequenceNumber.get(), new PageCache.CacheGetListener() {
                    @Override
                    public void onGetComplete(Page page, int sequence) {
                        if (!sequenceNumber.inSync(sequence)) {
                            return;
                        }
                        if (page != null) {
                            Log.d(TAG, "Using page from cache: "
                                    + model.getTitleOriginal().getDisplayText());
                            model.setPage(page);
                            model.setTitle(page.getTitle());
                            // Update our history entry, in case the Title was changed (i.e. normalized)
                            final HistoryEntry curEntry = model.getCurEntry();
                            model.setCurEntry(
                                    new HistoryEntry(model.getTitle(), curEntry.getSource()));
                            // load the current title's thumbnail from sqlite
                            updateThumbnail(PageImage.PERSISTENCE_HELPER.getImageUrlForTitle(app, model.getTitle()));
                            // Save history entry...
                            new SaveHistoryTask(model.getCurEntry(), app).execute();
                            // don't re-cache the page after loading.
                            cacheOnComplete = false;
                            state = STATE_COMPLETE_FETCH;
                            setState(state);
                            performActionForState(state);
                            if (fragment.isAdded()) {
                                fragment.onPageLoadComplete();
                            }
                        } else {
                            errorCallback.call(null);
                        }
                    }

                    @Override
                    public void onGetError(Throwable e, int sequence) {
                        Log.e(TAG, "Failed to get page from cache.", e);
                        if (!sequenceNumber.inSync(sequence)) {
                            return;
                        }
                        errorCallback.call(e);
                    }
                });
    }

    private void loadPageFromNetwork(final ErrorCallback errorCallback) {
        networkErrorCallback = errorCallback;
        // and make sure to write it to cache when it's loaded.
        cacheOnComplete = true;
        setState(STATE_NO_FETCH);
        performActionForState(state);
    }

    public void loadSavedPage(final ErrorCallback errorCallback) {
        new LoadSavedPageTask(model.getTitle(), sequenceNumber.get()) {
            @Override
            public void onFinish(Page result) {
                if (!fragment.isAdded() || !sequenceNumber.inSync(getSequence())) {
                    return;
                }

                // Save history entry and page image url
                new SaveHistoryTask(model.getCurEntry(), app).execute();

                model.setPage(result);
                editHandler.setPage(model.getPage());
                // kick off the lead image layout
                leadImagesHandler.beginLayout(new LeadImagesHandler.OnLeadImageLayoutListener() {
                    @Override
                    public void onLayoutComplete(int sequence) {
                        if (!fragment.isAdded() || !sequenceNumber.inSync(sequence)) {
                            return;
                        }
                        searchBarHideHandler.setFadeEnabled(leadImagesHandler.isLeadImageEnabled());
                        // when the lead image is laid out, load the lead section and the rest
                        // of the sections into the webview.
                        displayLeadSection();
                        displayNonLeadSectionForSavedPage(1);

                        setState(STATE_COMPLETE_FETCH);
                    }
                }, sequenceNumber.get());
            }

            @Override
            public void onCatch(Throwable caught) {
                errorCallback.call(caught);
            }
        }.execute();
    }

    private void updateThumbnail(String thumbUrl) {
        model.getTitle().setThumbUrl(thumbUrl);
        model.getTitleOriginal().setThumbUrl(thumbUrl);
        fragment.invalidateTabs();
    }

    private boolean isFirstPage() {
        return backStack.size() <= 1 && !webView.canGoBack();
    }

    /**
     * Pop the topmost entry from the backstack.
     * Does NOT automatically load the next topmost page on the backstack.
     */
    private void popBackStack() {
        if (backStack.isEmpty()) {
            return;
        }
        backStack.remove(backStack.size() - 1);
    }

    /**
     * Push the current page title onto the backstack.
     */
    private void pushBackStack() {
        PageBackStackItem item = new PageBackStackItem(model.getTitleOriginal(), model.getCurEntry());
        backStack.add(item);
    }

    /**
     * Update the current topmost backstack item, based on the currently displayed page.
     * (Things like the last y-offset position should be updated here)
     * Should be done right before loading a new page.
     */
    @Override
    public void updateCurrentBackStackItem() {
        if (backStack.isEmpty()) {
            return;
        }
        PageBackStackItem item = backStack.get(backStack.size() - 1);
        item.setScrollY(webView.getScrollY());
    }

    @Override
    public void loadPageFromBackStack() {
        if (backStack.isEmpty()) {
            return;
        }
        PageBackStackItem item = backStack.get(backStack.size() - 1);
        // display the page based on the backstack item, stage the scrollY position based on
        // the backstack item.
        fragment.displayNewPage(item.getTitle(), item.getHistoryEntry(), Cache.PREFERRED, false,
                item.getScrollY());
        Log.d(TAG, "Loaded page " + item.getTitle().getDisplayText() + " from backstack");
    }

    private void displayLeadSection() {
        Page page = model.getPage();

        sendMarginPayload();

        sendLeadSectionPayload(page);

        sendMiscPayload(page);

        if (webView.getVisibility() != View.VISIBLE) {
            webView.setVisibility(View.VISIBLE);
        }

        refreshView.setRefreshing(false);
        activity.updateProgressBar(true, true, 0);
    }

    private void sendMarginPayload() {
        JSONObject marginPayload = marginPayload();
        bridge.sendMessage("setMargins", marginPayload);
    }

    private JSONObject marginPayload() {
        int margin = DimenUtil.roundedPxToDp(getDimension(R.dimen.content_margin));
        try {
            return new JSONObject()
                    .put("marginLeft", margin)
                    .put("marginRight", margin);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendLeadSectionPayload(Page page) {
        JSONObject leadSectionPayload = leadSectionPayload(page);
        bridge.sendMessage("displayLeadSection", leadSectionPayload);
        Log.d(TAG, "Sent message 'displayLeadSection' for page: " + page.getDisplayTitle());
    }

    private JSONObject leadSectionPayload(Page page) {
        SparseArray<String> localizedStrings = localizedStrings(page);

        try {
            return new JSONObject()
                    .put("sequence", sequenceNumber.get())
                    .put("title", page.getDisplayTitle())
                    .put("section", page.getSections().get(0).toJSON())
                    .put("string_page_similar_titles", localizedStrings.get(R.string.page_similar_titles))
                    .put("string_page_issues", localizedStrings.get(R.string.button_page_issues))
                    .put("string_table_infobox", localizedStrings.get(R.string.table_infobox))
                    .put("string_table_other", localizedStrings.get(R.string.table_other))
                    .put("string_table_close", localizedStrings.get(R.string.table_close))
                    .put("string_expand_refs", localizedStrings.get(R.string.expand_refs))
                    .put("isBeta", app.isPreProdRelease()) // True for any non-production release type
                    .put("siteLanguage", model.getTitle().getSite().getLanguageCode())
                    .put("isMainPage", page.isMainPage())
                    .put("fromRestBase", page.isFromRestBase())
                    .put("apiLevel", Build.VERSION.SDK_INT);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private SparseArray<String> localizedStrings(Page page) {
        return getStringsForArticleLanguage(page.getTitle(),
                ResourceUtil.getIdArray(activity, R.array.page_localized_string_ids));
    }


    private void sendMiscPayload(Page page) {
        JSONObject miscPayload = miscPayload(page);
        bridge.sendMessage("setPageProtected", miscPayload);
    }

    private JSONObject miscPayload(Page page) {
        try {
            return new JSONObject()
                    .put("noedit", !isPageEditable(page)) // Controls whether edit pencils are visible.
                    .put("protect", page.isProtected());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isPageEditable(Page page) {
        return (app.getUserInfoStorage().isLoggedIn() || !isAnonEditingDisabled())
                && !page.isFilePage()
                && !page.isMainPage();
    }

    private boolean isAnonEditingDisabled() {
        return getRemoteConfig().optBoolean("disableAnonEditing", false);
    }

    private JSONObject getRemoteConfig() {
        return app.getRemoteConfig().getConfig();
    }

    private void displayNonLeadSectionForUnsavedPage(int index) {
        displayNonLeadSection(index, false);
    }

    private void displayNonLeadSectionForSavedPage(int index) {
        displayNonLeadSection(index, true);
    }

    private void displayNonLeadSection(int index, boolean savedPage) {
        activity.updateProgressBar(true, false,
                PageActivity.PROGRESS_BAR_MAX_VALUE / model.getPage()
                        .getSections().size() * index);
        if (index > model.getPage().getSections().size()) {
            // TODO: Remove this check if we find that it no longer happens, or if we fix it.
            String errorText = "Section index mismatch!";
            errorText += " modelTitle=" + model.getTitleOriginal().getPrefixedText();
            errorText += ", pageTitle=" + model.getPage().getTitle().getPrefixedText();
            errorText += ", source=" + model.getCurEntry().getSource();
            L.logRemoteErrorIfProd(new RuntimeException(errorText));
            return;
        }
        try {
            final Page page = model.getPage();
            JSONObject wrapper = new JSONObject();
            wrapper.put("sequence", sequenceNumber.get());
            wrapper.put(BRIDGE_PAYLOAD_SAVED_PAGE, savedPage);
            boolean lastSection = index == page.getSections().size();
            if (!lastSection) {
                JSONObject section = page.getSections().get(index).toJSON();
                wrapper.put("section", section);
                wrapper.put("index", index);
                if (sectionTargetFromIntent > 0 && sectionTargetFromIntent < page.getSections().size()) {
                    //if we have a section to scroll to (from our Intent):
                    wrapper.put("fragment",
                            page.getSections().get(sectionTargetFromIntent).getAnchor());
                } else if (sectionTargetFromTitle != null) {
                    //if we have a section to scroll to (from our PageTitle):
                    wrapper.put("fragment", sectionTargetFromTitle);
                } else if (!TextUtils.isEmpty(model.getTitle().getFragment())) {
                    // It's possible, that the link was a redirect and the new title has a fragment
                    // scroll to it, if there was no fragment so far
                    wrapper.put("fragment", model.getTitle().getFragment());
                }
            } else {
                wrapper.put("noMore", true);
            }
            //give it our expected scroll position, in case we need the page to be pre-scrolled upon loading.
            wrapper.put("scrollY",
                    (int) (stagedScrollY / activity.getResources().getDisplayMetrics().density));
            bridge.sendMessage("displaySection", wrapper);

            if (savedPage && lastSection) {
                // rewrite the image URLs in the webview, so that they're loaded from
                // local storage after all the sections have been loaded.
                fragment.readUrlMappings();
            }
        } catch (JSONException e) {
            L.logRemoteErrorIfProd(e);
        }
    }

    @VisibleForTesting
    protected void loadLeadSection(final int startSequenceNum) {
        app.getSessionFunnel().leadSectionFetchStart();
        PageLoadUtil.getApiService(model.getTitle().getSite()).pageLead(
                model.getTitle().getPrefixedText(),
                calculateLeadImageWidth(),
                !app.isImageDownloadEnabled(),
                new PageLead.Callback() {
                    @Override
                    public void success(PageLead pageLead, Response response) {
                        Log.v(TAG, response.getUrl());
                        app.getSessionFunnel().leadSectionFetchEnd();
                        onLeadSectionLoaded(pageLead, startSequenceNum);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, "PageLead error: " + error);
                        commonSectionFetchOnCatch(error, startSequenceNum);
                    }
                });
    }

    private void onLeadSectionLoaded(PageLead pageLead, int startSequenceNum) {
        if (!fragment.isAdded() || !sequenceNumber.inSync(startSequenceNum)) {
            return;
        }
        if (pageLead.hasError()) {
            ServiceError error = pageLead.getError();
            if (error != null) {
                ApiException apiException = new ApiException(error.getTitle(), error.getDetails());
                commonSectionFetchOnCatch(apiException, startSequenceNum);
            } else {
                ApiException apiException
                        = new ApiException("unknown", "unexpected pageLead response");
                commonSectionFetchOnCatch(apiException, startSequenceNum);
            }
            return;
        }

        Page page = pageLead.toPage(model.getTitle());
        model.setPage(page);
        model.setTitle(page.getTitle());

        editHandler.setPage(model.getPage());

        // kick off the lead image layout
        leadImagesHandler.beginLayout(new LeadImagesHandler.OnLeadImageLayoutListener() {
            @Override
            public void onLayoutComplete(int sequence) {
                if (!sequenceNumber.inSync(sequence)) {
                    return;
                }
                searchBarHideHandler.setFadeEnabled(leadImagesHandler.isLeadImageEnabled());
                // when the lead image is laid out, display the lead section in the webview,
                // and start loading the rest of the sections.
                displayLeadSection();
                setState(STATE_INITIAL_FETCH);
                performActionForState(state);
            }
        }, sequenceNumber.get());

        // Update our history entry, in case the Title was changed (i.e. normalized)
        final HistoryEntry curEntry = model.getCurEntry();
        model.setCurEntry(
                new HistoryEntry(model.getTitle(), curEntry.getTimestamp(), curEntry.getSource()));

        // Save history entry and page image url
        new SaveHistoryTask(model.getCurEntry(), app).execute();

        // Fetch larger thumbnail URL from the network, and save it to our DB.
        (new PageImagesTask(app.getAPIForSite(model.getTitle().getSite()), model.getTitle().getSite(),
                Arrays.asList(new PageTitle[]{model.getTitle()}), WikipediaApp.PREFERRED_THUMB_SIZE) {
            @Override
            public void onFinish(Map<PageTitle, String> result) {
                if (result.containsKey(model.getTitle())) {
                    PageImage pi = new PageImage(model.getTitle(), result.get(model.getTitle()));
                    app.getPersister(PageImage.class).upsert(pi, PageImage.PERSISTENCE_HELPER.SELECTION_KEYS);
                    updateThumbnail(result.get(model.getTitle()));
                }
            }

            @Override
            public void onCatch(Throwable caught) {
                // Thumbnails are expendable
                Log.w("SaveThumbnailTask", "Caught " + caught.getMessage(), caught);
            }
        }).execute();
    }

    private void loadRemainingSections(final int startSequenceNum) {
        app.getSessionFunnel().restSectionsFetchStart();
        PageLoadUtil.getApiService(model.getTitle().getSite()).pageRemaining(
                model.getTitle().getPrefixedText(),
                !app.isImageDownloadEnabled(),
                new PageRemaining.Callback() {
                    @Override
                    public void success(PageRemaining pageRemaining, Response response) {
                        Log.v(TAG, response.getUrl());
                        app.getSessionFunnel().restSectionsFetchEnd();
                        onRemainingSectionsLoaded(pageRemaining, startSequenceNum);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, "PageRemaining error: " + error);
                        commonSectionFetchOnCatch(error, startSequenceNum);
                    }
                });
    }

    private void onRemainingSectionsLoaded(PageRemaining pageRemaining, int startSequenceNum) {
        networkErrorCallback = null;
        if (!fragment.isAdded() || !sequenceNumber.inSync(startSequenceNum)) {
            return;
        }

        pageRemaining.mergeInto(model.getPage());

        displayNonLeadSectionForUnsavedPage(1);
        setState(STATE_COMPLETE_FETCH);

        fragment.onPageLoadComplete();
    }

    @VisibleForTesting
    protected void commonSectionFetchOnCatch(Throwable caught, int startSequenceNum) {
        ErrorCallback callback = networkErrorCallback;
        networkErrorCallback = null;
        cacheOnComplete = false;
        state = STATE_COMPLETE_FETCH;
        activity.supportInvalidateOptionsMenu();
        if (!sequenceNumber.inSync(startSequenceNum)) {
            return;
        }
        if (callback != null) {
            callback.call(caught);
        }
    }

    /**
     * Convenience method for hiding all the content of a page.
     */
    @Override
    public void onHidePageContent() {
        bottomContentHandler.hide();
    }

    @Override
    public boolean onBackPressed() {
        popBackStack();
        if (!backStack.isEmpty()) {
            loadPageFromBackStack();
            return true;
        }
        return false;
    }

    @Override
    public void setEditHandler(EditHandler editHandler) {
        this.editHandler = editHandler;
    }

    private float getDimension(@DimenRes int id) {
        return getResources().getDimension(id);
    }

    private Resources getResources() {
        return activity.getResources();
    }

    private abstract class SynchronousBridgeListener implements CommunicationBridge.JSEventListener {
        private static final String BRIDGE_PAYLOAD_SEQUENCE = "sequence";

        @Override
        public void onMessage(String message, JSONObject payload) {
            if (fragment.isAdded() && inSync(payload)) {
                onMessage(payload);
            }
        }

        protected abstract void onMessage(JSONObject payload);

        private boolean inSync(JSONObject payload) {
            return sequenceNumber.inSync(payload.optInt(BRIDGE_PAYLOAD_SEQUENCE,
                    sequenceNumber.get() - 1));
        }
    }

    /**
     * Monotonically increasing sequence number to maintain synchronization when loading page
     * content asynchronously between the Java and JavaScript layers, as well as between synchronous
     * methods and asynchronous callbacks on the UI thread.
     */
    private static class SequenceNumber {
        private int sequence;

        void increase() {
            ++sequence;
        }

        int get() {
            return sequence;
        }

        boolean inSync(int sequence) {
            return this.sequence == sequence;
        }
    }
}
