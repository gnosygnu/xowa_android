package gplx.xowa.drds;

import android.app.Activity;

import java.io.File;

import gplx.Bry_bfr;
import gplx.Io_mgr;
import gplx.Io_url;
import gplx.List_adp;
import gplx.List_adp_;
import gplx.String_;
import gplx.core.drds.Drd_asset_mgr_;
import gplx.core.drds.Drd_version;
import gplx.xowa.apps.fsys.Xoa_fsys_mgr;
import gplx.xowa.drds.ios.assets.Xod_asset_mgr;

public class Xod_asset_fsys_sync {
    public static void Sync_fil(Xod_media_scanner__drd media_scanner, Drd_version version, Xoa_fsys_mgr fsys_mgr, String... nest) {
        Io_url src_fil = fsys_mgr.Http_root().GenSubDir_nest(nest);
        Io_url trg_fil = fsys_mgr.Root_dir().GenSubDir_nest(nest);

        // do not sync if .version exists
        Io_url version_url = trg_fil.OwnerDir().GenSubFil_ary(trg_fil.NameOnly() + ".version");
//        if  (   Io_mgr.Instance.ExistsFil(version_url)
//        &&  String_.Eq(Io_mgr.Instance.LoadFilStr(version_url), version.Name())
//        )
//            return;

        // save version; sync recursively
        Io_mgr.Instance.SaveFilStr(version_url, version.Name());
        media_scanner.Add(version_url);
        media_scanner.Add(trg_fil);
        byte[] src_fil_bry = Io_mgr.Instance.LoadFilBry(src_fil);
        Io_mgr.Instance.SaveFilBry(trg_fil, src_fil_bry);
        media_scanner.Scan();
    }
    public static void Sync_dir(Activity activity, Xod_media_scanner__drd media_scanner, Drd_version version, Io_url root, String... nest) {
        String src_root = String_.Concat_with_str("/", nest);
        Io_url trg_root = root.GenSubDir_nest(nest);

        // do not sync if .version exists
        Io_url version_url = trg_root.GenSubFil_ary(trg_root.NameOnly() + ".version");
        if  (   Io_mgr.Instance.ExistsFil(version_url)
            &&  String_.Eq(Io_mgr.Instance.LoadFilStr(version_url), version.Name())
            )
            return;

        // save version; sync recursively
        Io_mgr.Instance.SaveFilStr(version_url, version.Name());
        media_scanner.Add(version_url);
        Sync_dir_recur(activity, media_scanner, src_root, trg_root);
        media_scanner.Scan();
    }
    private static void Sync_dir_recur(Activity activity, Xod_media_scanner__drd media_scanner, String src_path, Io_url trg_path) {
        String[] src_subs = Drd_asset_mgr_.List_as_str(activity, src_path);
        int src_subs_len = src_subs.length;
        if (src_subs_len > 0) { // src_path is dir
            for (int i = 0; i < src_subs_len; ++i) {
                String src_sub_dir = src_subs[i];
                Sync_dir_recur(activity, media_scanner, src_path + "/" + src_sub_dir, trg_path.GenSubDir(src_sub_dir));
            }
        } else {
            byte[] src_fil_bry = Drd_asset_mgr_.Load_as_bry(activity, src_path);
            Io_mgr.Instance.SaveFilBry(trg_path, src_fil_bry);
            media_scanner.Add(trg_path);
        }
    }
}
//public class Xod_asset_fsys_sync {
//    public static void Sync_dir(Activity activity, Xod_media_scanner__drd media_scanner, Drd_version version, Io_url root, String... nest) {
//        String src_root = String_.Concat_with_str("/", nest);
//        Io_url trg_root = root.GenSubDir_nest(nest);
//
//        // do not sync if .version exists
//        Io_url version_url = trg_root.GenSubFil_ary(trg_root.NameOnly() + ".version");
//        if  (   Io_mgr.Instance.ExistsFil(version_url)
//            &&  String_.Eq(Io_mgr.Instance.LoadFilStr(version_url), version.Name())
//            )
//            return;
//
//        // save version; sync recursively
//        Io_mgr.Instance.SaveFilStr(version_url, version.Name());
//        media_scanner.Add(version_url);
//        Sync_dir_recur(activity, media_scanner, src_root, trg_root);
//        media_scanner.Scan();
//    }
//    public static void Sync_fil(Activity activity, Xod_media_scanner__drd media_scanner, Drd_version version, Io_url root, String... nest) {
//        String src_root = String_.Concat_with_str("/", nest);
//        Io_url trg_root = root.GenSubDir_nest(nest);
//
//        // do not sync if .version exists
//        Io_url version_url = trg_root.GenSubFil_ary(trg_root.NameOnly() + ".version");
//        if  (   Io_mgr.Instance.ExistsFil(version_url)
//        &&  String_.Eq(Io_mgr.Instance.LoadFilStr(version_url), version.Name())
//        )
//            return;
//
//        // save version; sync recursively
//        Io_mgr.Instance.SaveFilStr(version_url, version.Name());
//        media_scanner.Add(version_url);
//        Sync_dir_recur(activity, media_scanner, src_root, trg_root);
//        media_scanner.Scan();
//    }
//    private static void Sync_dir_recur(Activity activity, Xod_media_scanner__drd media_scanner, String src_path, Io_url trg_path) {
//        String[] src_subs = Drd_asset_mgr_.List_as_str(activity, src_path);
//        int src_subs_len = src_subs.length;
//        if (src_subs_len > 0) { // src_path is dir
//            for (int i = 0; i < src_subs_len; ++i) {
//                String src_sub_dir = src_subs[i];
//                Sync_dir_recur(activity, media_scanner, src_path + "/" + src_sub_dir, trg_path.GenSubDir(src_sub_dir));
//            }
//        } else {
//            byte[] src_fil_bry = Drd_asset_mgr_.Load_as_bry(activity, src_path);
//            Io_mgr.Instance.SaveFilBry(trg_path, src_fil_bry);
//            media_scanner.Add(trg_path);
//        }
//    }
//}
class File_utl {
    public static String Ary_to_str(Bry_bfr bfr, File[] ary) {
        int ary_len = ary.length;
        if (ary_len == 1) return To_str(ary[0]);
        for (File itm : ary) {
            bfr.Add_str_u8(To_str(itm)).Add_byte_colon();
        }
        return bfr.To_str_and_clear();
    }
    public static String To_str(File f) {
        return f == null ? "<<NULL>>" : f.getAbsolutePath();
    }
}