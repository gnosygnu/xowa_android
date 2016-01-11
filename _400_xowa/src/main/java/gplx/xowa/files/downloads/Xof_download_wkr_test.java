package gplx.xowa.files.downloads; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
import gplx.core.ios.*;
public class Xof_download_wkr_test implements Xof_download_wkr {
	public IoEngine_xrg_downloadFil Download_xrg() {return IoEngine_xrg_downloadFil.new_("", Io_url_.Empty).Trg_engine_key_(IoEngine_.MemKey);}
	public byte Download(boolean src_is_web, String src_str, Io_url trg_url, String prog_fmt_hdr) {
		Io_mgr.Instance.CreateDirIfAbsent(trg_url.OwnerDir());
		Io_url src_url = Io_url_.new_fil_(src_str);
		if (!Io_mgr.Instance.ExistsFil(src_url)) return IoEngine_xrg_downloadFil.Rslt_fail_file_not_found;
		try {Io_mgr.Instance.CopyFil(src_url, trg_url, true);}
		catch (Exception exc) {Err_.Noop(exc); return IoEngine_xrg_downloadFil.Rslt_fail_unknown;}
		return IoEngine_xrg_downloadFil.Rslt_pass;
	}
}
