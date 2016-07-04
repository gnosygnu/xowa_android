package gplx.xowa.xtns.pfuncs.exprs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.parsers.*;
class Dot_tkn implements Expr_tkn {
	public int Tid() {return Expr_tkn_.Tid_number;}		
	public byte[] Val_ary()	{return Val_Ary;} static final    byte[] Val_Ary = new byte[] {Byte_ascii.Dot};
	public String Val_str()	{return String_.new_u8(Val_Ary);}
	public Dot_tkn() {}
}
