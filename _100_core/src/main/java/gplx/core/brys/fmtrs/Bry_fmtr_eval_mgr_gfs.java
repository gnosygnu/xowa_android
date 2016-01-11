package gplx.core.brys.fmtrs; import gplx.*; import gplx.core.*; import gplx.core.brys.*;
import gplx.langs.gfs.*;
public class Bry_fmtr_eval_mgr_gfs implements Bry_fmtr_eval_mgr {
	public boolean Enabled() {return enabled;} public void Enabled_(boolean v) {enabled = v;} private boolean enabled;
	public byte[] Eval(byte[] cmd) {			
		return enabled ? Bry_.new_u8(Object_.Xto_str_strict_or_null_mark(GfsCore.Instance.ExecText(String_.new_u8(cmd)))) : null;
	}
        public static final Bry_fmtr_eval_mgr_gfs Instance = new Bry_fmtr_eval_mgr_gfs(); Bry_fmtr_eval_mgr_gfs() {}
}
