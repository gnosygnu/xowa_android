package gplx.xowa.xtns.pfuncs.exprs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.parsers.*;
class Ws_tkn implements Expr_tkn {
	public int Tid() {return Expr_tkn_.Tid_space;}
	public byte[] Val_ary() {return val_ary;} private byte[] val_ary;
	public String Val_str() {return val_str;} private String val_str;
	public Ws_tkn(byte b) {this.val_ary = new byte[] {b}; this.val_str = Char_.To_str(Char_.By_int(b));}
}
