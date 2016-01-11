package org.wikipedia.server.restbase;

import org.wikipedia.server.PageSummary;
import org.wikipedia.util.log.L;

import com.google.gson.annotations.Expose;

import android.support.annotation.Nullable;

/**
 * Useful for link previews coming from RESTBase.
 */
public class RbPageSummary implements PageSummary {
    @Expose private RbServiceError error;

    @Override
    public boolean hasError() {
        // if there is no title or no extract set something went terribly wrong
        return error != null || title == null || extract == null;
    }

    @Nullable
    public RbServiceError getError() {
        return error;
    }

    public void logError(String message) {
        if (error != null) {
            message += ": " + error.toString();
        }
        L.e(message);
    }

    @Expose @Nullable private String title;

    @Expose @Nullable private String extract;

    @Expose @Nullable private Thumb thumbnail;

    @Override @Nullable
    public String getTitle() {
        return title;
    }

    @Override @Nullable
    public String getExtract() {
        return extract;
    }

    @Override @Nullable
    public String getThumbnailUrl() {
        return thumbnail == null ? null : thumbnail.getUrl();
    }

    /**
     * For the thumbnail URL of the page
     */
    public static class Thumb {
        @Expose private String source;

        public String getUrl() {
            return source;
        }
    }
}
