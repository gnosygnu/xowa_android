package gplx.core.net; import gplx.*; import gplx.core.*;
import gplx.core.ios.*;
class Gfo_inet_conn__http implements Gfo_inet_conn {
	private final IoEngine_xrg_downloadFil downloader = IoEngine_xrg_downloadFil.new_("", Io_url_.Empty);
	public int				Tid() {return Gfo_inet_conn_.Tid__http;}
	public void				Clear()										{throw Err_.new_unsupported();}
	public void				Upload_by_bytes(String url, byte[] data)	{throw Err_.new_unsupported();}
	public byte[]			Download_as_bytes_or_null(String url) {
		try {return downloader.Exec_as_bry(url);}
		catch (Exception e) {Err_.Noop(e); return null;}
	}
}
