package org.wikipedia.settings;

import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import org.wikipedia.R;
import org.wikipedia.activity.ActivityUtil;
import org.wikipedia.activity.ThemedActionBarActivity;

import java.io.IOException;

import static org.wikipedia.util.FileUtil.readFile;

/**
 * Displays license text of the libraries we use.
 */
public class LicenseActivity extends ThemedActionBarActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);

        final String path = getIntent().getData().getPath();
        // Example string: "/android_asset/licenses/Otto"

        final int libraryNameStart = 24;
        setTitle(getString(R.string.license_title, path.substring(libraryNameStart)));

        try {
            TextView textView = (TextView) findViewById(R.id.license_text);
            final int assetPathStart = 15;
            final String text = readFile(getAssets().open(path.substring(assetPathStart)));
            textView.setText(Html.fromHtml(text.replace("\n\n", "<br/><br/>")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return ActivityUtil.defaultOnOptionsItemSelected(this, item)
                || super.onOptionsItemSelected(item);
    }

    @Override
    protected void setTheme() {
        setActionBarTheme();
    }
}
