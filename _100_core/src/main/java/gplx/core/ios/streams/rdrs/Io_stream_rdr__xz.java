package gplx.core.ios.streams.rdrs; import gplx.*; import gplx.core.*; import gplx.core.ios.*; import gplx.core.ios.streams.*;
public class Io_stream_rdr__xz extends Io_stream_rdr__base {
	@Override public byte Tid() {return Io_stream_tid_.Tid__xz;}
	//#{body
	@Override public java.io.InputStream Wrap_stream(java.io.InputStream stream) {
		try {return new org.tukaani.xz.XZInputStream(stream);}
		catch (Exception exc) {throw Err_.new_wo_type("failed to open xz stream");}
	}
	//#}
}
