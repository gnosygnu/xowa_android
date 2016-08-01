package gplx.xowa.apps.cfgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*;
import gplx.core.brys.fmtrs.*;
import gplx.xowa.apps.gfs.*;
public class Xoa_cfg_db_txt implements Xoa_cfg_db {
	private Bry_fmtr fmtr = Bry_fmtr.new_("app.cfgs.get('~{msg}', '~{wiki}').val = '~{val}';\n", "msg", "wiki", "val");
	public void Cfg_reset_all(Xoa_cfg_mgr cfg_mgr) {
		Io_url src_url = this.Cfg_url(cfg_mgr);
		Io_url trg_url = src_url.GenNewNameAndExt("xowa_user_cfg." + Datetime_now.Get().XtoStr_fmt_yyyyMMdd_HHmmss() + ".gfs");
		Io_mgr.Instance.MoveFil_args(src_url, trg_url, true).Exec();
		if (cfg_mgr.App().Tid_is_edit())
			((Xoae_app)cfg_mgr.App()).Gui_mgr().Kit().Ask_ok("", "", "Options cleared. Please restart XOWA.");
	}
	public void Cfg_load_run(Xoa_cfg_mgr cfg_mgr) {
		String load = Io_mgr.Instance.LoadFilStr(Cfg_url(cfg_mgr));
		cfg_mgr.App().Gfs_mgr().Run_str(load);
	}
	public void Cfg_save_bgn(Xoa_cfg_mgr cfg_mgr) {
		bfr.ClearAndReset();
	} 	private Bry_bfr bfr = Bry_bfr_.New();
	public void Cfg_save_end(Xoa_cfg_mgr cfg_mgr) {
		Xoa_app_.Usr_dlg().Log_many("", "", "shutting down app; saving cfg: len=~{0}", bfr.Len());
		Io_mgr.Instance.SaveFilBfr(Cfg_url(cfg_mgr), bfr);
	}
	public void Cfg_save_run(Xoa_cfg_mgr cfg_mgr, Xoa_cfg_grp cfg_grp, Xoa_cfg_itm cfg_itm) {
		fmtr.Bld_bfr_many(bfr, Xoa_gfs_wtr_.Escape(cfg_grp.Key_bry()), Xoa_gfs_wtr_.Escape(cfg_itm.Key()), Xoa_gfs_wtr_.Escape(cfg_itm.Val()));
	}
	public Io_url Cfg_url(Xoa_cfg_mgr cfg_mgr) {return ((Xoae_app)cfg_mgr.App()).Usere().Fsys_mgr().App_data_cfg_dir().GenSubFil(File_name);}	
	public static final String File_name = "xowa_user_cfg.gfs";
}
