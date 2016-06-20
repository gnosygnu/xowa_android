package gplx.xowa.users; import gplx.*; import gplx.xowa.*;
import gplx.xowa.apps.gfs.*; import gplx.xowa.wikis.*;
public class Xou_fsys_mgr implements Gfo_invk {
	private Io_url app_root_dir; private Io_url wiki_root_dir; private Io_url cur_root;
	public Xou_fsys_mgr(Xoae_app app, Xoue_user usr, Io_url user_dir) {
		this.cur_root = user_dir;
		app_root_dir = cur_root.GenSubDir("app");
		app_temp_dir = app_root_dir.GenSubDir("tmp");
		app_temp_html_dir = app_temp_dir.GenSubDir("html");
		app_data_history_fil = app_root_dir.GenSubFil_nest("data", "history", "page_history.csv");
		wiki_root_dir = cur_root.GenSubDir("wiki");
	}
	public Io_url Root_dir()					{return cur_root;}
	public Io_url Wiki_root_dir()				{return wiki_root_dir;}
	public Io_url Wiki_html_dir(String wiki)	{return wiki_root_dir.GenSubDir_nest(wiki, "html");}
	public Io_url App_data_history_fil()		{return app_data_history_fil;} private Io_url app_data_history_fil;
	public Io_url App_temp_dir()				{return app_temp_dir;} private Io_url app_temp_dir;
	public Io_url App_temp_html_dir()			{return app_temp_html_dir;} private Io_url app_temp_html_dir;
	public Io_url App_data_dir()				{return app_root_dir.GenSubDir_nest("data");}
	public Io_url App_data_cfg_dir()			{return app_root_dir.GenSubDir_nest("data", "cfg");}
	public Io_url App_data_cfg_user_fil()		{return app_root_dir.GenSubFil_nest("data", "cfg", Xoa_gfs_mgr.Cfg_user_file);}
	public Io_url App_data_cfg_custom_fil()		{return app_root_dir.GenSubFil_nest("data", "cfg", Xoa_gfs_mgr.Cfg_user_custom_file);}
	public static final String Name_user_system_cfg = "user_system_cfg.gfs";
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_root_dir))		return cur_root;
		else	return Gfo_invk_.Rv_unhandled;
	}	private static final String Invk_root_dir = "root_dir";
}
