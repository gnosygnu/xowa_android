package gplx.xowa.xtns.pfuncs.exprs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.parsers.*;
class Func_tkn_e_op extends Func_tkn_base {
	public Func_tkn_e_op(String v) {this.Ctor(v);}
	@Override public int ArgCount()		{return 2;}
	@Override public int Precedence()	{return 9;}	// NOTE: needs to be < than - sign
	@Override public Func_tkn GetAlt() {return Func_tkn_e_const.Instance;}
	@Override public boolean Calc_hook(Xop_ctx ctx, Pfunc_expr_shunter shunter, Val_stack val_stack) {
		Decimal_adp rhs = val_stack.Pop();
		Decimal_adp lhs = val_stack.Pop();
		int rhs_int = rhs.To_int();
		if (	rhs_int > 308
			||	(lhs.To_double() >= 1.8f && rhs_int == 308)) {	// PHP:"maximum of ~1.8e308"; verified with {{#expr:1.8e308}} on sandbox; REF:http://php.net/manual/en/language.types.float.php; PAGE:en.w:Factorial; en.w:Astatine; DATE:2015-04-08; DATE:2015-04-21
			shunter.Rslt_set(Double_.Inf_pos_bry);
			return false;
		}
		double rhs_double = rhs.To_double();
		if ((double)rhs_int == rhs_double)	// exponent is integer; use pow_10 which does less casts to double
			val_stack.Push(lhs.Multiply(Decimal_adp_.pow_10_(rhs_int)));
		else
			val_stack.Push(lhs.Multiply(Decimal_adp_.double_thru_str_(Math_.Pow(10d, rhs_double))));
		return true;
	}
}
