package org.wikipedia.page;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.wikipedia.WikipediaApp;
import org.wikipedia.server.PageLeadProperties;
import org.wikipedia.util.StringUtil;

import java.text.ParseException;
import java.util.Date;

/**
 * Immutable class that contains metadata associated with a PageTitle.
 */
public class PageProperties implements Parcelable {
    private static final String JSON_NAME_TITLE_PRONUNCIATION_URL = "titlePronunciationUrl";

    private final int pageId;
    private final long revisionId;
    private final Date lastModified;
    private final String displayTitleText;
    private final String editProtectionStatus;
    private final int languageCount;
    private final boolean isMainPage;
    private final boolean isDisambiguationPage;
    /** Nullable URL with no scheme. For example, foo.bar.com/ instead of http://foo.bar.com/. */
    @Nullable private final String leadImageUrl;
    private final String leadImageName;
    @Nullable private final String titlePronunciationUrl;

    /**
     * True if the user who first requested this page can edit this page
     * FIXME: This is not a true page property, since it depends on current user.
     */
    private final boolean canEdit;

    /**
     * Side note: Should later be moved out of this class but I like the similarities with
     * PageProperties(JSONObject).
     */
    public PageProperties(PageLeadProperties core) {
        pageId = core.getId();
        revisionId = core.getRevision();
        displayTitleText = StringUtil.emptyIfNull(core.getDisplayTitle());
        titlePronunciationUrl = core.getTitlePronunciationUrl();
        editProtectionStatus = core.getFirstAllowedEditorRole();
        languageCount = core.getLanguageCount();
        leadImageUrl = core.getLeadImageUrl();
        leadImageName = core.getLeadImageName();
        lastModified = new Date();
        String lastModifiedText = core.getLastModified();
        if (lastModifiedText != null) {
            try {
                lastModified.setTime(WikipediaApp.getInstance().getSimpleDateFormat()
                        .parse(lastModifiedText).getTime());
            } catch (ParseException e) {
                Log.d("PageProperties", "Failed to parse date: " + lastModifiedText);
            }
        }
        // assume formatversion=2 is used so we get real booleans from the API
        canEdit = core.isEditable();

        isMainPage = core.isMainPage();
        isDisambiguationPage = core.isDisambiguation();
    }

    /**
     * Create a new PageProperties object.
     * @param json JSON object from which this item will be built.
     */
    public PageProperties(JSONObject json) {
        pageId = json.optInt("id");
        revisionId = json.optLong("revision");
        displayTitleText = json.optString("displaytitle");
        titlePronunciationUrl = json.optString(JSON_NAME_TITLE_PRONUNCIATION_URL, null);
        // Mediawiki API is stupid!
        if (!(json.opt("protection") instanceof JSONArray)
            && json.optJSONObject("protection") != null
            && json.optJSONObject("protection").has("edit")
                ) {
            editProtectionStatus = json.optJSONObject("protection").optJSONArray("edit").optString(0);
        } else {
            editProtectionStatus = null;
        }
        languageCount = json.optInt("languagecount");
        JSONObject thumb = json.optJSONObject("thumb");
        leadImageUrl = thumb != null ? thumb.optString("url") : null;
        JSONObject image = json.optJSONObject("image");
        leadImageName = image != null ? image.optString("file") : null;
        lastModified = new Date();
        String lastModifiedText = json.optString("lastmodified");
        try {
            lastModified.setTime(WikipediaApp.getInstance().getSimpleDateFormat()
                    .parse(lastModifiedText).getTime());
        } catch (ParseException e) {
            Log.d("PageProperties", "Failed to parse date: " + lastModifiedText);
        }
        // There's something really screwy going on with the "editable" key in the API response.
        // It's not always returning a boolean, sadly.
        // If the key is the empty string, or true, then the page is editable.
        // If the key is not in the response, or is false, then the page is not editable.
        // This solution, while stupid, will work even if the API starts returning a boolean.
        canEdit = (json.has("editable") && json.optString("editable").equals(""))
                || json.optString("editable").equals("true");

        isMainPage = json.has("mainpage");
        isDisambiguationPage = json.has("disambiguation");
    }

    public int getPageId() {
        return pageId;
    }

