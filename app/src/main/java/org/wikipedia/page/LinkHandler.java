package org.wikipedia.page;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import org.wikipedia.Site;
import org.wikipedia.WikipediaApp;
import org.wikipedia.bridge.CommunicationBridge;

import gplx.Bry_;
import gplx.Bry_find_;
import gplx.Byte_ascii;
import gplx.String_;
import gplx.xowa.Xow_wiki;
import gplx.xowa.drds.Xod_app;
import gplx.xowa.drds.Xod_app_mgr;

import static org.wikipedia.util.UriUtil.decodeURL;
import static org.wikipedia.util.UriUtil.handleExternalLink;

/**
 * Handles any html links coming from a {@link org.wikipedia.page.PageFragment}
 */
public abstract class LinkHandler implements CommunicationBridge.JSEventListener, LinkMovementMethodExt.UrlHandler {
    private final Context context;

    public LinkHandler(Context context, CommunicationBridge bridge) {
        this(context);

        bridge.addListener("linkClicked", this);
    }

    public LinkHandler(Context context) {
        this.context = context;
    }

    public abstract void onPageLinkClicked(String anchor);

    public abstract void onInternalLinkClicked(PageTitle title);

    // message from JS bridge:
    @Override
    public void onMessage(String messageType, JSONObject messagePayload) {
        try {
            String href = decodeURL(messagePayload.getString("href"));
            onUrlClick(href);
        } catch (IllegalArgumentException e) {
            // The URL is malformed and URL decoder can't understand it. Just do nothing.
            Log.d("Wikipedia", "A malformed URL was tapped.");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUrlClick(String href) {
        if (href.startsWith("//")) {
            // That's a protocol specific link! Make it https!
            href = "https:" + href;
        }
        Log.d("Wikipedia", "Link clicked was " + href);
        if (href.startsWith("/wiki/")) {
            PageTitle title = getSite().titleForInternalLink(href);
            onInternalLinkClicked(title);
        } else if (href.startsWith("/site/")) { // XOWA
            byte[] href_bry = Bry_.new_u8(href);
            int site_end_pos = Bry_find_.Find_fwd(href_bry, Byte_ascii.Slash, 6);   // 6 = len of "/site/" + 1
            Site site = new Site(String_.new_u8(href_bry, 6, site_end_pos));
            String page_name = String_.new_u8(href_bry, site_end_pos + 6, href_bry.length);
            if (String_.Len_eq_0(page_name)) {  // no page specified; use main; EX: "/site/en.wiktionary.org/"; DATE:2016-06-29
                Xow_wiki wiki = Xod_app_mgr.Instance.Get_by_domain(site.getDomain());
                if (wiki != null)               // make sure wiki is installed
                    page_name = String_.new_u8(wiki.Props().Main_page());
            }
            PageTitle title = new PageTitle(page_name, site);// 6 = len of "/wiki/" + 1
            onInternalLinkClicked(title);
        } else if (href.startsWith("#")) {
            onPageLinkClicked(href.substring(1));
        } else {
            Uri uri = Uri.parse(href);
            String authority = uri.getAuthority();
            // FIXME: Make this more complete, only to not handle URIs that contain unsupported actions
            if (authority != null && Site.isSupportedSite(authority) && uri.getPath().startsWith("/wiki/")) {
                Site site = new Site(authority);
                PageTitle title = site.titleForUri(uri);
                onInternalLinkClicked(title);
            } else {
                // if it's a /w/ URI, turn it into a full URI and go external
                if (href.startsWith("/w/")) {
                    href = String.format("%1$s://%2$s", WikipediaApp.getInstance().getNetworkProtocol(), getSite().getDomain()) + href;
                }
                handleExternalLink(context, Uri.parse(href));
            }
        }
    }

    public abstract Site getSite();
}
