package gplx.xowa.apps; import gplx.*; import gplx.xowa.*;
import gplx.core.brys.fmtrs.*;
import gplx.langs.gfs.*;
public class Xoa_app_eval implements Bry_fmtr_eval_mgr {
	public boolean Enabled() {return enabled;} public void Enabled_(boolean v) {enabled = v;} private boolean enabled = true;
	public byte[] Eval(byte[] cmd) {
		Object rslt = GfsCore.Instance.Exec_bry(cmd);
		return Bry_.new_u8(Object_.Xto_str_strict_or_null_mark(rslt));
	}
}
