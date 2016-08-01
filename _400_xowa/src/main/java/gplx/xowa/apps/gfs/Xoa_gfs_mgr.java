package gplx.xowa.apps.gfs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*;
import gplx.langs.gfs.*;
import gplx.xowa.users.*; import gplx.xowa.apps.fsys.*;
public class Xoa_gfs_mgr implements Gfo_invk, Gfo_invk_root_wkr {
	private final    Xou_fsys_mgr usr_fsys_mgr;
	public Xoa_gfs_mgr(Gfo_invk root_invk, Xoa_fsys_mgr app_fsys_mgr, Xou_fsys_mgr usr_fsys_mgr) {
		this.root_invk = root_invk; this.app_fsys_mgr = app_fsys_mgr; this.usr_fsys_mgr = usr_fsys_mgr;
		GfsCore.Instance.AddCmd(root_invk, Xoae_app.Invk_app);
		GfsCore.Instance.AddCmd(root_invk, Xoae_app.Invk_xowa);
	}
	public Gfo_invk Root_invk() {return root_invk;} private final    Gfo_invk root_invk; 
	public Xoa_fsys_mgr App_fsys_mgr() {return app_fsys_mgr;} private final    Xoa_fsys_mgr app_fsys_mgr; 
	public Xoa_app_eval Eval_mgr() {return eval_mgr;} private final    Xoa_app_eval eval_mgr = new Xoa_app_eval();
	public Gfs_wtr Wtr() {return wtr;} private final    Gfs_wtr wtr = new Gfs_wtr();
	public void Run_url(Io_url url) {
		Run_url_for(GfsCore.Instance.Root(), url);
		Gfo_usr_dlg_.Instance.Log_wkr().Log_to_session_fmt("gfs.done: ~{0}", url.Raw());
	}
	public void Run_url_for(Gfo_invk invk, Io_url url) {
		String raw = Io_mgr.Instance.LoadFilStr_args(url).MissingIgnored_().Exec(); if (String_.Len_eq_0(raw)) return;
		Run_str_for(invk, raw);
	}
	public Object Run_str(String raw) {return Run_str_for(GfsCore.Instance.Root(), raw);}
	public Object Run_str_for(Gfo_invk invk, String raw) {return Run_str_for(invk, Xoa_gfs_mgr_.Parse_to_msg(raw));}
	public Object Run_str_for(Gfo_invk invk, GfoMsg root_msg) {
		try {
			Object rv = null;
			GfsCtx ctx = GfsCtx.new_().Fail_if_unhandled_(Fail_if_unhandled).Usr_dlg_(Gfo_usr_dlg_.Instance);
			int len = root_msg.Subs_count();
			for (int i = 0; i < len; ++i)
				rv = GfsCore.Instance.ExecOne_to(ctx, invk, root_msg.Subs_getAt(i));
			return rv;	// return rv from last call
		} catch (Exception e) {
			Gfo_usr_dlg_.Instance.Warn_many("", "", "error while executing script: err=~{0}", Err_.Message_gplx_full(e));
			return Gfo_invk_.Rv_error;
		}
	}
	private void Run_url_by_type(String type) {
		Io_url app_data_dir = usr_fsys_mgr.App_data_dir();
		Io_url url = null;
		if		(String_.Eq(type, "user_system_cfg"))	url = app_data_dir.GenSubFil_nest("cfg", "user_system_cfg.gfs");
		else if	(String_.Eq(type, "xowa_cfg_custom"))	url = usr_fsys_mgr.App_data_cfg_custom_fil();
		else if	(String_.Eq(type, "xowa_cfg_user"))		url = usr_fsys_mgr.App_data_cfg_user_fil();
		else if	(String_.Eq(type, "xowa_cfg_os"))		{url = app_fsys_mgr.Bin_data_os_cfg_fil(); Xoa_gfs_mgr_.Cfg_os_assert(url);}
		else if	(String_.Eq(type, "xowa_cfg_app"))		url = app_fsys_mgr.Cfg_app_fil();
		else											throw Err_.new_wo_type("invalid gfs type", "type", type);
		try {Run_url(url);}
		catch (Exception e) {				// gfs is corrupt; may happen if multiple XOWAs opened, and "Close all" chosen in OS; DATE:2014-07-01
			if	(!String_.Eq(type, "xowa"))			// check if user.gfs
				Io_mgr.Instance.MoveFil(url, url.GenNewNameOnly(url.NameOnly() + "-" + Datetime_now.Get().XtoStr_fmt_yyyyMMdd_HHmmss()));	// move file
			Gfo_usr_dlg_.Instance.Warn_many("", "", "invalid gfs; obsoleting: src=~{0} err=~{1}", url.Raw(), Err_.Message_gplx_full(e));
		}
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_run_file_by_type))		Run_url_by_type(m.ReadStr("v"));
		else if	(ctx.Match(k, Invk_fail_if_unhandled_))		{Fail_if_unhandled = m.ReadYn("v"); ctx.Fail_if_unhandled_(Fail_if_unhandled);}
		else if	(ctx.Match(k, Invk_txns))					{return Gfo_invk_.Noop;}	// FUTURE: handle version for upgrades
		else return Gfo_invk_.Rv_unhandled;
		return this;
	}	private static final String Invk_run_file_by_type = "run_file_by_type", Invk_fail_if_unhandled_ = "fail_if_unhandled_", Invk_txns = "txns";
	public static void Msg_parser_init() {GfsCore.Instance.MsgParser_(gplx.langs.gfs.Gfs_msg_bldr.Instance);}
	public static final String Cfg_user_file = "xowa_user_cfg.gfs", Cfg_user_custom_file = "user_custom_cfg.gfs", Cfg_os = "xowa_cfg_os.gfs";
	public static boolean Fail_if_unhandled = false;
}
