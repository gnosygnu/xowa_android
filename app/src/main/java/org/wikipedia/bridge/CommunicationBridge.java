package org.wikipedia.bridge;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gplx.xowa.drds.OfflineJavascriptInterface;

import static org.wikipedia.util.UriUtil.decodeURL;

/**
 * Two way communications bridge between JS in a WebView and Java.
 */
public class CommunicationBridge {
    private final WebView webView;

    private final Map<String, ArrayList<JSEventListener>> eventListeners;

    private final BridgeMarshaller marshaller;

    private boolean isDOMReady = false;
    private final ArrayList<String> pendingJSMessages = new ArrayList<>();
    private final String baseURL;

    public interface JSEventListener {
        void onMessage(String messageType, JSONObject messagePayload);
    }
    public CommunicationBridge CreateNew(CommunicationBridge bridge) {
        return new CommunicationBridge(bridge.webView, bridge.baseURL);
    }

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    public CommunicationBridge(final WebView webView, final String baseURL) {
        this.webView = webView;
        this.baseURL = baseURL;
        this.marshaller = new BridgeMarshaller();

        webView.getSettings().setSupportZoom(true); // XOWA
        webView.getSettings().setBuiltInZoomControls(true); // XOWA
        webView.getSettings().setDisplayZoomControls(false); // XOWA
        webView.getSettings().setLoadWithOverviewMode(true); // XOWA
        // webView.getSettings().setUseWideViewPort(true); // XOWA: trying to do zoom-out
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new CommunicatingChrome());
        webView.addJavascriptInterface(marshaller, "marshaller");
        webView.addJavascriptInterface(OfflineJavascriptInterface.Instance.Set(webView), "xowa_exec");  // XOWA
        /*
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.out.println(url);
                return true;
            }
        });
        */
        eventListeners = new HashMap<>();
        webView.loadUrl(baseURL); // TODO: remove once we finish the page load experiment
//        this.Reload();
        this.addListener("DOMLoaded", new JSEventListener() {
            @Override
            public void onMessage(String messageType, JSONObject messagePayload) {
                isDOMReady = true;
                for (String jsString : pendingJSMessages) {
                    CommunicationBridge.this.webView.loadUrl(jsString);
                }
            }
        });
    }
//    public void Reload() {
//        webView.loadUrl(baseURL); // TODO: remove once we finish the page load experiment
//    }

    public void cleanup() {
        eventListeners.clear();
        if (incomingMessageHandler != null) {
            incomingMessageHandler.removeCallbacksAndMessages(null);
            incomingMessageHandler = null;
        }
    }

    public void addListener(String type, JSEventListener listener) {
        if (eventListeners.containsKey(type)) {
            eventListeners.get(type).add(listener);
        } else {
            ArrayList<JSEventListener> listeners = new ArrayList<>();
            listeners.add(listener);
            eventListeners.put(type, listeners);
        }
    }

    /**
     * Inject the styles specified by the bundle into this webview.
     *
     * @param styleBundle The bundle representing the styles to load.
     */
    public void injectStyleBundle(StyleBundle styleBundle) {
        sendMessage("injectStyles", styleBundle.toJSON());
    }

    public void sendMessage(String messageName, JSONObject messageData) {
        String messagePointer =  marshaller.putPayload(messageData.toString());

        String jsString = "javascript:handleMessage( \"" + messageName + "\", \"" + messagePointer + "\" );";
        if (!isDOMReady) {
            pendingJSMessages.add(jsString);
        } else {
            webView.loadUrl(jsString);
        }
    }

    private static final int MESSAGE_HANDLE_MESSAGE_FROM_JS = 1;
    private Handler incomingMessageHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            JSONObject messagePack = (JSONObject) msg.obj;
            String type = messagePack.optString("type");
            if (!eventListeners.containsKey(type)) {
                throw new RuntimeException("No such message type registered: " + type);
            }
            ArrayList<JSEventListener> listeners = eventListeners.get(type);
            for (JSEventListener listener : listeners) {
                listener.onMessage(type, messagePack.optJSONObject("payload"));
            }
            return false;
        }
    });

    private class CommunicatingChrome extends WebChromeClient {
        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            try {
                // If incomingMessageHandler is null, it means that we've been cleaned up, but we're
                // still receiving some final messages from the WebView, so we'll just ignore them.
                // But we should still return true and "confirm" the JsPromptResult down below.
                if (incomingMessageHandler != null) {
                    JSONObject messagePack = new JSONObject(decodeURL(message));
                    Message msg = Message.obtain(incomingMessageHandler, MESSAGE_HANDLE_MESSAGE_FROM_JS, messagePack);
                    incomingMessageHandler.sendMessage(msg);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            result.confirm();
            return true;
        }

        @Override
        public boolean onConsoleMessage(@NonNull ConsoleMessage consoleMessage) {
            Log.d("WikipediaWeb", consoleMessage.sourceId() + ":" + consoleMessage.lineNumber() + " - " + consoleMessage.message());
            return true;
        }
    }

    private static class BridgeMarshaller {
        private Map<String, String> queueItems = new HashMap<>();
        private int counter = 0;

        /**
         * Called from the JS via the JSBridge to get actual payload from a messagePointer.
         *
         * Warning: This is going to be called on an indeterminable background thread, not main thread.
         *
         * @param pointer Key returned from #putPayload
         */
        @JavascriptInterface
        public String getPayload(String pointer) {
            synchronized (this) {
                return queueItems.remove(pointer);
            }
        }

        public String putPayload(String payload) {
            String key = "pointerKey_" + counter;
            counter++;
            synchronized (this) {
                queueItems.put(key, payload);
            }
            return key;
        }
    }
}
