package gplx.core.ios.streams.rdrs; import gplx.*; import gplx.core.*; import gplx.core.ios.*; import gplx.core.ios.streams.*;
public class Io_stream_rdr__bzip2 extends Io_stream_rdr__base {
	@Override public byte Tid() {return Io_stream_tid_.Tid__bzip2;}
	//#{body
	@Override public int Read(byte[] bry, int bgn, int len) {
		return Io_stream_rdr_.Read_by_parts(stream, Read_len, bry, bgn, len);
	}
	@Override public java.io.InputStream Wrap_stream(java.io.InputStream stream) {
		try {return new org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream(stream, true);}
		catch (Exception exc) {throw Err_.new_wo_type("failed to open bzip2 stream");}
	}
	private static final int Read_len = Io_mgr.Len_mb * 128;
	//#}
}
