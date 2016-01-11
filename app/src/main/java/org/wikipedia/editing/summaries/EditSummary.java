package org.wikipedia.editing.summaries;

import java.util.Date;

public class EditSummary {
    public static final EditSummaryPersistenceHelper PERSISTENCE_HELPER = new EditSummaryPersistenceHelper();
    private final String summary;
    private final Date lastUsed;

    public EditSummary(String summary, Date lastUsed) {
        this.summary = summary;
        this.lastUsed = lastUsed;
    }

    public String getSummary() {
        return summary;
    }

    public Date getLastUsed() {
        return lastUsed;
    }
}
