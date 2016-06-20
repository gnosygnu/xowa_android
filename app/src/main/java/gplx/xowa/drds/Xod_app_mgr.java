package gplx.xowa.drds;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import org.json.*;
import org.wikipedia.BuildConfig;
import org.wikipedia.Site;
import org.wikipedia.WikipediaApp;
import org.wikipedia.bridge.CommunicationBridge;
import org.wikipedia.page.*;
import org.wikipedia.server.PageLeadProperties;
import org.wikipedia.util.log.L;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import gplx.*;
import gplx.core.Xod_power_mgr__drd;
import gplx.core.drds.Drd_version;
import gplx.core.envs.Env_;
import gplx.Gfo_log_;
import gplx.core.ios.drives.Io_drive_;
import gplx.core.ios.drives.Io_drive__drd;
import gplx.core.logs.Gfo_log__file;
import gplx.core.net.Net_conn_mgr_;
import gplx.core.net.emails.Gfo_email_mgr_;
import gplx.core.net.emails.Gfo_email_mgr__drd;
import gplx.core.threads.Thread_adp_;
import gplx.dbs.Drd_db_mgr;
import gplx.guis.tabs.Xog_tab_mgr__drd;
import gplx.xowa.*;
import gplx.xowa.addons.wikis.searchs.searchers.rslts.Srch_rslt_cbk;
import gplx.xowa.apps.Xoav_app;
import gplx.xowa.drds.files.Xod_fsys_mgr;
import gplx.xowa.drds.pages.Xod_page_itm;
import gplx.xowa.drds.powers.Xod_power_mgr_;
import gplx.xowa.guis.cbks.Xog_cbk_wkr;
import gplx.xowa.htmls.sections.Xoh_section_itm;
import gplx.xowa.users.data.Xoud_site_row;
import gplx.xowa.wikis.pages.tags.*;

