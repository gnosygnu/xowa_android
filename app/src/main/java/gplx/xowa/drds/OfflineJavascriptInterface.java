package gplx.xowa.drds;

import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import gplx.core.gfobjs.Gfobj_nde;
import gplx.xowa.guis.cbks.Xog_cbk_trg;
import gplx.xowa.guis.cbks.Xog_cbk_wkr;
import gplx.xowa.guis.cbks.swts.Gfobj_wtr__json__browser;

public class OfflineJavascriptInterface implements Xog_cbk_wkr, Runnable, ValueCallback<String> {
    public static final OfflineJavascriptInterface Instance = new OfflineJavascriptInterface();
    private final Gfobj_wtr__json__browser json_wtr = new Gfobj_wtr__json__browser();
    private boolean init = true;
    private WebView webview;
    private String send_json_script;
    public OfflineJavascriptInterface Set(WebView webview) {
        if (init) {
            init = false;
            Xod_app_mgr.Instance.xo_app.Gui__cbk_mgr().Reg(this);
        }
        this.webview = webview;
        return this;
    }
    @JavascriptInterface public String exec_json(String data) {return Xod_app_mgr.Instance.Html__bridge_mgr__exec_json(data);}
    @Override public Object Send_json(Xog_cbk_trg trg, String func, Gfobj_nde data) {
        this.send_json_script = json_wtr.Write_as_func__drd(func, data);
        webview.post(this);
        return "";
    }
    @Override public void run() {
        webview.evaluateJavascript(send_json_script, this);
    }
    @Override public void onReceiveValue(String value) {
    }
}
