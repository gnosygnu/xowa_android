package gplx.xowa.files.downloads; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
import gplx.core.ios.*;
public class Xof_download_wkr_io implements Xof_download_wkr {
	IoEngine_xrg_downloadFil xrg = Io_mgr.Instance.DownloadFil_args("", Io_url_.Empty);
	public IoEngine_xrg_downloadFil Download_xrg() {return xrg;}
	public String Download_err() {return download_err;} private String download_err = "";
	public byte Download(boolean src_is_web, String src_str, Io_url trg_url, String prog_fmt_hdr) {
		download_err = "";
		if (src_is_web) {
			xrg.Prog_fmt_hdr_(prog_fmt_hdr);
			xrg.Init(src_str, trg_url);
			xrg.Exec();
			return xrg.Rslt();
		}
		else {
			Io_url src_url = Io_url_.new_fil_(src_str);
			if (!Io_mgr.Instance.ExistsFil(src_url)) return IoEngine_xrg_downloadFil.Rslt_fail_file_not_found;
			try {Io_mgr.Instance.CopyFil(src_url, trg_url, true);}
			catch (Exception exc) {Err_.Noop(exc); return IoEngine_xrg_downloadFil.Rslt_fail_unknown;}
			return IoEngine_xrg_downloadFil.Rslt_pass;
		}
	}
}
