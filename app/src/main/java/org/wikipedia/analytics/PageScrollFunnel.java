package org.wikipedia.analytics;

import android.support.annotation.NonNull;

import org.json.JSONObject;
import org.wikipedia.WikipediaApp;

public class PageScrollFunnel extends TimedFunnel {
    private static final String SCHEMA_NAME = "MobileWikiAppPageScroll";
    private static final int REV_ID = 14591606;

    private final WikipediaApp app;
    private final int pageId;
    private int viewportHeight;
    private int pageHeight;
    private int scrollFluxDown;
    private int scrollFluxUp;
    private int maxScrollY;

    public PageScrollFunnel(WikipediaApp app, int pageId) {
        super(app, SCHEMA_NAME, REV_ID, app.isProdRelease() ? Funnel.SAMPLE_LOG_100 : Funnel.SAMPLE_LOG_ALL);
        this.app = app;
        this.pageId = pageId;
    }

    @Override protected void preprocessSessionToken(@NonNull JSONObject eventData) { }

    public void onPageScrolled(int oldScrollY, int scrollY, boolean isHumanScroll) {
        if (isHumanScroll) {
            if (scrollY > oldScrollY) {
                scrollFluxDown += (scrollY - oldScrollY);
            } else {
                scrollFluxUp += (oldScrollY - scrollY);
            }
        }
        maxScrollY = Math.max(maxScrollY, scrollY);
    }

    public void setPageHeight(int height) {
        this.pageHeight = (int) (height * app.getScreenDensity());
    }

    public void setViewportHeight(int height) {
        this.viewportHeight = height;
    }

    public void logDone() {
        log(
                "pageID", pageId,
                "pageHeight", (int) (pageHeight / app.getScreenDensity()),
                "scrollFluxDown", (int) (scrollFluxDown / app.getScreenDensity()),
                "scrollFluxUp", (int) (scrollFluxUp / app.getScreenDensity()),
                "maxPercentViewed", getMaxPercentViewed()
        );
    }

    private int getMaxPercentViewed() {
        final int maxPercent = 100;
        return Math.min(maxPercent, pageHeight == 0 ? 0 : ((maxScrollY + viewportHeight) * maxPercent / pageHeight));
    }
}
