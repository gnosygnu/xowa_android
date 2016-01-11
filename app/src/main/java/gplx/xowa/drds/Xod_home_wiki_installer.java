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
}
