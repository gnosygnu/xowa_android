package gplx.xowa.files.bins; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
import gplx.core.ios.*; import gplx.core.ios.streams.*;
public class Io_download_mgr_ {
	public static Io_download_mgr			new_system()	{return new Io_download_mgr__system();}
	public static Io_download_mgr__memory	new_memory()	{return new Io_download_mgr__memory();}
}
class Io_download_mgr__system implements Io_download_mgr {
	private final    IoEngine_xrg_downloadFil download_wkr = IoEngine_xrg_downloadFil.new_("", Io_url_.Empty); 
	public void Upload_data(String url, byte[] data) {throw Err_.new_unimplemented();}
	public Io_stream_rdr Download_as_rdr(String url) {
		download_wkr.Init(url, Io_url_.Empty);
		return download_wkr.Exec_as_rdr();
	}
}
