package gplx.xowa.xtns.pfuncs.exprs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.parsers.*;
import gplx.xowa.langs.msgs.*;
class Func_tkn_ln extends Func_tkn_base {
	public Func_tkn_ln(String v) {this.Ctor(v);}
	@Override public int ArgCount()		{return 1;}
	@Override public int Precedence()	{return 9;}
	@Override public boolean Calc_hook(Xop_ctx ctx, Pfunc_expr_shunter shunter, Val_stack val_stack) {
		Decimal_adp val = val_stack.Pop();
		if (val.Comp_lte(0)) {shunter.Err_set(ctx, Xol_msg_itm_.Id_pfunc_expr_invalid_argument_ln); return false;}		
		val_stack.Push(Decimal_adp_.double_(Math_.Log(val.To_double())));
		return true;
	}
}
