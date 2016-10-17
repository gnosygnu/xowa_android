package gplx.xowa.files.bins; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
import gplx.core.ios.*; import gplx.core.ios.streams.*;
public class Io_download_mgr__memory implements Io_download_mgr {
	private final    Ordered_hash hash = Ordered_hash_.New();
	public void	Clear() {hash.Clear();}
	public void Upload_data(String url, byte[] data) {hash.Add(url, data);}
	public Io_stream_rdr Download_as_rdr(String url) {
		byte[] data = (byte[])hash.Get_by(url); if (data == null) return Io_stream_rdr_.Noop;
		return Io_stream_rdr_.New__mem(data);
	}
}