    public long getRevisionId() {
        return revisionId;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public String getDisplayTitle() {
        return displayTitleText;
    }

    @Nullable
    public String getTitlePronunciationUrl() {
        return titlePronunciationUrl;
    }

    public String getEditProtectionStatus() {
        return editProtectionStatus;
    }

    public int getLanguageCount() {
        return languageCount;
    }

    public boolean canEdit() {
        return canEdit;
    }

    public boolean isMainPage() {
        return isMainPage;
    }

    public boolean isDisambiguationPage() {
        return isDisambiguationPage;
    }

    /**
     * @return Nullable URL with no scheme. For example, foo.bar.com/ instead of
     *         http://foo.bar.com/.
     */
    @Nullable
    public String getLeadImageUrl() {
        return leadImageUrl;
    }

    public String getLeadImageName() {
        return leadImageName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(pageId);
        parcel.writeLong(revisionId);
        parcel.writeLong(lastModified.getTime());
        parcel.writeString(displayTitleText);
        parcel.writeString(titlePronunciationUrl);
        parcel.writeString(editProtectionStatus);
        parcel.writeInt(languageCount);
        parcel.writeInt(canEdit ? 1 : 0);
        parcel.writeInt(isMainPage ? 1 : 0);
        parcel.writeInt(isDisambiguationPage ? 1 : 0);
        parcel.writeString(leadImageUrl);
        parcel.writeString(leadImageName);
    }

    private PageProperties(Parcel in) {
        pageId = in.readInt();
        revisionId = in.readLong();
        lastModified = new Date(in.readLong());
        displayTitleText = in.readString();
        titlePronunciationUrl = in.readString();
        editProtectionStatus = in.readString();
        languageCount = in.readInt();
        canEdit = in.readInt() == 1;
        isMainPage = in.readInt() == 1;
        isDisambiguationPage = in.readInt() == 1;
        leadImageUrl = in.readString();
        leadImageName = in.readString();
    }

    public static final Parcelable.Creator<PageProperties> CREATOR
            = new Parcelable.Creator<PageProperties>() {
        public PageProperties createFromParcel(Parcel in) {
            return new PageProperties(in);
        }

        public PageProperties[] newArray(int size) {
            return new PageProperties[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PageProperties that = (PageProperties) o;

        return pageId == that.pageId
                && revisionId == that.revisionId
                && lastModified.equals(that.lastModified)
                && displayTitleText.equals(that.displayTitleText)
                && TextUtils.equals(titlePronunciationUrl, that.titlePronunciationUrl)
                && languageCount == that.languageCount
                && canEdit == that.canEdit
                && isMainPage == that.isMainPage
                && isDisambiguationPage == that.isDisambiguationPage
                && TextUtils.equals(editProtectionStatus, that.editProtectionStatus)
                && TextUtils.equals(leadImageUrl, that.leadImageUrl)
                && TextUtils.equals(leadImageName, that.leadImageName);
    }

    @Override
    public int hashCode() {
        int result = lastModified.hashCode();
        result = 31 * result + displayTitleText.hashCode();
        result = 31 * result + (titlePronunciationUrl != null ? titlePronunciationUrl.hashCode() : 0);
        result = 31 * result + (editProtectionStatus != null ? editProtectionStatus.hashCode() : 0);
        result = 31 * result + languageCount;
        result = 31 * result + (isMainPage ? 1 : 0);
        result = 31 * result + (isDisambiguationPage ? 1 : 0);
        result = 31 * result + (leadImageUrl != null ? leadImageUrl.hashCode() : 0);
        result = 31 * result + (leadImageName != null ? leadImageName.hashCode() : 0);
        result = 31 * result + (canEdit ? 1 : 0);
        result = 31 * result + pageId;
        result = 31 * result + (int) revisionId;
        return result;
    }

    @Override
    public String toString() {
        return toJSON().toString();
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("id", pageId);
            json.put("revision", revisionId);
            json.put("lastmodified", WikipediaApp.getInstance().getSimpleDateFormat()
                    .format(getLastModified()));
            json.put("displaytitle", displayTitleText);
            json.put(JSON_NAME_TITLE_PRONUNCIATION_URL, titlePronunciationUrl);
            if (editProtectionStatus == null) {
                json.put("protection", new JSONArray());
            } else {
                JSONObject protectionStatusObject = new JSONObject();
                JSONArray editProtectionStatusArray = new JSONArray();
                editProtectionStatusArray.put(editProtectionStatus);
                protectionStatusObject.put("edit", editProtectionStatusArray);
                json.put("protection", protectionStatusObject);
            }
            json.put("languagecount", languageCount);
            json.put("editable", canEdit);
            if (isMainPage) {
                json.put("mainpage", "");
            }
            if (isDisambiguationPage) {
                json.put("disambiguation", "");
            }
            if (leadImageUrl != null) {
                JSONObject thumbObject = new JSONObject();
                thumbObject.put("url", leadImageUrl);
                json.put("thumb", thumbObject);
            }
            if (leadImageName != null) {
                JSONObject imageObject = new JSONObject();
                imageObject.put("file", leadImageName);
                json.put("image", imageObject);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return json;
    }
}
