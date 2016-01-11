package org.wikipedia.analytics;

import android.support.annotation.NonNull;

import org.json.JSONObject;
import org.wikipedia.WikipediaApp;

public class ConnectionIssueFunnel extends Funnel {
    private static final String SCHEMA_NAME = "MobileWikiAppStuffHappens";
    private static final int REVISION = 8955468;

    public ConnectionIssueFunnel(WikipediaApp app) {
        super(app, SCHEMA_NAME, REVISION);
    }

    public void logConnectionIssue(String failedEndpoint, String applicationContext) {
        log(
                "failedEndpoint", failedEndpoint,
                "applicationContext", applicationContext
        );
    }

    @Override protected void preprocessAppInstallID(@NonNull JSONObject eventData) { }
    @Override protected void preprocessSessionToken(@NonNull JSONObject eventData) { }
}
