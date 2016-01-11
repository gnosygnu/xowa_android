package gplx.xowa.apps.cfgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*;
public interface Xoa_cfg_db {
	void Cfg_reset_all(Xoa_cfg_mgr cfg_mgr);
	void Cfg_save_bgn(Xoa_cfg_mgr cfg_mgr);
	void Cfg_save_end(Xoa_cfg_mgr cfg_mgr);
	void Cfg_save_run(Xoa_cfg_mgr cfg_mgr, Xoa_cfg_grp cfg_grp, Xoa_cfg_itm cfg_itm);
	void Cfg_load_run(Xoa_cfg_mgr cfg_mgr);
}
