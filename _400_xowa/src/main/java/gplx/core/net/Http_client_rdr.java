package gplx.core.net; import gplx.*; import gplx.core.*;
public interface Http_client_rdr {
	void Stream_(Object o);
	String Read_line();
	byte[] Read_line_as_bry();
	void Rls();
}
