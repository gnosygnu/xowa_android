package gplx.xowa.drds;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import org.wikipedia.page.Section;
import org.wikipedia.server.PageLeadProperties;
import java.util.ArrayList;
import java.util.List;

import gplx.xowa.files.gui.Xog_js_wkr;

public class OfflinePageLeadProperties implements PageLeadProperties {
    public OfflinePageLeadProperties(int id, int revisionId
    , String displayTitle, String normalizedTitle, String redirected, String description, String lastModified
    , boolean editable, boolean mainPage, boolean disambiguation, int languageCount
    , String leadImageUrl, String leadImageName
    , String firstAllowedEditorRole
    , List<Section> sections
    ) {
        this.id = id; this.revisionId = revisionId;
        this.displayTitle = displayTitle; this.normalizedTitle = normalizedTitle; this.redirected = redirected; this.description = description; this.lastModified = lastModified;
        this.editable = editable; this.mainPage = mainPage; this.disambiguation = disambiguation; this.languageCount = languageCount;
        this.leadImageUrl= leadImageUrl; this.leadImageName= leadImageName; this.firstAllowedEditorRole = firstAllowedEditorRole;
        this.titlePronunciationUrl = null;
        this.sections = sections;
    }
    @Override public int getId() {return id;} private final int id;
    @Override public long getRevision() {return revisionId;} private final long revisionId;
    @Override @Nullable public String getDisplayTitle() {return displayTitle;} private final String displayTitle;
    @Override @Nullable public String getNormalizedTitle() {return normalizedTitle;} private final String normalizedTitle;
    @Override @Nullable public String getRedirected() {return redirected;} private final String redirected;
    @Override @Nullable public String getDescription() {return description;} private final String description;
    @Override @Nullable public String getLastModified() {return lastModified;} private final String lastModified;
    @Override public boolean isEditable() {return editable;} private final Boolean editable;
    @Override public boolean isMainPage() {return mainPage;} private final Boolean mainPage;
    @Override public boolean isDisambiguation() {return disambiguation;} private final Boolean disambiguation;
    @Override public int getLanguageCount() {return languageCount;} private final int languageCount;
    @Override @Nullable public String getLeadImageUrl() {return leadImageUrl;} private final String leadImageUrl;
    @Override @Nullable public String getLeadImageName() {return leadImageName;} private final String leadImageName;
    @Override @Nullable public String getFirstAllowedEditorRole() {return firstAllowedEditorRole;} private final String firstAllowedEditorRole;
    @Override @Nullable public List<Section> getSections() {return sections;} private final List<Section> sections;
    @Override @Nullable public String getTitlePronunciationUrl() {
        return titlePronunciationUrl;
    } private final String titlePronunciationUrl;
}
