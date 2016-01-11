package gplx.xowa.langs.vnts.converts; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*; import gplx.xowa.langs.vnts.*;
public class Xol_convert_itm {
	public Xol_convert_itm(byte[] src, byte[] trg) {this.src = src; this.trg = trg;}	// convert from src to trg; EX: A -> A1
	public byte[] Src() {return src;} private final byte[] src;
	public byte[] Trg() {return trg;} private final byte[] trg;
}
