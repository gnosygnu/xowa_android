package gplx.core.drds;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class Drd_version {
    public Drd_version(int code, String name) {
        this.code = code; this.name = name;
    }
    public int Code() {return code;} private final int code;
    public String Name() {return name;} private final String name;
    public static Drd_version New(Context context) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {info = manager.getPackageInfo(context.getPackageName(), 0);}
        catch (PackageManager.NameNotFoundException e) {e.printStackTrace();}
        return new Drd_version(info.versionCode, info.versionName);
    }
}
