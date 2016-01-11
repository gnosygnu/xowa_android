package gplx.core.ios; import gplx.*; import gplx.core.*;
public interface Io_stream_wtr extends Rls_able {
	byte Tid();
	Io_url Url(); Io_stream_wtr Url_(Io_url v);
	void Trg_bfr_(Bry_bfr v);
	Io_stream_wtr Open();
	byte[] To_ary_and_clear();

	void Write(byte[] bry, int bgn, int len);
	void Flush();
}
