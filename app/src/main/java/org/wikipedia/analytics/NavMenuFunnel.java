package org.wikipedia.analytics;

import org.wikipedia.WikipediaApp;

public class NavMenuFunnel extends Funnel {
    private static final String SCHEMA_NAME = "MobileWikiAppNavMenu";
    private static final int REV_ID = 12732211;

    private static final String NAV_MENU_TODAY = "Today";
    private static final String NAV_MENU_HISTORY = "Recent";
    private static final String NAV_MENU_SAVED_PAGES = "SavedPages";
    private static final String NAV_MENU_NEARBY = "Nearby";
    private static final String NAV_MENU_MORE = "More";
    private static final String NAV_MENU_LOGIN = "Login";
    private static final String NAV_MENU_RANDOM = "Random";

    public NavMenuFunnel() {
        super(WikipediaApp.getInstance(), SCHEMA_NAME, REV_ID, Funnel.SAMPLE_LOG_100);
    }

    public void logToday() {
        logSelect(NAV_MENU_TODAY);
    }

    public void logHistory() {
        logSelect(NAV_MENU_HISTORY);
    }

    public void logSavedPages() {
        logSelect(NAV_MENU_SAVED_PAGES);
    }

    public void logNearby() {
        logSelect(NAV_MENU_NEARBY);
    }

    public void logMore() {
        logSelect(NAV_MENU_MORE);
    }

    public void logLogin() {
        logSelect(NAV_MENU_LOGIN);
    }

    public void logRandom() {
        logSelect(NAV_MENU_RANDOM);
    }

    public void logOpen() {
        log(
                "action", "open"
        );
    }

    public void logCancel() {
        log(
                "action", "cancel"
        );
    }

    private void logSelect(String menuItem) {
        log(
                "action", "select",
                "menuItem", menuItem
        );
    }
}
