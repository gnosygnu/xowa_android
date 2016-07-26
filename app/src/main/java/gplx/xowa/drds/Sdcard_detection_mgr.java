package gplx.xowa.drds;
import android.content.Context;

import org.wikipedia.BuildConfig;

import java.io.*;

import gplx.core.lists.Iterator_null;

public class Sdcard_detection_mgr {
    public static File Find_writeable_root_or_null(Context context, Sdcard_detection_strategy[] ary) {
        // String root_rhs = "/Android/data/org.xowa"; //+ BuildConfig.APPLICATION_ID;
        String root_rhs = Get_root_rhs();
        for (Sdcard_detection_strategy strategy : ary) {
            String[] dirs = strategy.Get_dirs(context);
            File rv = Get_first_or_null(root_rhs, dirs);
            if (rv != null) return rv;  // file found; return it
        }
        return null;
    }
    public static File Get_first_or_null(String root_rhs, String[] dirs) {
        for (String dir_str : dirs) {
            String dir_path = dir_str + root_rhs;           // EX: "/ext/sdCard" + "/Android/data/org.gplx"
            if (Is_internal(dir_path)) continue;            // ignore emulated; physical storage wanted
            File dir = new File(dir_path);
            if (!dir.exists()) continue;                    // dir doesn't exist; don't bother trying to create b/c permission won't be available at 4.4+
            if (Dir_is_writeable(dir)) return dir;
        }
        return null;
    }
    public static boolean Dir_is_writeable(File dir) {     // check for permissions by create a temp file
        String tmp_path = dir.getAbsolutePath() + "/temp.tmp";
        File tmp = new File(tmp_path);
        FileOutputStream tmp_stream = null;
        try {tmp_stream = new FileOutputStream(tmp.getPath());}
        catch (FileNotFoundException e) {return false;}
        try {tmp_stream.close();}
        catch (IOException e) {return false;}
        tmp.delete();
        return true;
    }
    public static String Get_root_rhs() {return "/Android/data/" + BuildConfig.APPLICATION_ID;}
    private static boolean Is_internal(String path) {return path.contains("/emulated/");}
}
interface Sdcard_detection_strategy {
    int Sort_idx();
    String[] Get_dirs(Context context);
}
class Strategy__storage_dir implements Sdcard_detection_strategy {
    public Strategy__storage_dir(int sort_idx) {this.sort_idx = sort_idx;}
    public int Sort_idx() {return sort_idx;} private final int sort_idx;
    public String[] Get_dirs(Context context) {
        File storage_root = new File("/storage");
        String[] sub_dirs = storage_root.list();
        for (int i = 0; i < sub_dirs.length; ++i) {
            sub_dirs[i] = "/storage/" + sub_dirs[i];
        }
        return sub_dirs;
    }
}
class Strategy__envvar__external_storage implements Sdcard_detection_strategy {
    public Strategy__envvar__external_storage(int sort_idx) {this.sort_idx = sort_idx;}
    public int Sort_idx() {return sort_idx;} private final int sort_idx;
    public String[] Get_dirs(Context context) {
        return new String[] {System.getenv("EXTERNAL_STORAGE")};
    }
}class Strategy__envvar__secondary_storage implements Sdcard_detection_strategy {
    public Strategy__envvar__secondary_storage(int sort_idx) {this.sort_idx = sort_idx;}
    public int Sort_idx() {return sort_idx;} private final int sort_idx;
    public String[] Get_dirs(Context context) {
        return new String[] {System.getenv("SECONDARY_STORAGE")};
    }
}
