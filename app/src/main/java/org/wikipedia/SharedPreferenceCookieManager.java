package org.wikipedia;

import org.wikipedia.settings.Prefs;
import org.wikipedia.util.StringUtil;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class SharedPreferenceCookieManager extends CookieManager {
    private static final String DELIMITER = ";";

    private final HashMap<String, HashMap<String, String>> cookieJar = new HashMap<>();

    public SharedPreferenceCookieManager() {
        List<String> domains = makeList(Prefs.getCookieDomains());
        for (String domain: domains) {
            String cookies = Prefs.getCookiesForDomain(domain);
            cookieJar.put(domain, makeCookieMap(makeList(cookies)));
        }
    }

    @Override
    public Map<String, List<String>> get(URI uri, Map<String, List<String>> requestHeaders) throws IOException {
        if (uri == null || requestHeaders == null) {
            throw new IllegalArgumentException("Argument is null");
        }

        Map<String, List<String>> cookieMap = new HashMap<>();
        ArrayList<String> cookiesList = new ArrayList<>();

        String domain = uri.getAuthority();

        for (String domainSpec: cookieJar.keySet()) {
            // Very weak domain matching.
            // Primarily to make sure that cookies set for .wikipedia.org are sent for en.wikipedia.org
            // FIXME: Whitelist the domains we accept cookies from/send cookies to. SECURITY!!!1
            if (domain.endsWith(domainSpec)) {
                cookiesList.addAll(makeCookieList(cookieJar.get(domainSpec)));
            }
        }

        cookieMap.put("Cookie", cookiesList);

        return Collections.unmodifiableMap(cookieMap);
    }

    @Override
    public void put(URI uri, Map<String, List<String>> responseHeaders) throws IOException {
        // pre-condition check
        if (uri == null || responseHeaders == null) {
            throw new IllegalArgumentException("Argument is null");
        }

        HashSet<String> domainsModified = new HashSet<>();

        for (String headerKey : responseHeaders.keySet()) {
            if (headerKey == null || !headerKey.equalsIgnoreCase("Set-Cookie")) {
                continue;
            }

            for (String headerValue : responseHeaders.get(headerKey)) {
                try {
                    List<HttpCookie> cookies = HttpCookie.parse(headerValue);
                    for (HttpCookie cookie : cookies) {
                        // Default to the URI's domain if domain is not explicitly set
                        String domainSpec = cookie.getDomain() == null ? uri.getAuthority() : cookie.getDomain();
                        if (!cookieJar.containsKey(domainSpec)) {
                            cookieJar.put(domainSpec, new HashMap<String, String>());
                        }
                        cookieJar.get(domainSpec).put(cookie.getName(), cookie.getValue());
                        domainsModified.add(domainSpec);
                    }
                } catch (IllegalArgumentException e) {
                    // invalid set-cookie header string
                    // no-op
                }
            }
        }

        Prefs.setCookieDomains(makeString(cookieJar.keySet()));

        for (String domain : domainsModified) {
            Prefs.setCookiesForDomain(domain, makeString(makeCookieList(cookieJar.get(domain))));
        }
    }

    @Override
    public CookieStore getCookieStore() {
        // We don't actually have one. hehe
        throw new UnsupportedOperationException("We poor. We no have CookieStore");
    }

    public void clearCookiesForDomain(String domain) {
        Prefs.removeCookiesForDomain(domain);
    }

    public void clearAllCookies() {
        for (String domain: cookieJar.keySet()) {
            Prefs.removeCookiesForDomain(domain);
        }
        Prefs.setCookieDomains(null);
        cookieJar.clear();
    }

    private HashMap<String, String> makeCookieMap(List<String> cookies) {
        HashMap<String, String> cookiesMap = new HashMap<>();
        for (String cookie : cookies) {
            if (!cookie.contains("=")) {
                throw new RuntimeException("Cookie " + cookie + " is invalid!");
            }
            String[] parts = cookie.split("=");
            cookiesMap.put(parts[0], parts[1]);
        }
        return cookiesMap;
    }

    private List<String> makeCookieList(Map<String, String> cookies) {
        ArrayList<String> cookiesList = new ArrayList<>();
        for (Map.Entry<String, String> entry: cookies.entrySet()) {
            cookiesList.add(entry.getKey() + "=" + entry.getValue());
        }
        return cookiesList;
    }

    private String makeString(Iterable<String> list) {
        return StringUtil.listToDelimitedString(list, DELIMITER);
    }

    private List<String> makeList(String str) {
        return StringUtil.delimiterStringToList(str, DELIMITER);
    }
}
