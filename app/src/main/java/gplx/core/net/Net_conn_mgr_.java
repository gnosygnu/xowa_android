package gplx.core.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

public class Net_conn_mgr_ {
    public static boolean Active_type_is_wifi(Context context) {
        ConnectivityManager conn_mgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active = conn_mgr.getActiveNetworkInfo();
        return active != null && active.getType() == ConnectivityManager.TYPE_WIFI;
    }
}
