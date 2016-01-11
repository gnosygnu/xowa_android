package gplx.xowa.xtns.pfuncs.pages; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Pfunc_noeditsection extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_noeditsection;}
	@Override public Pf_func New(int id, byte[] name) {return new Pfunc_noeditsection().Name_(name);}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {}
	public static final Pfunc_noeditsection Instance = new Pfunc_noeditsection(); Pfunc_noeditsection() {}
}
