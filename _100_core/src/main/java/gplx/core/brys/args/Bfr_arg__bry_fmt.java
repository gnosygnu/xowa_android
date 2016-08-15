package gplx.core.brys.args; import gplx.*; import gplx.core.*; import gplx.core.brys.*;
import gplx.core.brys.*;
public class Bfr_arg__bry_fmt implements Bfr_arg {
	private final    Bry_fmt fmt;
	private Object[] arg_ary;
	public Bfr_arg__bry_fmt(Bry_fmt fmt, Object... arg_ary) {
		this.fmt = fmt;
		Args_(arg_ary);
	}
	public void Bfr_arg__clear() {arg_ary = null;}
	public boolean Bfr_arg__missing() {return arg_ary == null;}

	public Bfr_arg__bry_fmt Args_(Object... arg_ary) {
		this.arg_ary = arg_ary;
		return this;
	}
	public void Bfr_arg__add(Bry_bfr bfr) {
		if (Bfr_arg__missing()) return;
		fmt.Bld_many(bfr, arg_ary);
	}
}
