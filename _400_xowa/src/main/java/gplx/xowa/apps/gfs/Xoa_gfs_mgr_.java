package gplx.xowa.apps.gfs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*;
import gplx.langs.gfs.*;
public class Xoa_gfs_mgr_ {
	public static GfoMsg Parse_to_msg(String v) {return Gfs_msg_bldr.Instance.ParseToMsg(v);}
	public static void Cfg_os_assert(Io_url orig_url) {
		Io_url dflt_url = orig_url.GenNewNameOnly(orig_url.NameOnly() + "_default");
		if (!Io_mgr.Instance.ExistsFil(dflt_url)) return;	// no dflt
		if (!Io_mgr.Instance.ExistsFil(orig_url)) {
			Io_mgr.Instance.CopyFil(dflt_url, orig_url, true);
			Gfo_usr_dlg_.Instance.Log_many("", "", "xowa_cfg_os generated; url=~{0}", orig_url.Raw());
		}
	}
}
