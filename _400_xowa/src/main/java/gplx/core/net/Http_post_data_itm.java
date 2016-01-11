package gplx.core.net; import gplx.*; import gplx.core.*;
public class Http_post_data_itm {
	public Http_post_data_itm(byte[] key, byte[] val) {this.key = key; this.val = val;}
	public byte[] Key() {return key;} private final byte[] key;
	public byte[] Val() {return val;} private final byte[] val;
}
