package gplx.xowa.xtns.pfuncs.stringutils; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Pfunc_len extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_strx_len;}
	@Override public Pf_func New(int id, byte[] name) {return new Pfunc_len().Name_(name);}
	@Override public boolean Func_require_colon_arg() {return true;}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		byte[] argx = Eval_argx(ctx, src, caller, self);
		int char_count = gplx.core.intls.Utf8_.Len_of_bry(argx);
		bfr.Add_int_variable(char_count);
	}
}	
