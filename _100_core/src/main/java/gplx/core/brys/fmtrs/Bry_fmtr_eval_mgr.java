package gplx.core.brys.fmtrs; import gplx.*; import gplx.core.*; import gplx.core.brys.*;
public interface Bry_fmtr_eval_mgr {
	boolean Enabled(); void Enabled_(boolean v);
	byte[] Eval(byte[] cmd);
}
