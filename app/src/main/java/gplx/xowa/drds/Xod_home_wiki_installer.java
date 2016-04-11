package gplx.xowa.drds;

import android.app.Activity;

import gplx.Io_mgr;
import gplx.Io_url;
import gplx.String_;
import gplx.core.drds.Drd_asset_mgr_;
import gplx.core.drds.Drd_version;

public class Xod_home_wiki_installer {
    public static boolean Assert(Activity activity, Drd_version version, String src_fil, Io_url trg_dir) {
        Io_url wiki_url     = trg_dir.GenSubFil_ary("home.xowa");
        Io_url version_url  = trg_dir.GenSubFil_ary("home.version");
        if (    Io_mgr.Instance.ExistsFil(wiki_url)
            &&  Io_mgr.Instance.ExistsFil(version_url)
            && String_.Eq(Io_mgr.Instance.LoadFilStr(version_url), version.Name())
        )
            return true;
        byte[] wiki_bry = Drd_asset_mgr_.Load_as_bry(activity, src_fil);
        Io_mgr.Instance.SaveFilBry(wiki_url, wiki_bry);
        Io_mgr.Instance.SaveFilStr(version_url, version.Name());
        return false;
    }
    public static boolean Assert_dir(Activity activity, Drd_version version, String src_root, Io_url trg_root) {
        Io_url version_url  = trg_root.GenSubFil_ary(trg_root.NameOnly() + ".version");
        if  (   Io_mgr.Instance.ExistsFil(version_url)
            &&  String_.Eq(Io_mgr.Instance.LoadFilStr(version_url), version.Name())
            )
            return true;
        Io_mgr.Instance.SaveFilStr(version_url, version.Name());
        Assert_dir_recur(activity, src_root, trg_root);
        return false;
    }
    private static void Assert_dir_recur(Activity activity, String src_path, Io_url trg_path) {
        String [] src_subs = Drd_asset_mgr_.List_as_str(activity, src_path);
        if (src_subs.length > 0) {
            for (String src_sub_dir : src_subs) {
                Assert_dir_recur(activity, src_path + "/" + src_sub_dir, trg_path.GenSubDir(src_sub_dir));
            }
        } else {
            byte[] src_fil_bry = Drd_asset_mgr_.Load_as_bry(activity, src_path);
            Io_mgr.Instance.SaveFilBry(trg_path, src_fil_bry);
        }
    }
}
