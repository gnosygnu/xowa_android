package org.wikipedia.server;

import org.wikipedia.page.Section;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import java.util.List;

/**
 * The main properties of a page
 */
public interface PageLeadProperties {

    int getId();

    long getRevision();

    @Nullable
    String getLastModified();

    int getLanguageCount();

    @Nullable
    String getDisplayTitle();

    @Nullable
    String getTitlePronunciationUrl();

    @Nullable
    String getRedirected();

    @Nullable
    String getNormalizedTitle();

    @Nullable
    String getDescription();

    /**
     * @return Nullable URL with no scheme. For example, foo.bar.com/ instead of
     *         http://foo.bar.com/.
     */
    @Nullable
    String getLeadImageUrl();

    @Nullable
    String getLeadImageName();

    @Nullable
    String getFirstAllowedEditorRole();

    boolean isEditable();

    boolean isMainPage();

    boolean isDisambiguation();

    @VisibleForTesting
    @Nullable
    List<Section> getSections();
}
