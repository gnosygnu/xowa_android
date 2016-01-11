package gplx.core.brys.args; import gplx.*; import gplx.core.*; import gplx.core.brys.*;
import gplx.core.brys.fmtrs.*;
public class Bfr_arg__bry_fmtr implements Bfr_arg {
	private Bry_fmtr fmtr; private Object[] arg_ary;
	public Bfr_arg__bry_fmtr(Bry_fmtr fmtr, Object[] arg_ary) {Set(fmtr, arg_ary);}
	public Bfr_arg__bry_fmtr Set(Bry_fmtr fmtr, Object... arg_ary) {
		this.fmtr = fmtr; this.arg_ary = arg_ary;
		return this;
	}
	public void Bfr_arg__add(Bry_bfr bfr) {fmtr.Bld_bfr_many(bfr, arg_ary);}
}
