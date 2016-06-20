package org.wikipedia.settings;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.Preference;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.wikipedia.R;
import org.wikipedia.Site;
import org.wikipedia.WikipediaApp;
import org.wikipedia.history.HistoryEntry;
import org.wikipedia.page.PageActivity;
import org.wikipedia.page.PageTitle;

import static org.wikipedia.util.DeviceUtil.hideSoftKeyboard;

public class PreferenceMultiLine extends Preference {

    public PreferenceMultiLine(Context ctx, AttributeSet attrs, int defStyle) {
        super(ctx, attrs, defStyle);
    }

    public PreferenceMultiLine(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
    }

    public PreferenceMultiLine(Context ctx) {
        super(ctx);
    }

    @Override
    protected void onBindView(@NonNull View view) {
        super.onBindView(view);
        TextView textView = (TextView) view.findViewById(android.R.id.title);
        if (textView != null) {
            textView.setSingleLine(false);
        }
        // Intercept the click listener for this preference, and if the preference has an intent,
        // launch the intent ourselves, so that we can catch the exception if the intent fails.
        // (but only do this if the preference doesn't already have a click listener)
        if (this.getOnPreferenceClickListener() == null) {
            this.setOnPreferenceClickListener(new OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {

                    if (preference.getIntent() != null) {
                        try {
                            // getContext().startActivity(preference.getIntent());

                            // HACK: change to launch Special page
                            Intent original_intent = preference.getIntent();
                            WikipediaApp app = WikipediaApp.getInstance();
                            PageTitle title = new PageTitle(original_intent.getDataString(), new Site("home"));
                            Intent intent = new Intent(getContext(), PageActivity.class);
                            intent.setAction(PageActivity.ACTION_PAGE_FOR_TITLE);
                            intent.putExtra(PageActivity.EXTRA_PAGETITLE, title);
                            intent.putExtra(PageActivity.EXTRA_HISTORYENTRY, new HistoryEntry(title, HistoryEntry.SOURCE_INTERNAL_LINK));
                            getContext().startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(getContext(), getContext().getString(R.string.error_browser_not_found), Toast.LENGTH_LONG).show();
                        }
                        return true;
                    }
                    return false;
                }
            });
        }
    }
}
