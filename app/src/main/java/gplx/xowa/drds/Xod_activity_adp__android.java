package gplx.xowa.drds;

import android.app.Activity;

import java.io.File;

import gplx.xowa.drds.files.Xod_activity_adp;

public class Xod_activity_adp__android implements Xod_activity_adp {
    private final Activity activity;
    public Xod_activity_adp__android(Activity activity) {
        this.activity = activity;
    }
    @Override
    public String Fsys__files_dir() {return File_utl.To_str(activity.getFilesDir());}

    @Override
    public String Fsys__cache_dir() {return File_utl.To_str(activity.getCacheDir());}

    @Override
    public String Fsys__sdcard_rw_or_null() {
        File rv = Sdcard_detection_mgr.Find_writeable_root_or_null(activity);
        return rv == null ? null : File_utl.To_str(rv);
    }
}
