package gplx.core.security; import gplx.*; import gplx.core.*;
public interface Hash_algo {
	String Key();
	byte[] Hash_bry_as_bry(byte[] src);
	String Hash_bry_as_str(byte[] src);
	String Hash_stream_as_str(gplx.core.consoles.Console_adp console, gplx.core.ios.streams.IoStream src_stream);
	byte[] Hash_stream_as_bry(gplx.core.progs.Gfo_prog_ui prog_ui, gplx.core.ios.streams.IoStream src_stream);
}
