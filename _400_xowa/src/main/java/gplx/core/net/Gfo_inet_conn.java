package gplx.core.net; import gplx.*; import gplx.core.*;
public interface Gfo_inet_conn {
	int				Tid();
	void			Clear();
	void			Upload_by_bytes(String url, byte[] data);
	byte[]			Download_as_bytes_or_null(String url);	// return null instead of throwing exception
}
