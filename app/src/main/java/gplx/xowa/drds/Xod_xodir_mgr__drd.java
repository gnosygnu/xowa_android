package gplx.xowa.drds;

import android.content.Context;

import java.io.File;

import gplx.Bry_;
import gplx.Ordered_hash;
import gplx.Ordered_hash_;
import gplx.String_;
import gplx.xowa.Xoa_app;
import gplx.xowa.addons.bldrs.xodirs.Xobc_xodir_cfg;
import gplx.xowa.addons.bldrs.xodirs.Xobc_xodir_dir;
import gplx.xowa.addons.bldrs.xodirs.Xobc_xodir_mgr;
import gplx.xowa.apps.Xoav_app;

public class Xod_xodir_mgr__drd implements Xobc_xodir_mgr {
    private final Context context;
    private final Xoav_app app;
    public Xod_xodir_mgr__drd(Context context, Xoav_app app) {this.context = context; this.app = app;}
    @Override public Xobc_xodir_dir[] Get_dirs(Xoa_app app) {
        Ordered_hash hash = Ordered_hash_.New();

        // add default app dir
        String default_data_dir = context.getApplicationInfo().dataDir + "/";
        String selected = app.User().User_db_mgr().Cfg().Get_app_str_or(Xobc_xodir_cfg.Key__selected_dir, default_data_dir);
        Xobc_xodir_dir itm = new Xobc_xodir_dir(String_.Eq(selected, default_data_dir), false, Bry_.new_u8(default_data_dir));
        hash.Add(default_data_dir, itm);

        // add "guessed" external dirs
        Sdcard_detection_strategy[] strategy_ary = new Sdcard_detection_strategy[]
        { new Strategy__storage_dir(-1)
        , new Strategy__envvar__external_storage(-1)
        , new Strategy__envvar__secondary_storage(-1) // NOTE: needed for BLU-STUDIO C 5+5 LTE; DATE: 2016-06-28
        };
        int len = strategy_ary.length;
        String root_rhs = Sdcard_detection_mgr.Get_root_rhs() + "/";
        for (int i = 0; i < len; ++i) {
            Sdcard_detection_strategy strategy = strategy_ary[i];
            String[] dir_strs = strategy.Get_dirs(context);
            for (String dir_str : dir_strs) {
                if (dir_str == null) continue;
                dir_str += root_rhs;
                if (hash.Has(dir_str)) continue;
                File dir = new File(dir_str);
                if (!dir.exists()) continue;                    // dir doesn't exist; don't bother trying to create b/c permission won't be available at 4.4+
                if (!Sdcard_detection_mgr.Dir_is_writeable(dir)) continue;
                itm = new Xobc_xodir_dir(String_.Eq(selected, dir_str), false, Bry_.new_u8(dir_str));
                hash.Add(dir_str, itm);
            }
        }

        // add custom dir
        String custom = app.User().User_db_mgr().Cfg().Get_app_str_or(Xobc_xodir_cfg.Key__custom_dir, "(choose your own folder)");
        itm = new Xobc_xodir_dir(String_.Eq(selected, custom), true, Bry_.new_u8(custom));
        hash.Add(custom, itm);
        return (Xobc_xodir_dir[])hash.To_ary(Xobc_xodir_dir.class);
    }
}
