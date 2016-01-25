package org.wikipedia.crash;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.wikipedia.R;
import org.wikipedia.WikipediaApp;
import org.wikipedia.activity.CallbackFragment;
import org.wikipedia.drawable.DrawableUtil;
import gplx.xowa.drds.OfflineCrashLog;

public class CrashReportFragment extends CallbackFragment<CrashReportFragmentCallback> {
    public static CrashReportFragment newInstance() {
        return new CrashReportFragment();
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater,
                                                 ViewGroup container,
                                                 Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_crash_report, container, false);

        TextView textView = (TextView)view.findViewById(R.id.crash_report_log);
        textView.setText(CrashReportActivity.Last_error);
        setOnClickListener(view, R.id.crash_report_send_email, new SendEmailOnClickListener(this)); // XOWA
        setOnClickListener(view, R.id.crash_report_start_over, new StartOverOnClickListener());
        setOnClickListener(view, R.id.crash_report_quit, new QuitOnClickListener());

        setIconColor(view.findViewById(R.id.crash_report_icon).getBackground().mutate(),
        getContrastingThemeColor());

        return view;
    }

    private void setIconColor(@NonNull Drawable icon, @ColorInt int color) {
        DrawableUtil.setTint(icon, color);
    }

    @ColorInt
    private int getContrastingThemeColor() {
        return WikipediaApp.getInstance().getContrastingThemeColor();
    }

    private void setOnClickListener(View view, @IdRes int id, View.OnClickListener listener) {
        view.findViewById(id).setOnClickListener(listener);
    }

    private class StartOverOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //noinspection ConstantConditions
            getCallback().onStartOver();
        }
    }

    private class QuitOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //noinspection ConstantConditions
            getCallback().onQuit();
        }
    }
}
class SendEmailOnClickListener implements View.OnClickListener {
    private CrashReportFragment fragment;
    public SendEmailOnClickListener(CrashReportFragment fragment) {this.fragment = fragment;}
    @Override
    public void onClick(View v) {
        Intent i = new Intent(Intent.ACTION_SEND);
        //i.setType("text/plain"); //use this line for testing in the emulator
        i.setType("message/rfc822") ; // use from live device
        i.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");//sending email via gmail
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"gnosygnu+xowa_error_android@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "XOWA Android crash");
        i.putExtra(Intent.EXTRA_TEXT, CrashReportActivity.Last_error);
        fragment.startActivity(i);
    }
}
