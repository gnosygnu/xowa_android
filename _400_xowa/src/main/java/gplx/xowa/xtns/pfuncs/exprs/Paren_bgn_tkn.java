package gplx.xowa.xtns.pfuncs.exprs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.parsers.*;
class Paren_bgn_tkn implements Expr_tkn, Func_tkn {
	public int Tid() {return Expr_tkn_.Tid_paren_lhs;}
	public boolean Func_is_const() {return false;}
	public byte[] Val_ary() {return val_ary;} private byte[] val_ary = Bry_.new_u8(val_str);
	public String Val_str() {return val_str;} static final    String val_str = "(";
	public int ArgCount() {return 0;}
	public int Precedence() {return -1;}
	public Func_tkn GetAlt() {return this;}
	public boolean Calc(Xop_ctx ctx, Pfunc_expr_shunter shunter, Val_stack val_stack) {throw Err_.new_unimplemented();}
	public static Paren_bgn_tkn Instance = new Paren_bgn_tkn(); Paren_bgn_tkn() {}
}
