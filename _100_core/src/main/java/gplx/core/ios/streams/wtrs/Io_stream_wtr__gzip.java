package gplx.core.ios.streams.wtrs; import gplx.*; import gplx.core.*; import gplx.core.ios.*; import gplx.core.ios.streams.*;
public class Io_stream_wtr__gzip extends Io_stream_wtr__base {
	@Override public byte Tid() {return Io_stream_tid_.Tid__gzip;}
	//#{body
	@Override public java.io.OutputStream Wrap_stream(java.io.OutputStream stream) {
		try {return new java.util.zip.GZIPOutputStream(stream);}
		catch (Exception e) {throw Err_.new_exc(e, "io", "failed to open gz stream");}
	}
	//#}
}
