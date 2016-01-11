package gplx.core.brys.fmtrs; import gplx.*; import gplx.core.*; import gplx.core.brys.*;
public class Bry_fmtr_eval_mgr_ {
	public static Io_url Eval_url(Bry_fmtr_eval_mgr eval_mgr, byte[] fmt) {
		if (eval_mgr == null) return Io_url_.new_any_(String_.new_u8(fmt));
		Bry_bfr bfr = Bry_bfr.reset_(255);
		Bry_fmtr fmtr = Bry_fmtr.tmp_();
		fmtr.Eval_mgr_(eval_mgr).Fmt_(fmt).Bld_bfr_none(bfr);
		return Io_url_.new_any_(bfr.To_str_and_clear());
	}
}