public class Xod_app_mgr {
    private Activity activity; private CommunicationBridge bridge;
    private Drd_version version;
    private Xod_app drd_app;
    public Xoav_app xo_app;
    private Xod_js_wkr js_wkr;
    private Img_loader img_loader;
    private Page cached_page;
    private PageTitle cur_page;
    private Xod_media_scanner__drd media_scanner;
    private Bry_bfr tmp_bfr;
    private Xopg_tag_wtr_cbk__drd tag_wtr_cbk = new Xopg_tag_wtr_cbk__drd();
    private void Boot__init_env(Context context, Gfo_log log) {
        log.Info("app.boot:boot env");
        tmp_bfr = Bry_bfr_.New();
        Env_.Init_drd();
        Drd_db_mgr.Setup(context);
        this.media_scanner = new Xod_media_scanner__drd(context);
        Io_mgr.Instance.Loader_(new Xod_asset_mgr__drd(activity));
        Io_drive_.Instance = new Io_drive__drd();
        Xod_power_mgr_.Instance = new Xod_power_mgr__drd(context);
        Gfo_email_mgr_.Instance = new Gfo_email_mgr__drd(context);
        this.js_wkr = new Xod_js_wkr().Init(activity, bridge);
        log.Info("app.boot:device info"
        , "version_code", BuildConfig.VERSION_CODE
        , "android_os", Build.VERSION.SDK_INT
        , "device_type", Build.MANUFACTURER + ":" + Build.MODEL);
        log.Info("app.boot:device misc"
        , "Network.isWifi", Net_conn_mgr_.Active_type_is_wifi(context)
        , "getExternalStorageDirectory", File_utl.To_str(Environment.getExternalStorageDirectory())
        , "EXTERNAL_STORAGE", System.getenv("EXTERNAL_STORAGE")
        , "SECONDARY_STORAGE", System.getenv("SECONDARY_STORAGE")
        , "getExternalFilesDirs", File_utl.Ary_to_str(tmp_bfr, context.getExternalFilesDirs(null))
        , "getExternalCacheDirs", File_utl.Ary_to_str(tmp_bfr, context.getExternalCacheDirs())
        );
    }
    private void Boot() {
        // init env
        Context context = activity.getApplicationContext();
        Gfo_log log = Gfo_log_.Instance;
        Boot__init_env(context, log);

        // init app
        log.Info("app.boot:boot app");
        Xod_fsys_mgr fsys_mgr = new Xod_fsys_mgr(log, new Xod_activity_adp__android(activity));
        Xog_tab_mgr__drd tab_mgr = new Xog_tab_mgr__drd((PageActivity)activity);
        log.Info("app.boot:init app.bgn");
        this.xo_app = Xoav_app.New_by_drd(fsys_mgr, tab_mgr);
        log.Info("app.boot:init app.end");
        tab_mgr.Ctor(xo_app);
        log = Gfo_log_.Instance__set(Gfo_log_.New_file(fsys_mgr.App_root_dir().GenSubDir_nest("user", "anonymous", "app", "tmp", "xolog")));
        media_scanner.Add(((Gfo_log__file)(Gfo_log_.Instance)).Url()).Scan();
        this.drd_app = new Xod_app(xo_app);
        this.img_loader = new Img_loader(drd_app, js_wkr);
        Rebind_message_handlers(bridge);

        // sync files
        log.Info("sync files");
        String[] home_db_nest = String_.Ary("bin", "any", "xowa", "wiki", "home", "home.xowa");
        Xod_asset_fsys_sync.Sync_fil(media_scanner, version, xo_app.Fsys_mgr(), home_db_nest);
        Xod_asset_fsys_sync.Sync_fil(media_scanner, version, xo_app.Fsys_mgr(), String_.Ary("bin", "any", "xowa", "addon", "bldr", "central", "bldr_central.data_db.xowa"));
//        Xod_asset_fsys_sync.Sync_dir(media_scanner, version, xo_app.Fsys_mgr(), String_.Ary("bin", "any", "xowa", "addon", "bldr", "central", "bldr_central.data_db.xowa"));
//        Xod_asset_fsys_sync.Sync_dir(activity, media_scanner, version, app_root, "xowa", "bin", "any", "xowa", "addon");
//        Xod_asset_fsys_sync.Sync_dir(activity, media_scanner, version, app_root, "xowa", "bin", "any", "xowa", "html");

        // init user db; home_db
        log.Info("init user.anonymous.db.bgn");
        xo_app.Init_by_app(fsys_mgr.Usr_data_fil());
        log.Info("init load user wikis");
        xo_app.Wiki_mgr().Load_by_user_data();
        log.Info("init home db");
        Load_wiki_by_url(xo_app.Fsys_mgr().Root_dir().GenSubFil_nest(home_db_nest));
        log.Info("init home db.end");
    }
    public Site Cur_site(WikipediaApp app) {
        return cur_page == null ? app.getPrimarySite() : cur_page.getSite();
    }
    public String Cur_site_domain(WikipediaApp app) {
        Site site = cur_page == null ? app.getPrimarySite() : cur_page.getSite();
        return site.getDomain();
    }
    public Xod_app_mgr Init(Activity activity, CommunicationBridge bridge) {
        this.activity = activity; this.bridge = bridge;
        this.version = Drd_version.New(activity.getApplicationContext());
        tag_wtr_cbk.Bridge_(bridge);
        return this;
    }
    public int Wikis_installed_count(Activity activity) {
        if (drd_app == null) {
            this.Init(activity, null);
            Boot();
        }
        Xoud_site_row[] site_rows = xo_app.User().User_db_mgr().Site_mgr().Get_all();
        int rv = 0;
        for (Xoud_site_row site_row : site_rows) {
            if (String_.Eq(site_row.Domain(), "home")) continue;
            ++rv;
        }
        if (rv == 0) {
            Request_permission();
        }
        return rv;
    }
    private static final int READ_EXTERNAL_STORAGE_CONST = 1, WAKE_LOCK_CONST = 2;
    private void Request_permission() {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_CONST);
        }
    }
    public void Rebind_message_handlers(CommunicationBridge bridge) {
        if (img_loader != null && bridge != null) {
            bridge.addListener("pageLoadComplete", img_loader); // check for null b/c can be called before app is init'd
            js_wkr.Init(activity, bridge);
        }
    }
    public Xow_wiki Load_wiki_by_url(Io_url core_url) {
        Xow_wiki rv = xo_app.Wiki_mgr().Import_by_url(core_url);
        rv.Init_by_wiki();
        return rv;
    }
    public PageTitle Wiki__get_random(WikipediaApp app) {
        Site site = Cur_site(app);
        Xow_wiki wiki = drd_app.Wikis__get_by_domain(site.getDomain());
        Xod_page_itm page_itm = drd_app.Wiki__get_random(wiki, wiki.Ns_mgr().Ns_main());
        return new PageTitle(page_itm.Ttl_db(), site);
    }
    public Page Get_page_or_load(PageTitle title, boolean force_load) {
        Gfo_log_.Instance.Info("Get_page_or_load.bgn", "title", title.getDisplayText());
        cur_page = title;
        if (cached_page != null && cached_page.getTitle().getText() == title.getText() && !force_load) return cached_page;
        if (drd_app == null) Boot();
        Xow_wiki wiki = null;
        try {
            wiki = drd_app.Wikis__get_by_domain(title.getSite().getDomain());
        } catch (Exception e) {return null;}
        if (wiki == null) wiki = drd_app.Wikis__get_by_domain("home");

        byte[] page_url_bry = Xod_app.To_page_url(wiki, title.getCanonicalUri());
        Xoa_ttl page_ttl = wiki.Ttl_parse(page_url_bry);
        Xoa_url page_url = wiki.Utl__url_parser().Parse(page_url_bry);
        if (String_.Eq(page_ttl.Page_db_as_str(), "XowaDownloadCentral")) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WAKE_LOCK) != PackageManager.PERMISSION_GRANTED) {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WAKE_LOCK}, WAKE_LOCK_CONST);
            }
        }

        Xod_page_itm xpg = null;
        try     {xpg = drd_app.Wiki__get_by_url(wiki, page_url);}
        catch (Exception e) {// XOWA: ignore failed pages ; occurs when SDCARD swapped out; DATE:2016-06-11
            return Make_missing_page(title, xpg);
        }
        if (    (page_ttl.Ns().Id_is_special() && xpg.Ttl_special() == null)
            ||  xpg.Ttl_db() == null) {
            return Make_missing_page(title, xpg);
        }
        Gfo_log_.Instance.Info("Get_page_or_load.load");
        img_loader.Set(wiki, xpg);
        bridge.sendMessage("xowa__html__reset", new JSONObject());
        List<Section> sections = Make_sections(xpg);
        String page_title = page_ttl.Ns().Id_is_special() ? xpg.Ttl_special() : title.getDisplayText();
        PageLeadProperties lead_props = Make_lead_props(page_title, xpg.Modified_on(), sections);
        cached_page = new Page(title, sections, new PageProperties(lead_props));
        Process_tags(tag_wtr_cbk, xpg.Head_tags());
        Process_tags(tag_wtr_cbk, xpg.Tail_tags());
        Gfo_log_.Instance.Info("Get_page_or_load.end");
        return cached_page;
    }
    private static Page Make_missing_page(PageTitle title, Xod_page_itm xpg) {
        List<Section> sections = Make_sections(xpg);
        return new Page(title, sections, new PageProperties(Make_lead_props("Missing", DateAdp_.Now().XtoStr_fmt_iso_8561_w_tz(), sections)));
    }
    private static void Process_tags(Xopg_tag_wtr_cbk__drd cbk, Xopg_tag_mgr tag_mgr) {
        Bry_bfr tmp_bfr = Bry_bfr_.New();
        byte[] code = Xopg_tag_wtr.Write(tmp_bfr, false, cbk, tag_mgr);
        cbk.Write_tag(tmp_bfr, Xopg_tag_itm.New_js_code(code));
    }
    public void Search_titles(Cancelable cxl, Srch_rslt_cbk rslt_cbk, String domain, String search, int slab_bgn, int slab_end) {
        drd_app.Wiki__search(cxl, rslt_cbk, drd_app.Wikis__get_by_domain(domain), search, slab_bgn, slab_end);
    }
    public void Gui__cbk_mgr__reg(Xog_cbk_wkr cbk_wkr) {xo_app.Gui__cbk_mgr().Reg(cbk_wkr);}
    public String Html__bridge_mgr__exec_json(String data) {return xo_app.Html__bridge_mgr().Cmd_mgr().Exec(Bry_.new_u8(data));}
    private static List<Section> Make_sections(Xod_page_itm xpg) {
        List<Section> rv = new ArrayList<>();
        List_adp list = xpg == null ? null : xpg.Section_list();
        int len = list == null ? 0 : list.Count();
        if (len == 0) {// always add one section else progress bar won't disappear
            rv.add(new Section(1, 1, "", "", "Page not found"));
        }
        else {
            for (int i = 0; i < len; ++i) {
                Xoh_section_itm section_itm = (Xoh_section_itm) list.Get_at(i);
                rv.add(new Section(section_itm.Uid(), section_itm.Level() - 1, String_.new_u8(section_itm.Header()), String_.new_u8(section_itm.Anchor()), String_.new_u8(section_itm.Content())));
            }
        }
        return rv;
    }
    private static OfflinePageLeadProperties Make_lead_props(String title, String date, List<Section> sections) {
        return new OfflinePageLeadProperties
        ( 0, 1
        , title, title.replaceAll(" ", "_"), "", title + " description", date
        , false, false, false, 1
        , null, null
        , null, sections
        );
    }
    public static final Xod_app_mgr Instance = new Xod_app_mgr();
