package gplx.xowa.xtns.pfuncs.exprs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.parsers.*;
class Num_tkn implements Expr_tkn {
	public int Tid() {return Expr_tkn_.Tid_number;}		
	public byte[] Val_ary()	{return val_ary;} private byte[] val_ary;
	public String Val_str()	{return String_.new_u8(val_ary);}
	public Num_tkn(int val_int) {
		this.val_int = val_int;
		this.val_ary = new byte[] {Byte_.By_int(val_int + Byte_ascii.Num_0)};
	}	int val_int;
}
