package gplx.xowa.drds;

import android.app.Activity;
import android.widget.Toast;

import org.json.JSONObject;
import org.wikipedia.Site;
import org.wikipedia.WikipediaApp;
import org.wikipedia.bridge.CommunicationBridge;
import org.wikipedia.page.Page;
import org.wikipedia.page.PageProperties;
import org.wikipedia.page.PageTitle;
import org.wikipedia.page.Section;
import org.wikipedia.server.PageLeadProperties;
import org.wikipedia.util.log.L;

import java.util.ArrayList;
import java.util.List;

import gplx.Bry_;
import gplx.Bry_find_;
import gplx.Cancelable;
import gplx.Err_;
import gplx.core.drds.Drd_version;
import gplx.core.net.Gfo_qarg_itm;
import gplx.dbs.*;
import gplx.Gfo_usr_dlg;
import gplx.Gfo_usr_dlg_;
import gplx.Gfo_usr_dlg__gui_;
import gplx.Gfo_usr_dlg__log_base;
import gplx.Gfo_usr_dlg_base;
import gplx.Io_url;
import gplx.Io_url_;
import gplx.List_adp;
import gplx.String_;
import gplx.core.envs.Env_;
import gplx.langs.htmls.encoders.Gfo_url_encoder_;
import gplx.xowa.Xoa_ttl;
import gplx.xowa.Xoa_url;
import gplx.xowa.Xow_wiki;
import gplx.xowa.apps.Xoa_app_mode;
import gplx.xowa.apps.Xoav_app;
import gplx.xowa.drds.OfflinePageLeadProperties;
import gplx.xowa.drds.Xod_app;
import gplx.xowa.drds.Xod_js_wkr;
import gplx.xowa.drds.pages.Xod_page_itm;
import gplx.xowa.htmls.hrefs.Xoh_href_;
import gplx.xowa.htmls.sections.Xoh_section_itm;
import gplx.xowa.specials.search.Xows_ui_async;
import gplx.xowa.wikis.Xowv_wiki;

