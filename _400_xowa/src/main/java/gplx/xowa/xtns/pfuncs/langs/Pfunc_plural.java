package gplx.xowa.xtns.pfuncs.langs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Pfunc_plural extends Pf_func_base {
	@Override public boolean Func_require_colon_arg() {return true;}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {// REF.MW: CoreParserFunctions.php
		byte[] number = Eval_argx(ctx, src, caller, self);
		int self_args_len = self.Args_len();
		int arg_idx = Pf_func_.Eq(ctx, number, Ary_Num_1) ? 0 : 1;
		if (arg_idx == 1 && self_args_len == 1) arg_idx = 0;	// number is plural, but plural_arg not present; use singular; see test
		byte[] word = Pf_func_.Eval_arg_or_empty(ctx, src, caller, self, self_args_len, arg_idx);
		bfr.Add(word);
	}	private static final    byte[] Ary_Num_1 = new byte[] {Byte_ascii.Num_1};
	@Override public int Id() {return Xol_kwd_grp_.Id_i18n_plural;}
	@Override public Pf_func New(int id, byte[] name) {return new Pfunc_plural().Name_(name);}
}	
