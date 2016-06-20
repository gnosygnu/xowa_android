package gplx.xowa.drds;

import android.app.Activity;

import gplx.Err_;
import gplx.Io_url;
import gplx.String_;
import gplx.core.ios.IoEngine_system;
import gplx.xowa.drds.ios.assets.Xod_asset_mgr;

class Xod_asset_mgr__drd implements Xod_asset_mgr {
    private final Activity activity;
    private static final int Android_assets_len = 15; // "/android_asset/".length
    public Xod_asset_mgr__drd(Activity activity) {this.activity = activity;}
    @Override public byte[] Load_fil_as_bry(Io_url fil) {
        String fil_str = fil.Xto_api(); if (!fil_str.startsWith("/android_asset/")) return null;
        fil_str = String_.Mid(fil_str, Android_assets_len);
        java.io.InputStream stream = null;
        try {
            stream = activity.getAssets().open(fil_str);
            return IoEngine_system.Load_from_stream_as_bry(stream, fil_str);
        }
        catch (Exception e) {
            throw Err_.new_exc(e, "drd", "failed to load asset", "url", fil_str);
        }
    }
    @Override public String[] List_as_str_ary(Io_url dir) {
        String dir_str = String_.Mid(dir.Xto_api(), Android_assets_len);
        try {
            return activity.getAssets().list(dir_str);
        }
        catch (Exception e) {
            throw Err_.new_exc(e, "drd", "failed to list files", "dir", dir_str);
        }
    }
}