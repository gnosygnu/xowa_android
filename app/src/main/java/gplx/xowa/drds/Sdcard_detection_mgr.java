package gplx.xowa.drds;
import android.content.Context;

import org.wikipedia.BuildConfig;

import java.io.*;
public class Sdcard_detection_mgr {
    private static Sdcard_detection_strategy[] Ary = new Sdcard_detection_strategy[]
    { new Strategy__storage_dir()
    };
    public static File Find_writeable_root_or_null(Context context) {
        // String root_rhs = "/Android/data/org.xowa"; //+ BuildConfig.APPLICATION_ID;
        String root_rhs = "/Android/data/" + BuildConfig.APPLICATION_ID;
        for (Sdcard_detection_strategy strategy : Ary) {
            String[] dirs = strategy.Get_dirs(context);
            File rv = Get_first_or_null(root_rhs, dirs);
            if (rv != null) return rv;  // file found; return it
        }
        return null;
    }
    private static File Get_first_or_null(String root_rhs, String[] dirs) {
        for (String dir_str : dirs) {
            String dir_path = dir_str + root_rhs;           // EX: "/ext/sdCard" + "/Android/data/org.gplx"
            if (dir_path.contains("/emulated/")) continue;  // ignore emulated; physical storage wanted
            File dir = new File(dir_path);
            if (!dir.exists()) continue;                    // dir doesn't exist; don't bother trying to create b/c permission won't be available at 4.4+
            if (Dir_is_writeable(dir)) return dir;
        }
        return null;
    }
    private static boolean Dir_is_writeable(File dir) {     // check for permissions by create a temp file
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
}
interface Sdcard_detection_strategy {
    String[] Get_dirs(Context context);
}
class Strategy__storage_dir implements Sdcard_detection_strategy {
    public String[] Get_dirs(Context context) {
        File storage_root = new File("/storage");
        String[] sub_dirs = storage_root.list();
        for (int i = 0; i < sub_dirs.length; ++i) {
            sub_dirs[i] = "/storage/" + sub_dirs[i];
        }
        return sub_dirs;
    }
}