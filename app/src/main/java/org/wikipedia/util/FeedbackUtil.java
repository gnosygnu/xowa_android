package org.wikipedia.util;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.content.Context;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.wikipedia.R;
import org.wikipedia.WikipediaApp;
import org.wikipedia.page.PageActivity;

import java.util.concurrent.TimeUnit;

import static org.wikipedia.util.UriUtil.visitInExternalBrowser;

public final class FeedbackUtil {
    public static final int LENGTH_DEFAULT = (int) TimeUnit.SECONDS.toMillis(5);
    private static final int SNACKBAR_MAX_LINES = 5;

    public static Snackbar makeSnackbar(View view, CharSequence text, int duration) {
        Snackbar snackbar = Snackbar.make(view, text, duration);
        TextView textView = (TextView) snackbar.getView().findViewById(R.id.snackbar_text);
        textView.setMaxLines(SNACKBAR_MAX_LINES);
        return snackbar;
    }

    public static void showError(View containerView, Throwable e) {
        ThrowableUtil.AppError error = ThrowableUtil.getAppError(containerView.getContext(), e);
        makeSnackbar(containerView, error.getError(), LENGTH_DEFAULT).show();
    }

    public static void showMessageAsPlainText(Activity activity, CharSequence possibleHtml) {
        CharSequence richText = Html.fromHtml(possibleHtml.toString());
        showMessage(activity, richText.toString());
    }

    private static void showMessage(View containerView, CharSequence text, int duration) {
        makeSnackbar(containerView, text, duration).show();
    }

    public static void showMessage(Activity activity, @StringRes int resId) {
        showMessage(activity, activity.getString(resId), Snackbar.LENGTH_LONG);
    }

    public static void showMessage(Activity activity, CharSequence text) {
        showMessage(findBestView(activity), text, Snackbar.LENGTH_LONG);
    }

    public static void showMessage(Activity activity, @StringRes int resId, int duration) {
        showMessage(activity, activity.getString(resId), duration);
    }

    public static void showMessage(Activity activity, CharSequence text, int duration) {
        showMessage(findBestView(activity), text, duration);
    }

    public static void showError(Activity activity, Throwable e) {
        showError(findBestView(activity), e);
    }

    public static void showPrivacyPolicy(Context context) {
        visitInExternalBrowser(context, Uri.parse(context.getString(R.string.privacy_policy_url)));
    }

    /**
     * Ask user to try connecting again upon (hopefully) recoverable network failure.
     */
    public static void toastNetworkFail() {
        Toast.makeText(WikipediaApp.getInstance(), R.string.error_network_error_try_again, Toast.LENGTH_LONG).show();
    }

    /**
     * Set message for error popup on a TextView.
     * @param textView the TextView or EditText to pop the error message from
     * @param error the error message. Use null message to clear.
     * @see TextView#setError
     * @see <a href='http://stackoverflow.com/questions/14413575/how-to-write-style-to-error-text-of-edittext-in-android'>StackOverflow: How to write style to error text of EditText in Android?</a>
     */
    public static void setErrorPopup(TextView textView, String error) {
        textView.setError(error);
    }

    private static View findBestView(Activity activity) {
        if (activity instanceof PageActivity
                && ((PageActivity) activity).getCurPageFragment() != null) {
            return activity.findViewById(R.id.page_contents_container);
        } else {
            return activity.findViewById(android.R.id.content);
        }
    }

    private FeedbackUtil() {
    }
}