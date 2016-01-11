package gplx.core.drds;
import android.app.Activity;
import gplx.*; import gplx.core.ios.IoEngine_system;

public class Drd_asset_mgr_ {
    public static byte[] Load_as_bry(Activity activity, String url_str) {
        java.io.InputStream stream = null;
        try {
            stream = activity.getAssets().open(url_str);
            return IoEngine_system.Load_from_stream_as_bry(stream, url_str);
        }
        catch (Exception e) {
            throw Err_.new_exc(e, "drd", "failed to load asset", "url", url_str);
        }
    }
}
