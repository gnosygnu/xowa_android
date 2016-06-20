package gplx.core.net; import gplx.*; import gplx.core.*;
import gplx.core.ios.*; import gplx.core.ios.streams.*;
public interface Http_client_wtr {
	void Stream_(Object o);
	void Write_bry(byte[] bry);
	void Write_str(String s);
	void Write_mid(byte[] bry, int bgn, int end);
	void Write_stream(Io_stream_rdr stream_rdr);
	void Rls();
}
