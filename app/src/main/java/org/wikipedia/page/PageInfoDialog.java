package org.wikipedia.page;

import org.wikipedia.R;
import org.wikipedia.history.HistoryEntry;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ViewFlipper;

import static org.wikipedia.util.L10nUtil.getStringForArticleLanguage;

/**
 * A dialog to host page issues and disambig information.
 */
/*package*/ class PageInfoDialog extends BottomDialog {
    private final ViewFlipper flipper;
    private final Button disambigHeading;
    private final Button issuesHeading;
    private final ListView disambigList;

    /*package*/ PageInfoDialog(final PageActivity activity, PageInfo pageInfo, int height) {
        super(activity, R.layout.dialog_page_info);

        View parentView = getDialogLayout();
        flipper = (ViewFlipper) parentView.findViewById(R.id.page_info_flipper);
        disambigList = (ListView) parentView.findViewById(R.id.disambig_list);
        ListView issuesList = (ListView) parentView.findViewById(R.id.page_issues_list);
        disambigHeading = (Button) parentView.findViewById(R.id.page_info_similar_titles_heading);
        issuesHeading = (Button) parentView.findViewById(R.id.page_info_page_issues_heading);
        View separatorHeading = parentView.findViewById(R.id.page_info_heading_separator);
        View closeButton = parentView.findViewById(R.id.page_info_close);

        disambigHeading.setText(getStringForArticleLanguage(pageInfo.getTitle(), R.string.page_similar_titles));
        issuesHeading.setText(getStringForArticleLanguage(pageInfo.getTitle(), R.string.dialog_page_issues));

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        parentView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, height));

        issuesList.setAdapter(new IssuesListAdapter(activity, pageInfo.getIssues()));
        disambigList.setAdapter(new DisambigListAdapter(activity, pageInfo.getDisambigs()));
        disambigList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PageTitle title = ((DisambigResult) disambigList.getAdapter().getItem(position)).getTitle();
                HistoryEntry historyEntry = new HistoryEntry(title, HistoryEntry.SOURCE_DISAMBIG);
                dismiss();
                activity.displayNewPage(title, historyEntry);
            }
        });
        PageLongPressHandler.ListViewContextMenuListener contextMenuListener = new LongPressHandler(activity);
        new PageLongPressHandler(getContext(), disambigList, HistoryEntry.SOURCE_DISAMBIG,
                contextMenuListener);

        if (pageInfo.getDisambigs().length > 0) {
            disambigHeading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDisambig();
                }
            });
        } else {
            disambigHeading.setVisibility(View.GONE);
            separatorHeading.setVisibility(View.GONE);
        }
        if (pageInfo.getIssues().length > 0) {
            issuesHeading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showIssues();
                }
            });
        } else {
            issuesHeading.setVisibility(View.GONE);
            separatorHeading.setVisibility(View.GONE);
        }
    }

    /*package*/ void showDisambig() {
        if (flipper.getCurrentView() != flipper.getChildAt(0)) {
            flipper.setInAnimation(getContext(), R.anim.slide_in_left);
            flipper.setOutAnimation(getContext(), R.anim.slide_out_right);
            flipper.showNext();
        }

        disambigHeading.setTypeface(null, Typeface.BOLD);
        disambigHeading.setEnabled(false);
        issuesHeading.setTypeface(null, Typeface.NORMAL);
        issuesHeading.setEnabled(true);
    }

    /*package*/ void showIssues() {
        if (flipper.getCurrentView() != flipper.getChildAt(1)) {
            flipper.setInAnimation(getContext(), R.anim.slide_in_right);
            flipper.setOutAnimation(getContext(), R.anim.slide_out_left);
            flipper.showPrevious();
        }

        disambigHeading.setTypeface(null, Typeface.NORMAL);
        disambigHeading.setEnabled(true);
        issuesHeading.setTypeface(null, Typeface.BOLD);
        issuesHeading.setEnabled(false);
    }

    private class LongPressHandler extends PageActivityLongPressHandler
            implements PageLongPressHandler.ListViewContextMenuListener {
        LongPressHandler(@NonNull PageActivity activity) {
            super(activity);
        }

        @Override
        public PageTitle getTitleForListPosition(int position) {
            return ((DisambigResult) disambigList.getAdapter().getItem(position)).getTitle();
        }

        @Override
        public void onOpenLink(PageTitle title, HistoryEntry entry) {
            super.onOpenLink(title, entry);
            dismiss();
        }

        @Override
        public void onOpenInNewTab(PageTitle title, HistoryEntry entry) {
            super.onOpenInNewTab(title, entry);
            dismiss();
        }

        @Override
        public void onCopyLink(PageTitle title) {
            super.onCopyLink(title);
            dismiss();
        }

        @Override
        public void onShareLink(PageTitle title) {
            super.onShareLink(title);
            dismiss();
        }

        @Override
        public void onSavePage(PageTitle title) {
            super.onSavePage(title);
            dismiss();
        }
    }
}
