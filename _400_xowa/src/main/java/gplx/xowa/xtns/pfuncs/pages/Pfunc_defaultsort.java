package gplx.xowa.xtns.pfuncs.pages; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Pfunc_defaultsort extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_page_defaultsort;}
	@Override public Pf_func New(int id, byte[] name) {return new Pfunc_defaultsort().Name_(name);}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {}
	public static final Pfunc_defaultsort Instance = new Pfunc_defaultsort(); Pfunc_defaultsort() {}
}	
