package gplx.xowa.drds;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;

import gplx.xowa.drds.ios.assets.Xod_asset_mgr;
import gplx.xowa.drds.ios.media_scanners.Xod_media_scanner__base;

class Xod_media_scanner__drd extends Xod_media_scanner__base implements MediaScannerConnection.MediaScannerConnectionClient {
    private final Context context;
    public Xod_media_scanner__drd(Context context) {
        super();
        this.context = context;
    }

    @Override protected void Scan__hook(String[] urls) {
        MediaScannerConnection.scanFile(context, urls, null, this);
    }

    @Override public void onMediaScannerConnected() {}
    @Override public void onScanCompleted(String path, Uri uri) {}
}
