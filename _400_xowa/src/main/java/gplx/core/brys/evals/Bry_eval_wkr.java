package gplx.core.brys.evals; import gplx.*; import gplx.core.*; import gplx.core.brys.*;
public interface Bry_eval_wkr {
	String Key();
	void Resolve(Bry_bfr rv, byte[] src, int args_bgn, int args_end);
}
