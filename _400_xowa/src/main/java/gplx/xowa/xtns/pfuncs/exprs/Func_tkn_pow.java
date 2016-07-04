package gplx.xowa.xtns.pfuncs.exprs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.parsers.*;
class Func_tkn_pow extends Func_tkn_base {
	public Func_tkn_pow(String v) {this.Ctor(v);}
	@Override public int ArgCount()		{return 2;}
	@Override public int Precedence()	{return 8;}
	@Override public boolean Calc_hook(Xop_ctx ctx, Pfunc_expr_shunter shunter, Val_stack val_stack) {
		Decimal_adp rhs = val_stack.Pop();
		Decimal_adp lhs = val_stack.Pop();
		int rhs_int = rhs.To_int();	
		if ((double)rhs_int == rhs.To_double())	// exponent is integer; use decimal pow which does less casts to double
			val_stack.Push(lhs.Pow(rhs_int));
		else {
			double rslt = Math_.Pow(lhs.To_double(), rhs.To_double());
			if (Double_.IsNaN(rslt)) {
				shunter.Rslt_set(Double_.NaN_bry);
				return false;
			}
			else
				val_stack.Push(Decimal_adp_.double_thru_str_(rslt));
		}
		return true;
	}
}
