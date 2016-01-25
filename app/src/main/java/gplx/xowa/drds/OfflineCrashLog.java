package gplx.xowa.drds;

import android.util.Log;
import android.widget.RemoteViews;

import net.hockeyapp.android.Constants;
import net.hockeyapp.android.CrashManagerListener;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.UUID;

import gplx.Io_mgr;

public class OfflineCrashLog {
    public static String Last_error() {return last_error;} private static String last_error = "";
    public static String Load() {return Io_mgr.Instance.LoadFilStr(Constants.FILES_PATH + "xowa_crash_log.txt");}
    public static String Save(Throwable exception, CrashManagerListener listener) {
        final Date now = new Date();
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);

        String rv = "";
        exception.printStackTrace(printWriter);

        try {
            // Create filename from a random uuid
            String filename = UUID.randomUUID().toString();
            String path = Constants.FILES_PATH + "/" + filename + ".stacktrace";
            Log.d(Constants.TAG, "Writing unhandled exception to: " + path);

            // Write the stacktrace to disk
            StringWriter write = new StringWriter();

            // HockeyApp expects the package name in the first line!
            write.write("Package: " + Constants.APP_PACKAGE + "\n");
            write.write("Version Code: " + Constants.APP_VERSION + "\n");
            write.write("Version Name: " + Constants.APP_VERSION_NAME + "\n");

            if ((listener == null) || (listener.includeDeviceData())) {
                write.write("Android: " + Constants.ANDROID_VERSION + "\n");
                write.write("Manufacturer: " + Constants.PHONE_MANUFACTURER + "\n");
                write.write("Model: " + Constants.PHONE_MODEL + "\n");
            }

            if (Constants.CRASH_IDENTIFIER != null && (listener == null || listener.includeDeviceIdentifier())) {
                write.write("CrashReporter Key: " + Constants.CRASH_IDENTIFIER + "\n");
            }

            write.write("Date: " + now + "\n");
            write.write("\n");
            write.write(result.toString());

            if (listener != null) {
//                writeValueToFile(limitedString(listener.getUserID()), filename + ".user");
//                writeValueToFile(limitedString(listener.getContact()), filename + ".contact");
//                writeValueToFile(listener.getDescription(), filename + ".description");
                write.write("Description: " + listener.getDescription());
            }

            write.flush();
            write.close();
            rv = write.toString();
            last_error = rv;
            Io_mgr.Instance.SaveFilStr(Constants.FILES_PATH + "xowa_crash_log.txt",  rv);
        }
        catch (Exception another) {
            Log.e(Constants.TAG, "Error saving exception stacktrace!\n", another);
        }
        return rv;
    }
}
