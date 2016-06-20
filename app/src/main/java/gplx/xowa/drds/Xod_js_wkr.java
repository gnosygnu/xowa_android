package gplx.xowa.drds;

import android.app.Activity;

import org.json.JSONObject;
import org.wikipedia.bridge.CommunicationBridge;

import java.util.concurrent.locks.Lock;

import gplx.xowa.files.gui.Xog_js_wkr;

public class Xod_js_wkr implements Xog_js_wkr {
    private Activity activity; private CommunicationBridge bridge;
    public Xod_js_wkr Init(Activity activity, CommunicationBridge bridge) {
        this.activity = activity;
        this.bridge = bridge;
        return this;
    }
    public void Html_img_update		(String id, String src, int w, int h) {
        JSONObject message = new JSONObject();
        try {
            message.put("img_id", id);
            message.put("img_src", src);
            message.put("img_w", w);
            message.put("img_h", h);
        } catch (Exception e) {}
        activity.runOnUiThread(new Runnable__bridge_cmd(bridge, message, "xowa__img__update"));
    }
    public void Html_redlink			(String html_uid) {
        JSONObject message = new JSONObject();
        try {
            message.put("anch_id", html_uid);
        } catch (Exception e) {}
        activity.runOnUiThread(new Runnable__bridge_cmd(bridge, message, "xowa__anch_redlink"));
    }

    public void Html_atr_set			(String uid, String key, String val) {}
    public void Html_elem_replace_html	(String uid, String html) {}
    public void Html_elem_append_above	(String uid, String html) {}

    @Override public void Html_elem_delete(String elem_id) {}
    @Override public void Html_gallery_packed_exec() {}
    @Override public void Html_popups_bind_hover_to_doc() {}
}
class Runnable__bridge_cmd implements Runnable {
    private final CommunicationBridge bridge;
    private final JSONObject message;
    private final String cmd_name;
    public Runnable__bridge_cmd(CommunicationBridge bridge, JSONObject message, String cmd_name) {
        this.bridge = bridge; this.message = message; this.cmd_name = cmd_name;
    }
    public void run() {bridge.sendMessage(cmd_name, message);}
}
