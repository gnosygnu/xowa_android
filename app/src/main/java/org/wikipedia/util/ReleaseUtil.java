package org.wikipedia.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import org.wikipedia.BuildConfig;
import org.wikipedia.settings.Prefs;

public final class ReleaseUtil {
    private static final int RELEASE_PROD = 0;
    private static final int RELEASE_BETA = 1;
    private static final int RELEASE_ALPHA = 2;
    private static final int RELEASE_DEV = 3;

    public static boolean isProdRelease() {
        return calculateReleaseType() == RELEASE_PROD;
    }

    public static boolean isPreProdRelease() {
        return calculateReleaseType() != RELEASE_PROD;
    }

    public static boolean isAlphaRelease() {
        return calculateReleaseType() == RELEASE_ALPHA;
    }

    public static boolean isPreBetaRelease() {
        switch (calculateReleaseType()) {
            case RELEASE_PROD:
            case RELEASE_BETA:
                return false;
            default:
                return true;
        }
    }

    public static boolean isDevRelease() {
        return calculateReleaseType() == RELEASE_DEV;
    }

    /**
     * Gets the distribution channel for the app from SharedPreferences
     */
    public static String getChannel(Context ctx) {
        String channel = Prefs.getAppChannel();
        if (channel == null) {
            channel = getChannelDescriptor(ctx);
            setChannel(channel);
        }
        return channel;
    }

    public static int calculateReleaseType() {
        if (BuildConfig.APPLICATION_ID.contains("beta")) {
            return RELEASE_BETA;
        }
        if (BuildConfig.APPLICATION_ID.contains("alpha")) {
            return RELEASE_ALPHA;
        }
        if (BuildConfig.APPLICATION_ID.contains("dev")) {
            return RELEASE_DEV;
        }
        return RELEASE_PROD;
    }

    /**
     * Returns the distribution channel for the app from AndroidManifest.xml
     * @return The channel (the empty string if not defined)
     */
    private static String getChannelDescriptor(Context ctx) {
        try {
            ApplicationInfo info = ctx.getPackageManager()
                    .getApplicationInfo(BuildConfig.APPLICATION_ID, PackageManager.GET_META_DATA);
            String channel = info.metaData.getString(Prefs.getAppChannelKey());
            return channel != null ? channel : "";
        } catch (Throwable t) {
            return "";
        }
    }

    /**
     * Sets the distribution channel for the app into SharedPreferences
     */
    private static void setChannel(String channel) {
        Prefs.setAppChannel(channel);
    }

    private ReleaseUtil() {

    }
}
