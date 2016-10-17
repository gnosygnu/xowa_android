package gplx.core.net.qargs; import gplx.*; import gplx.core.*; import gplx.core.net.*;
public class Gfo_qarg_itm {
	public Gfo_qarg_itm(byte[] key_bry, byte[] val_bry) {
		this.key_bry = key_bry;
		this.val_bry = val_bry;
	}
	public byte[]			Key_bry() {return key_bry;} private final    byte[] key_bry;
	public byte[]			Val_bry() {return val_bry;} private byte[] val_bry;
	public void				Val_bry_(byte[] v) {val_bry = v;}

	public static final    Gfo_qarg_itm[] Ary_empty = new Gfo_qarg_itm[0];
}
