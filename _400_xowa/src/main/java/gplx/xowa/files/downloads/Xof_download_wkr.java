package gplx.xowa.files.downloads; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
import gplx.core.ios.*;
public interface Xof_download_wkr {
	byte Download(boolean src_is_web, String src, Io_url trg, String prog_fmt_hdr);
	IoEngine_xrg_downloadFil Download_xrg();
}
