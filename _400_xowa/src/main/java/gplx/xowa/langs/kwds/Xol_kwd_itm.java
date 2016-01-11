package gplx.xowa.langs.kwds; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*;
public class Xol_kwd_itm {// NOTE: keeping as separate class b/c may include fmt props later; EX: thumbnail=$1
	public Xol_kwd_itm(byte[] val) {this.val = val;}
	public byte[] Val() {return val;} private byte[] val;
	public void Val_(byte[] v) {val = v;}	// should only be called by lang
	public static final Xol_kwd_itm[] Ary_empty = new Xol_kwd_itm[0];
}
