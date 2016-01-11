package gplx.xowa.bldrs.wkrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
import gplx.core.flds.*; import gplx.core.ios.*;
public abstract class Xob_idx_base extends Xob_itm_basic_base implements Xob_cmd, GfoInvkAble {
	public abstract String Cmd_key();
	public Gfo_fld_wtr Fld_wtr() {return fld_wtr;} Gfo_fld_wtr fld_wtr = Gfo_fld_wtr.xowa_();
	public Gfo_fld_rdr Fld_rdr() {return fld_rdr;} Gfo_fld_rdr fld_rdr = Gfo_fld_rdr.xowa_();
	public Io_url Temp_dir() {return temp_dir;} Io_url temp_dir;
	public void Cmd_init(Xob_bldr bldr) {}
	public void Cmd_bgn(Xob_bldr bldr) {
		temp_dir = wiki.Fsys_mgr().Tmp_dir().GenSubDir(this.Cmd_key());
		Io_mgr.Instance.DeleteDirDeep(temp_dir);
		Cmd_bgn_hook();
	}
	public abstract void Cmd_bgn_hook(); 
	public abstract void Cmd_run();
	@gplx.Virtual public void Cmd_end() {}
	public void Cmd_term() {}	
	public Io_line_rdr rdr_(Io_url dir) {
		Io_url[] fils = Io_mgr.Instance.QueryDir_fils(dir);
		return new Io_line_rdr(bldr.Usr_dlg(), fils).Key_gen_(Io_line_rdr_key_gen_.first_pipe);
	}
}