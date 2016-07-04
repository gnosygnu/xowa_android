package gplx.xowa.xtns.pfuncs.exprs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.parsers.*;
import gplx.xowa.langs.msgs.*;
abstract class Func_tkn_base implements Func_tkn {
	public int Tid() {return Expr_tkn_.Tid_operator;}
	public abstract int Precedence();
	public abstract int ArgCount();
	@gplx.Virtual public boolean Func_is_const() {return false;}
	public void Ctor(String v) {val_ary = Bry_.new_u8(v);}
	public byte[] Val_ary()	{return val_ary;} private byte[] val_ary;
	public String Val_str()	{return String_.new_u8(Val_ary());}
	@gplx.Virtual public Func_tkn GetAlt() {return this;}
	public boolean Calc(Xop_ctx ctx, Pfunc_expr_shunter shunter, Val_stack val_stack) {
		if (val_stack.Len() < this.ArgCount()) {shunter.Err_set(ctx, Xol_msg_itm_.Id_pfunc_expr_missing_operand, Val_ary()); return false;}
		return Calc_hook(ctx, shunter, val_stack);
	}
	public abstract boolean Calc_hook(Xop_ctx ctx, Pfunc_expr_shunter shunter, Val_stack val_stack);
}
