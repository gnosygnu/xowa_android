package gplx.xowa.xtns.pfuncs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public interface Pf_func extends Xot_defn {
	int Id();
	void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src);
	boolean Func_require_colon_arg();
	Pf_func New(int id, byte[] name);
}
