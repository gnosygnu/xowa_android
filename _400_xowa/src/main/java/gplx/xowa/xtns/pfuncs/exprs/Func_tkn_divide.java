package gplx.xowa.xtns.pfuncs.exprs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.parsers.*;
import gplx.xowa.langs.msgs.*;
class Func_tkn_divide extends Func_tkn_base {
	public Func_tkn_divide(String v) {this.Ctor(v);}
	@Override public int ArgCount()		{return 2;}
	@Override public int Precedence()	{return 7;}
	@Override public boolean Calc_hook(Xop_ctx ctx, Pfunc_expr_shunter shunter, Val_stack val_stack) {
		Decimal_adp rhs = val_stack.Pop();
		Decimal_adp lhs = val_stack.Pop();
		if (rhs.Eq(0)) {
			shunter.Err_set(ctx, Xol_msg_itm_.Id_pfunc_expr_division_by_zero);
			return false;
		}
		val_stack.Push(lhs.Divide(rhs));
		return true;
	}
}
