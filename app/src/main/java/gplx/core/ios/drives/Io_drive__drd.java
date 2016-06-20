package gplx.core.ios.drives;

import android.os.StatFs;
import gplx.Io_url;

public class Io_drive__drd implements Io_drive {
    public long Get_space_total(Io_url drive) {return new StatFs(drive.Xto_api()).getTotalBytes();}
    public long Get_space_free (Io_url drive) {return new StatFs(drive.Xto_api()).getAvailableBytes();}
}
