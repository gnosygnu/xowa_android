package gplx.xowa.xtns.pfuncs.exprs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.parsers.*;
class Paren_end_tkn implements Expr_tkn {
	public int Tid() {return Expr_tkn_.Tid_paren_rhs;}
	public byte[] Val_ary() {return val_ary;} private byte[] val_ary = Bry_.new_u8(val_str);
	public String Val_str() {return val_str;} static final    String val_str = ")";
	public static Paren_end_tkn Instance = new Paren_end_tkn(); Paren_end_tkn() {}
}