//    public static String Import_root() {return "Special:XowaFileBrowser?path=/";}
    public static String Import_root() {return "Special:XowaDownloadCentral";}
    public static final String Wikis_root = "Special:XowaWikis";
}
class Img_loader implements CommunicationBridge.JSEventListener {
    private Xod_app drd_app;
    private Xow_wiki wiki;
    private Xod_page_itm xpg;
    private Xod_js_wkr js_wkr;
    public Img_loader(Xod_app drd_app, Xod_js_wkr js_wkr) {
        this.drd_app = drd_app;
        this.js_wkr = js_wkr;
    }
    public void Set(Xow_wiki wiki, Xod_page_itm xpg) {
        this.wiki = wiki;
        this.xpg = xpg;
    }
    @Override
    public void onMessage(String messageType, JSONObject messagePayload) {
        drd_app.Page__load_files(wiki, xpg, js_wkr);
    }
}
class Xopg_tag_wtr_cbk__drd implements Xopg_tag_wtr_cbk {
    private CommunicationBridge bridge;
    public void Bridge_(CommunicationBridge bridge) {
        this.bridge = bridge;
    }
    @Override public void Write_tag(Bry_bfr bfr, Xopg_tag_itm itm) {
        JSONObject wrapper = new JSONObject();
        try {
            wrapper.put("data_source", itm.Source);
            wrapper.put("name", String_.new_u8(itm.Node()));
            wrapper.put("text", String_.new_u8(itm.Body()));
            JSONArray trg_atrs = new JSONArray();
            wrapper.put("atrs", trg_atrs);
            Keyval[] src_atrs = itm.Atrs();
            int src_atrs_len = src_atrs.length;
            for (int i = 0; i < src_atrs_len; ++i) {
                Keyval src_atr = src_atrs[i];
                JSONObject trg_atr = new JSONObject();
                trg_atr.put("key", src_atr.Key());
                trg_atr.put("val", Object_.Xto_str_strict_or_null_mark(src_atr.Val()));
                Log.i("xowa", src_atr.Key() + "=" + Object_.Xto_str_strict_or_null_mark(src_atr.Val()));
                trg_atrs.put(trg_atr);
            }
        } catch (JSONException e) {
            L.logRemoteErrorIfProd(e);
        }
        bridge.sendMessage("xowa__html__add__head", wrapper);
    }
}
