package gplx.xowa.files.bins; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
import gplx.core.ios.*; import gplx.core.ios.streams.*;
public interface Io_download_mgr {
	Io_stream_rdr	Download_as_rdr(String src);
}
