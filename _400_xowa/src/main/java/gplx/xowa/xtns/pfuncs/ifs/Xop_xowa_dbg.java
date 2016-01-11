package gplx.xowa.xtns.pfuncs.ifs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Xop_xowa_dbg extends Pf_func_base {
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {			
		byte[] argx = Eval_argx(ctx, src, caller, self);
		bfr.Add(argx);
		Argx_list.Add(argx);
	}
	public static final List_adp Argx_list = List_adp_.new_();
	@Override public int Id() {return Xol_kwd_grp_.Id_xowa_dbg;}
	@Override public Pf_func New(int id, byte[] name) {return new Xop_xowa_dbg().Name_(name);}
}
