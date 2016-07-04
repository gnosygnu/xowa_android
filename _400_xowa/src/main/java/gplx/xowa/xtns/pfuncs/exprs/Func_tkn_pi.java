package gplx.xowa.xtns.pfuncs.exprs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.parsers.*;
class Func_tkn_pi extends Func_tkn_base {
	public Func_tkn_pi(String v) {this.Ctor(v);}
	@Override public int ArgCount()		{return 0;}
	@Override public int Precedence()	{return 0;}
	@Override public boolean Func_is_const() {return true;}
	@Override public boolean Calc_hook(Xop_ctx ctx, Pfunc_expr_shunter shunter, Val_stack val_stack) {
		val_stack.Push(Decimal_adp_.Const_pi);
		return true;
	}
}
