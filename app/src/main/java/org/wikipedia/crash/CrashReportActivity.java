package org.wikipedia.crash;

import android.content.Intent;
import android.os.Bundle;

import org.wikipedia.WikipediaApp;
import org.wikipedia.activity.ActivityUtil;
import org.wikipedia.activity.CompatSingleFragmentActivity;

public class CrashReportActivity extends CompatSingleFragmentActivity<CrashReportFragment>
        implements CrashReportFragmentCallback {
    public static String Last_error = "";
    @Override
    protected CrashReportFragment createFragment() {
        return CrashReportFragment.newInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WikipediaApp.getInstance().checkCrashes(this);
        Intent intent = getIntent();
        if (intent.hasExtra("Last_error")) {
            Last_error = intent.getStringExtra("Last_error");
        }
    }

    @Override
    public void onStartOver() {
        int flags = Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK;
        Intent intent = ActivityUtil.getLaunchIntent(this).addFlags(flags);
        startActivity(intent);
        finish();
    }

    @Override
    public void onQuit() {
        finish();
    }
}
