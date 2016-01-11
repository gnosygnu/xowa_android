package gplx.xowa.langs.numbers; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*;
public class Xol_num_grp {
	public Xol_num_grp(byte[] dlm, int digits, boolean repeat) {this.dlm = dlm; this.digits = digits; this.repeat = repeat;}
	public byte[] Dlm() {return dlm;} private byte[] dlm;
	public int Digits() {return digits;} private int digits;
	public boolean Repeat() {return repeat;} private boolean repeat;
	public static final Xol_num_grp[] Ary_empty = new Xol_num_grp[0];
	public static final Xol_num_grp Default = new Xol_num_grp(new byte[] {Byte_ascii.Comma}, 3, true);
}
