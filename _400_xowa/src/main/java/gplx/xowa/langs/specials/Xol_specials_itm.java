package gplx.xowa.langs.specials; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*;
public class Xol_specials_itm {
	public Xol_specials_itm(byte[] special, byte[][] aliases) {this.special = special; this.aliases = aliases;}
	public byte[] Special() {return special;} private byte[] special;
	public byte[][] Aliases() {return aliases;} private byte[][] aliases;
}
