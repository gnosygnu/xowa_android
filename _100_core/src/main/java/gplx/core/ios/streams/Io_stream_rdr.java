package gplx.core.ios.streams; import gplx.*; import gplx.core.*; import gplx.core.ios.*;
public interface Io_stream_rdr extends Rls_able {
	byte Tid();
	boolean Exists();
	Io_url Url(); Io_stream_rdr Url_(Io_url v);
	long Len(); Io_stream_rdr Len_(long v);
	Io_stream_rdr Open();
	void Open_mem(byte[] v);
	Object Under();

	int Read(byte[] bry, int bgn, int len);
	long Skip(long len);
}
