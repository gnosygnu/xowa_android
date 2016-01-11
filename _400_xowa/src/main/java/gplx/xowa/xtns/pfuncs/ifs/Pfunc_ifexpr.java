package gplx.xowa.xtns.pfuncs.ifs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.xtns.pfuncs.exprs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Pfunc_ifexpr extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_xtn_ifexpr;}
	@Override public Pf_func New(int id, byte[] name) {return new Pfunc_ifexpr().Name_(name);}
	@Override public boolean Func_require_colon_arg() {return true;}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {			
		int self_args_len = self.Args_len();
		byte[] argx = Eval_argx(ctx, src, caller, self); if (argx == null) return;
		Decimal_adp result = shunter.Evaluate(ctx, argx);
		boolean is_nan = result == Pfunc_expr_shunter.Null_rslt;
		if (is_nan && shunter.Err().Len() > 0) {
			bfr.Add_bfr_and_preserve(shunter.Err());
			shunter.Err().Clear();
		}
		else {
			if (is_nan || result.To_int() == 0)
				bfr.Add(Pf_func_.Eval_arg_or_empty(ctx, src, caller, self, self_args_len, 1));
			else
				bfr.Add(Pf_func_.Eval_arg_or_empty(ctx, src, caller, self, self_args_len, 0));
		}
	}
	private final Pfunc_expr_shunter shunter = Pfunc_expr_shunter.Instance;
}	
