package gplx.xowa.xtns.pfuncs.exprs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.parsers.*;
interface Func_tkn extends Expr_tkn {
	boolean Func_is_const();
	int ArgCount();
	int Precedence();
	Func_tkn GetAlt();
	boolean Calc(Xop_ctx ctx, Pfunc_expr_shunter shunter, Val_stack val_stack);
}
