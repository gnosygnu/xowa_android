package gplx.core.net.downloads; import gplx.*; import gplx.core.*; import gplx.core.net.*;
public interface Http_download_wkr {
	String Fail_msg();
	Http_download_wkr Make_new();
	long Checkpoint__load_by_trg_fil(Io_url trg_url);
	byte Exec(gplx.core.progs.Gfo_prog_ui prog_ui, String src_str, Io_url trg_url, long expd_size);
	void Exec_cleanup();
}
