package gplx.xowa.apps; import gplx.*; import gplx.xowa.*;
import gplx.core.threads.*;
public class Xoa_thread_mgr {
	public Gfo_thread_pool		Page_load_mgr() {return page_load_mgr;} private Gfo_thread_pool page_load_mgr = new Gfo_thread_pool();
	public Gfo_thread_pool		File_load_mgr() {return file_load_mgr;} private Gfo_thread_pool file_load_mgr = new Gfo_thread_pool();
	public void Usr_dlg_(Gfo_usr_dlg usr_dlg) {
		page_load_mgr.Usr_dlg_(usr_dlg);
		file_load_mgr.Usr_dlg_(usr_dlg);
	}
}
