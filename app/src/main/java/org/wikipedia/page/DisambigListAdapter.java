package org.wikipedia.page;

import org.wikipedia.ParcelableLruCache;
import org.wikipedia.R;
import org.wikipedia.Site;
import org.wikipedia.WikipediaApp;
import org.wikipedia.pageimages.PageImagesTask;
import org.wikipedia.views.GoneIfEmptyTextView;
import org.wikipedia.wikidata.GetDescriptionsTask;
import com.squareup.picasso.Picasso;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ListAdapter for disambiguation items.
 */
class DisambigListAdapter extends ArrayAdapter<DisambigResult> {
    private static final int MAX_CACHE_SIZE_IMAGES = 24;
    private final ParcelableLruCache<String> pageImagesCache
            = new ParcelableLruCache<>(MAX_CACHE_SIZE_IMAGES, String.class);
    private final Activity activity;
    private final DisambigResult[] items;
    private final WikipediaApp app;
    private final Site site;

    /**
     * Constructor
     * @param activity The current activity.
     * @param items The objects to represent in the ListView.
     */
    DisambigListAdapter(Activity activity, DisambigResult[] items) {
        super(activity, 0, items);
        this.activity = activity;
        this.items = items;
        app = (WikipediaApp) getContext().getApplicationContext();
        site = app.getPrimarySite();
        requestPageImages();
        fetchDescriptions();
    }

    private void requestPageImages() {
        List<PageTitle> titleList = new ArrayList<>();
        for (DisambigResult r : items) {
            if (pageImagesCache.get(r.getTitle().getPrefixedText()) == null) {
                // not in our cache yet
                titleList.add(r.getTitle());
            }
        }
        if (titleList.isEmpty()) {
            return;
        }

        PageImagesTask imagesTask = new PageImagesTask(
                app.getAPIForSite(site),
                site,
                titleList,
                (int)(WikipediaApp.PREFERRED_THUMB_SIZE * WikipediaApp.getInstance().getScreenDensity())) {
            @Override
            public void onFinish(Map<PageTitle, String> result) {
                for (Map.Entry<PageTitle, String> entry : result.entrySet()) {
                    if (entry.getValue() == null) {
                        continue;
                    }
                    pageImagesCache.put(entry.getKey().getPrefixedText(), entry.getValue());
                }
                notifyDataSetInvalidated();
            }

            @Override
            public void onCatch(Throwable caught) {
                // Don't actually do anything.
                // Thumbnails are expendable
            }
        };
        imagesTask.execute();
    }

    /**
     * Start getting Wikidata descriptions (directly from the current Wikipedia site).
     */
    private void fetchDescriptions() {
        List<PageTitle> titleList = new ArrayList<>();
        for (DisambigResult r : items) {
            titleList.add(r.getTitle());
        }
        if (titleList.isEmpty()) {
            return;
        }

        new GetDescriptionsTask(app.getPrimarySiteApi(), site, titleList) {
            @Override
            public void onFinish(Map<PageTitle, Void> result) {
                notifyDataSetChanged();
            }
            @Override
            public void onCatch(Throwable caught) {
                // descriptions are expendable
            }
        }.execute();
    }

    class ViewHolder {
        private ImageView icon;
        private TextView title;
        private TextView description;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_page_list_entry, null);
            holder = new ViewHolder();
            holder.icon = (ImageView) convertView.findViewById(R.id.page_list_item_image);
            holder.title = (TextView) convertView.findViewById(R.id.page_list_item_title);
            holder.description = (GoneIfEmptyTextView) convertView.findViewById(R.id.page_list_item_description);
            convertView.setTag(holder);
        } else {
            // view already defined, retrieve view holder
            holder = (ViewHolder) convertView.getTag();
        }

        final DisambigResult item = items[position];
        holder.title.setText(item.getTitle().getDisplayText());

        holder.description.setText(item.getTitle().getDescription());

        String thumbnail = pageImagesCache.get(item.getTitle().getDisplayText());
        if (thumbnail == null) {
            Picasso.with(parent.getContext())
                   .load(R.drawable.ic_pageimage_placeholder)
                   .into(holder.icon);
        } else {
            Picasso.with(parent.getContext())
                   .load(thumbnail)
                   .placeholder(R.drawable.ic_pageimage_placeholder)
                   .error(R.drawable.ic_pageimage_placeholder)
                   .into(holder.icon);
        }

        return convertView;
    }
}
