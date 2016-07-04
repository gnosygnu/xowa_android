package gplx.xowa; import gplx.*;
import gplx.core.brys.*; import gplx.core.ios.*; import gplx.core.log_msgs.*;
import gplx.langs.htmls.encoders.*;
import gplx.xowa.guis.views.boots.*;
import gplx.xowa.apps.boots.*; import gplx.xowa.apps.gfs.*;
public class Xoa_app_ {
	public static void Run(String... args) {
		Xoa_cmd_arg_mgr arg_mgr = Xoa_cmd_arg_mgr.new_();
		try {
			Xoa_boot_mgr boot_mgr = new Xoa_boot_mgr();
			boot_mgr.Run(args, arg_mgr);
		} catch (Error e) {	//#<>(Exception e)~(Error e)
			if (arg_mgr.App_type().Tid_is_gui())
				Xog_error_win.Run(Err_.Message_lang(e), Err_.Trace_lang(e));
			else
				throw e;
		}
	}
	public static final String		Name			= "xowa";
	public static final String		Version			= "3.7.1.1";
	public static String	Build_date		= "2012-12-30 00:00:00";
	public static String	Op_sys_str;
	public static String	User_agent		= "";
	public static final    Gfo_msg_grp Nde = Gfo_msg_grp_.prj_(Name);
	public static Gfo_usr_dlg		Usr_dlg()			{return usr_dlg;}			public static void Usr_dlg_(Gfo_usr_dlg v) {usr_dlg = v;} private static Gfo_usr_dlg usr_dlg;
	public static Bry_bfr_mkr		Utl__bfr_mkr()		{return utl__bry_bfr_mkr;}	private static final    Bry_bfr_mkr utl__bry_bfr_mkr = new Bry_bfr_mkr();
	public static Io_stream_zip_mgr Utl__zip_mgr()		{return utl__zip_mgr;}		private static final    Io_stream_zip_mgr utl__zip_mgr = new Io_stream_zip_mgr();
	public static Xoa_gfs_mgr		Gfs_mgr()			{return gfs_mgr;}			public static void Gfs_mgr_(Xoa_gfs_mgr v) {gfs_mgr = v;} private static Xoa_gfs_mgr gfs_mgr;
	public static void Plog_none(String mod, String fmt) {
		String msg = mod + ": " + fmt;
		Xoa_app_.Usr_dlg().Plog_many("", "", msg);
	}
	public static Gfo_usr_dlg usr_dlg_console_() {
		Gfo_usr_dlg rv = new Gfo_usr_dlg_base(new Gfo_usr_dlg__log_base(), Gfo_usr_dlg__gui_.Console);
		rv.Log_wkr().Queue_enabled_(true);
		return rv;
	}
}
