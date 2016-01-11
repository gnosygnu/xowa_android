package gplx.core.brys.fmtrs; import gplx.*; import gplx.core.*; import gplx.core.brys.*;
import gplx.core.brys.*;
public class Bry_fmtr_vals implements Bfr_arg {
	private final Bry_fmtr fmtr; private Object[] vals;
	Bry_fmtr_vals(Bry_fmtr fmtr) {this.fmtr = fmtr;}
	public Bry_fmtr_vals Vals_(Object... v) {this.vals = v; return this;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		fmtr.Bld_bfr_ary(bfr, vals);
	}
	public static Bry_fmtr_vals new_fmt(String fmt, String... keys) {
		Bry_fmtr fmtr = Bry_fmtr.new_(fmt, keys);
		return new Bry_fmtr_vals(fmtr);
	}
	public static Bry_fmtr_vals new_(Bry_fmtr fmtr) {return new Bry_fmtr_vals(fmtr);}
}
