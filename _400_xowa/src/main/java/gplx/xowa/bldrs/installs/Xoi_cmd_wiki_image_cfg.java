package gplx.xowa.bldrs.installs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
import gplx.core.threads.*;
class Xoi_cmd_wiki_image_cfg extends Gfo_thread_cmd_replace implements Gfo_thread_cmd {//#*inherit
	public Xoi_cmd_wiki_image_cfg(Xoae_app app, Io_url url) {this.app = app; super.Init(app.Usr_dlg(), app.Gui_mgr().Kit(), url);} private Xoae_app app;
	@Override public void Async_run() {
		super.Async_run();
		app.Cfg_mgr().Set_by_app("app.files.download.enabled", "y");
		app.Cfg_mgr().Db_save_txt();
	}
	static final String GRP_KEY = "xowa.thread.dump.image_cfg";
	public static final String KEY_dump = "wiki.image_cfg";
}
