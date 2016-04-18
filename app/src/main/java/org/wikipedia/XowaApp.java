package org.wikipedia;

import android.content.Context;
import android.support.multidex.MultiDex;

public class XowaApp extends WikipediaApp {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