public class Xod_app_mgr {
    private Activity activity; private CommunicationBridge bridge;
    private Drd_version version;
    private Xod_app drd_app;
    private Xoav_app xo_app;
    private Xod_js_wkr js_wkr;
    private Img_loader img_loader;
    private Page cached_page;
    private PageTitle cur_page;
    public Site Cur_site(WikipediaApp app) {
        return cur_page == null ? app.getPrimarySite() : cur_page.getSite();
    }
    public int Imported_wikis_count() {
        if (drd_app == null) Init_app();
        return drd_app.Get_wiki_count();
    }
    public Xod_app_mgr Init(Activity activity, CommunicationBridge bridge) {
        this.activity = activity; this.bridge = bridge;
        this.version = Drd_version.New(activity.getApplicationContext());
        return this;
    }
    public void Rebind_message_handlers(CommunicationBridge bridge) {
        if (img_loader != null) {
            bridge.addListener("pageLoadComplete", img_loader); // check for null b/c can be called before app is init'd
            js_wkr.Init(activity, bridge);
        }
    }
    public Xowv_wiki Load_wiki_by_url(Io_url core_url) {
        Xowv_wiki rv = xo_app.Wiki_mgr().Import_by_fil(core_url);
        xo_app.Wiki_mgr().Add(rv);
        rv.Init_by_wiki();
        return rv;
    }
    public Page Get_page_or_load(PageTitle title, boolean force_load) {
        cur_page = title;
        if (cached_page != null && cached_page.getTitle().getText() == title.getText() && !force_load) return cached_page;
        if (drd_app == null) Init_app();
        Xow_wiki wiki = drd_app.Get_wiki(title.getSite().getDomain());
        if (wiki == null) wiki = drd_app.Get_wiki("home");
        // get page title; lines needed to handle "A:B" where "A:" is not a ns, even though PageTitle treats "A:" as a namespace
        byte[] canonical_url = Bry_.new_u8(title.getCanonicalUri());
        int ttl_bgn = Bry_find_.Move_fwd(canonical_url, Xoh_href_.Bry__wiki, 0); if (ttl_bgn == Bry_find_.Not_found) throw Err_.new_("drd", "uknown url format: no '/wiki/'", "url", canonical_url);
        Xoa_ttl page_ttl = wiki.Ttl_parse(canonical_url, ttl_bgn, canonical_url.length);
        byte[] page_url_bry = Xoa_ttl.Replace_spaces(Gfo_url_encoder_.Href.Decode(page_ttl.Page_db())); // canonical url has spaces as well as %-encoding

        Xoa_url page_url = wiki.Utl__url_parser().Parse(page_url_bry);

        Xod_page_itm xpg = drd_app.Get_page(wiki, page_url);
        img_loader.Set(wiki, xpg);
        List<Section> sections = Make_sections(xpg);
        PageLeadProperties lead_props = Make_lead_props(page_ttl.Ns().Id_is_special() ? "Import XOWA Wiki" : title.getDisplayText(), xpg.Modified_on(), sections);
        cached_page = new Page(title, sections, new PageProperties(lead_props));
        return cached_page;
    }
    public String[] Search_titles(Cancelable cxl, Xows_ui_async ui_async, String domain, String search) {
        return drd_app.Search_titles(cxl, drd_app.Get_wiki(domain), ui_async, search);
    }
    private void Init_app() {
        // init app
        Env_.Init_drd();
        Gfo_usr_dlg__log_base log = new Gfo_usr_dlg__log_base(); log.Log_dir_(Io_url_.new_dir_("/mnt/sdcard/external_sd/temp/"));
        Gfo_usr_dlg usr_dlg = new Gfo_usr_dlg_base(log, Gfo_usr_dlg__gui_.Console);
        Io_url user_dir = Io_url_.new_dir_(activity.getFilesDir().getAbsolutePath() + "/");
        this.xo_app = new Xoav_app(usr_dlg, Xoa_app_mode.Itm_gui, "drd", user_dir, user_dir, user_dir.GenSubDir("temp"));
        this.drd_app = new Xod_app(xo_app);
        Io_url home_wiki_dir = user_dir.GenSubDir_nest("bin", "any", "xowa", "wiki", "home");
        Xod_home_wiki_installer.Assert(activity, version, "xowa/bin/any/xowa/wiki/home/home.xowa", home_wiki_dir);
        // gplx.xowa.xtns.hieros.Hiero_xtn_mgr.Hiero_root_dir_(Io_url_.new_dir_("android_asset/xowa/bin/xtns/Wikihiero/"));
        Gfo_usr_dlg_.Instance = usr_dlg;

        // init user db
        Drd_db_mgr.Setup(activity.getApplicationContext());
        xo_app.Init_by_app(user_dir.GenSubFil("user-anonymous.sqlite3"));
        xo_app.Wiki_mgr().Load_by_user_data();

        this.js_wkr = new Xod_js_wkr().Init(activity, bridge);
        this.img_loader = new Img_loader(drd_app, js_wkr);
        Rebind_message_handlers(bridge);

        Load_wiki_by_url(home_wiki_dir.GenSubFil("home.xowa"));
//        String domain_name = "en.wikipedia.org";
//        Load_wiki_by_url(Io_url_.new_dir_("/storage/external_SD/xowa/").GenSubFil_nest("wiki", domain_name, domain_name + "-core.xowa"));
    }
    private static List<Section> Make_sections(Xod_page_itm xpg) {
        List<Section> rv = new ArrayList<>();
        List_adp list = xpg.Section_list();
        int len = list.Count();
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
    public static final String Import_root = "Special:XowaFileBrowser?cmd=xowa.wiki.add&path=/";
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
        drd_app.Load_files(wiki, xpg, js_wkr);
    }
}