package gplx.xowa.drds;

import android.content.Context;

import java.io.File;

import gplx.CompareAble;
import gplx.Int_;
import gplx.Io_mgr;
import gplx.Io_url;
import gplx.Io_url_;
import gplx.List_adp;
import gplx.List_adp_;
import gplx.dbs.Db_conn;
import gplx.dbs.Db_conn_bldr;
import gplx.xowa.addons.bldrs.xodirs.Xobc_xodir_cfg;
import gplx.xowa.users.cfgs.Xou_cfg_mgr;

public class Drd_bootstrap_mgr {
    // returns root org.xowa dir; EX: "/sdcard/Android/data/org.xowa/"
    public static Io_url Find_app_dir(Context context) {
        // first, try "normal" user by checking cfg_mgr; if cfg_val exists, use it;
        Io_url app_dflt_dir = Get_app_dflt_dir(context);
        Xou_cfg_mgr cfg_mgr = Cfg__get_or_make(app_dflt_dir);
        String cfg_val = cfg_mgr.Get_app_str_or(Xobc_xodir_cfg.Key__selected_dir, null);
        if  (   cfg_val != null
            &&  Sdcard_detection_mgr.Dir_is_writeable(new File(cfg_val))    // make sure dir is still writeable; handles off situations where cfg_val points to no-longer accessible location (SD card removed?); DATE:2016-10-16
            )
            return Io_url_.new_dir_(cfg_val);

        // identify "upgrade" user by looping dirs and finding /xowa/wiki/any_wiki/
        Drd_bootstrap_data data = new Drd_bootstrap_data(context, app_dflt_dir);
        Io_url rv = Find_existing_dir_by_wikis_or_null(data);
        if (rv == null) // no wikis; user is "brand_new"; grab "best" dir
            rv = Find_external_dir_or_dflt_dir(data);

        // save to cfg
        cfg_mgr.Set_app_str(Xobc_xodir_cfg.Key__selected_dir, rv.Raw());
        return rv;
    }
    private static Io_url Get_app_dflt_dir(Context context) {
        return Io_url_.new_any_(context.getApplicationInfo().dataDir + "/");    // EX: "/data/user/0/org.xowa" + "/"
    }
    private static Xou_cfg_mgr Cfg__get_or_make(Io_url app_dflt_dir) {
        Io_url usr_db_url = app_dflt_dir.GenSubFil_nest("files", "usr-anonymous.sqlite3");
        Db_conn usr_db_conn = Db_conn_bldr.Instance.Get_or_autocreate(true, usr_db_url);
        Xou_cfg_mgr rv = new Xou_cfg_mgr();
        rv.Init_by_app(usr_db_conn);
        return rv;
    }
    private static Io_url Find_existing_dir_by_wikis_or_null(Drd_bootstrap_data data) {
        // NOTE: strategy order is "bad" order from v3.7.1.1; sort_idx tries to correct it
        Sdcard_detection_strategy[] strategy_ary = new Sdcard_detection_strategy[]
        { new Strategy__envvar__external_storage(100)
        , new Strategy__envvar__secondary_storage(200) // NOTE: needed for BLU-STUDIO C 5+5 LTE; DATE: 2016-06-28
        , new Strategy__storage_dir(0)
        };

        // loop strategies
        Io_url rv_url = null;
        int rv_count = 0;
        for (Sdcard_detection_strategy strategy : strategy_ary) {
            String[] dirs = strategy.Get_dirs(data.Ctx());

            // find 1st read-write dir
            File dir_as_file = Sdcard_detection_mgr.Get_first_or_null(data.Root_rhs(), dirs);

            // if nothing found, continue; else make url and add to candidates
            if (dir_as_file == null) continue;
            Io_url candidate_url = Io_url_.new_dir_(dir_as_file.getAbsolutePath() + "/");
            data.Dirs__add(candidate_url, strategy.Sort_idx());

            // check if "/xowa/wiki" exists;
            Io_url wikis_dir_url = candidate_url.GenSubDir_nest("files", "xowa", "wiki");  // EX: "/sdcard" + "/Android/data/org.xowa/" + "files/xowa/wiki/"
            if (Io_mgr.Instance.ExistsDir(wikis_dir_url)) {
                Io_url[] wikis = Io_mgr.Instance.QueryDir_args(wikis_dir_url).DirInclude_().ExecAsUrlAry();
                int wikis_len = wikis.length;
                if (    wikis_len > 0           // /wiki/ exists
                    &&  wikis_len > rv_count) { // more dirs exist than found so far
                    rv_url = candidate_url;
                    rv_count = wikis_len;
                }
            }
        }
        return rv_url;
    }
    private static Io_url Find_external_dir_or_dflt_dir(Drd_bootstrap_data data) {
        data.Dirs__sort();
        return data.Dirs__len() == 0 ? data.Dflt_dir() : data.Dirs__get_at(0).Url();
    }
}
class Drd_bootstrap_data {
    private final List_adp dirs = List_adp_.New();

    public Drd_bootstrap_data(Context context, Io_url app_dflt_dir) {
        this.ctx = context;
        this.app_dflt_dir = app_dflt_dir;
        this.root_rhs = Sdcard_detection_mgr.Get_root_rhs();  // EX: "/Android/data/org.xowa"/
    }
    public Context Ctx() {return ctx;} private final Context ctx;
    public Io_url Dflt_dir() {return app_dflt_dir;} private final Io_url app_dflt_dir;
    public String Root_rhs() {return root_rhs;} private final String root_rhs;
    public int Dirs__len() {return dirs.Len();}
    public Drd_bootstrap_dir Dirs__get_at(int i) {return (Drd_bootstrap_dir)dirs.Get_at(i);}
    public void Dirs__sort() {dirs.Sort();}
    public void Dirs__add(Io_url url, int sort_idx) {
        Drd_bootstrap_dir itm = new Drd_bootstrap_dir(url, sort_idx);
        dirs.Add(itm);
    }
}
class Drd_bootstrap_dir implements CompareAble {
    public Drd_bootstrap_dir(Io_url url, int sort_idx) {
        this.url = url;
        this.sort_idx = sort_idx;
    }
    public Io_url Url() {return url;} private final Io_url url;
    public int Sort_idx() {return sort_idx;} private final int sort_idx;

    @Override public int compareTo(Object obj) {
        return Int_.Compare(sort_idx, ((Drd_bootstrap_dir)obj).sort_idx);
    }
}
