package gplx.core.ios; import gplx.*; import gplx.core.*;
public class IoErr {
	public static String Namespace				= "gplx.core.ios.";
	public static String FileIsReadOnly_key		= Namespace + "FileIsReadOnlyError";
	public static String FileNotFound_key		= Namespace + "FileNotFoundError";
	public static Err FileIsReadOnly(Io_url url) {
		return Err_.new_(FileIsReadOnly_key, "file is read-only", "url", url.Xto_api()).Trace_ignore_add_1_();
	}
	public static Err FileNotFound(String op, Io_url url) {
		// file is missing -- op='copy' file='C:\a.txt' copyFile_target='D:\a.txt' 
		return Err_.new_(FileNotFound_key, "file not found", "op", op, "file", url.Xto_api()).Trace_ignore_add_1_();
	}
}