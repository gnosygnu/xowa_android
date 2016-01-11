package gplx.xowa.xtns.pfuncs.stringutils; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Pfunc_rpos extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_strx_rpos;}
	@Override public Pf_func New(int id, byte[] name) {return new Pfunc_rpos().Name_(name);}
	@Override public boolean Func_require_colon_arg() {return true;}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		byte[] argx = Eval_argx(ctx, src, caller, self);
		int self_args_len = self.Args_len();
		byte[] find = Pf_func_.Eval_arg_or(ctx, src, caller, self, self_args_len, 0, Byte_ascii.Space_bry);	// MW: use " " if find is missing
		byte[] offset_bry = Pf_func_.Eval_arg_or(ctx, src, caller, self, self_args_len, 1, null);
		int offset = offset_bry == null ? argx.length : Bry_.To_int_or_neg1(offset_bry);
		int pos = Bry_find_.Find_bwd(argx, find, offset); if (pos == Bry_find_.Not_found) pos = -1;
		bfr.Add_int_variable(pos);
	}
}	
