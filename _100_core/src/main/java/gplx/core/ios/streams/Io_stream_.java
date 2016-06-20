package gplx.core.ios.streams; import gplx.*; import gplx.core.*; import gplx.core.ios.*;
public class Io_stream_ {	// SERIALIZED
	public static final byte Tid_null = 0, Tid_raw = 1, Tid_zip = 2, Tid_gzip = 3, Tid_bzip2 = 4;
	public static final String Ext_zip = ".zip", Ext_gz = ".gz", Ext_bz2 = ".bz2";

	public static String Obsolete_to_str(byte v) {
		switch (v) {
			case Io_stream_.Tid_raw		: return ".xdat";
			case Io_stream_.Tid_zip		: return ".zip";
			case Io_stream_.Tid_gzip	: return ".gz";
			case Io_stream_.Tid_bzip2	: return ".bz2";
			default						: throw Err_.new_unhandled(v);
		}
	}
	public static byte Obsolete_to_tid(String v) {
		if		(String_.Eq(v, ".xdat"))		return Io_stream_.Tid_raw;
		else if	(String_.Eq(v, ".zip"))			return Io_stream_.Tid_zip;
		else if	(String_.Eq(v, ".gz"))			return Io_stream_.Tid_gzip;
		else if	(String_.Eq(v, ".bz2"))			return Io_stream_.Tid_bzip2;
		else									throw Err_.new_unhandled(v);
	}
	public static String To_str(byte v) {
		switch (v) {
			case Io_stream_.Tid_raw		: return "raw";
			case Io_stream_.Tid_zip		: return "zip";
			case Io_stream_.Tid_gzip	: return "gzip";
			case Io_stream_.Tid_bzip2	: return "bzip2";
			default						: throw Err_.new_unhandled(v);
		}
	}
	public static byte To_tid(String v) {
		if		(String_.Eq(v, "raw"))			return Io_stream_.Tid_raw;
		else if	(String_.Eq(v, "zip"))			return Io_stream_.Tid_zip;
		else if	(String_.Eq(v, "gzip"))			return Io_stream_.Tid_gzip;
		else if	(String_.Eq(v, "bzip2"))		return Io_stream_.Tid_bzip2;
		else									throw Err_.new_unhandled(v);
	}
}
