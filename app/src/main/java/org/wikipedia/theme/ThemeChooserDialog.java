package org.wikipedia.theme;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import com.squareup.otto.Subscribe;

import org.wikipedia.R;
import org.wikipedia.WikipediaApp;
import org.wikipedia.analytics.AppearanceChangeFunnel;
import org.wikipedia.events.WebViewInvalidateEvent;
import org.wikipedia.page.BottomDialog;
import org.wikipedia.settings.Prefs;

public class ThemeChooserDialog extends BottomDialog {
    private WikipediaApp app;
    private Button buttonDefaultTextSize;
    private Button buttonDecreaseTextSize;
    private Button buttonIncreaseTextSize;
    private Button buttonThemeLight;
    private Button buttonThemeDark;
    private ProgressBar fontChangeProgressBar;
    private boolean updatingFont = false;
    private AppearanceChangeFunnel funnel;

    public ThemeChooserDialog(Context context) {
        super(context, R.layout.dialog_themechooser);
        app = WikipediaApp.getInstance();
        funnel = new AppearanceChangeFunnel(app, app.getPrimarySite());

        buttonDecreaseTextSize = (Button) getDialogLayout().findViewById(R.id.buttonDecreaseTextSize);
        buttonDecreaseTextSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatingFont = true;
                float currentSize = app.getFontSize(getWindow());
                app.setFontSizeMultiplier(Prefs.getTextSizeMultiplier() - 1);
                updateButtonState();
                funnel.logFontSizeChange(currentSize, app.getFontSize(getWindow()));
            }
        });

        buttonDefaultTextSize = (Button) getDialogLayout().findViewById(R.id.buttonDefaultTextSize);
        buttonDefaultTextSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatingFont = true;
                float currentSize = app.getFontSize(getWindow());
                app.setFontSizeMultiplier(0);
                updateButtonState();
                funnel.logFontSizeChange(currentSize, app.getFontSize(getWindow()));
            }
        });

        buttonIncreaseTextSize = (Button) getDialogLayout().findViewById(R.id.buttonIncreaseTextSize);
        buttonIncreaseTextSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatingFont = true;
                float currentSize = app.getFontSize(getWindow());
                app.setFontSizeMultiplier(Prefs.getTextSizeMultiplier() + 1);
                updateButtonState();
                funnel.logFontSizeChange(currentSize, app.getFontSize(getWindow()));
            }
        });

        ThemeOnClickListener themeOnClickListener = new ThemeOnClickListener();
        buttonThemeLight = (Button) getDialogLayout().findViewById(R.id.buttonColorsLight);
        buttonThemeLight.setOnClickListener(themeOnClickListener);

        buttonThemeDark = (Button) getDialogLayout().findViewById(R.id.buttonColorsDark);
        buttonThemeDark.setOnClickListener(themeOnClickListener);

        fontChangeProgressBar = (ProgressBar) getDialogLayout().findViewById(R.id.font_change_progress_bar);

        updateButtonState();
    }

    @Subscribe
    public void onWebViewInvalidated(WebViewInvalidateEvent event) {
        updatingFont = false;
        updateButtonState();
    }

    @Override
    public void show() {
        app.getBus().register(this);
        super.show();
    }

    @Override
    public void dismiss() {
        app.getBus().unregister(this);
        super.dismiss();
    }

    private void updateButtonState() {
        int mult = Prefs.getTextSizeMultiplier();
        if (updatingFont) {
            fontChangeProgressBar.setVisibility(View.VISIBLE);
            buttonDefaultTextSize.setEnabled(false);
            buttonDecreaseTextSize.setEnabled(false);
            buttonIncreaseTextSize.setEnabled(false);
        } else {
            fontChangeProgressBar.setVisibility(View.GONE);
            if (mult == 0) {
                buttonDefaultTextSize.setEnabled(false);
                buttonDecreaseTextSize.setEnabled(true);
                buttonIncreaseTextSize.setEnabled(true);
            } else {
                buttonDefaultTextSize.setEnabled(true);
                buttonDecreaseTextSize.setEnabled(mult > WikipediaApp.FONT_SIZE_MULTIPLIER_MIN);
                buttonIncreaseTextSize.setEnabled(mult < WikipediaApp.FONT_SIZE_MULTIPLIER_MAX);
            }
        }

        buttonThemeLight.setActivated(app.isCurrentThemeLight());
        buttonThemeDark.setActivated(app.isCurrentThemeDark());
    }

    private class ThemeOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Theme theme = getThemeChoiceForButton(view);
            if (app.getCurrentTheme() != theme) {
                funnel.logThemeChange(app.getCurrentTheme(), theme);
                app.setCurrentTheme(theme);
            }
        }

        private Theme getThemeChoiceForButton(View view) {
            return isButtonForLightTheme(view) ? Theme.LIGHT : Theme.DARK;
        }

        private boolean isButtonForLightTheme(View view) {
            return view == buttonThemeLight;
        }
    }
}