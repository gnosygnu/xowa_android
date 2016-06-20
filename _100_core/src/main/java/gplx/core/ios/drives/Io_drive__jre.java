package gplx.core.ios.drives; import gplx.*; import gplx.core.*; import gplx.core.ios.*;
public class Io_drive__jre implements Io_drive {
	public long Get_space_total(Io_url drive) {return new java.io.File(drive.Xto_api()).getTotalSpace();}	//#<>return 0~return new java.io.File(drive.Xto_api()).getTotalSpace()
	public long Get_space_free (Io_url drive) {return new java.io.File(drive.Xto_api()).getFreeSpace();}	//#<>return 0~return new java.io.File(drive.Xto_api()).getFreeSpace()
}
