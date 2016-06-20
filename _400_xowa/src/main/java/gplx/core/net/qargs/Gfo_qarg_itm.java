package gplx.core.net.qargs; import gplx.*; import gplx.core.*; import gplx.core.net.*;
public class Gfo_qarg_itm {
	public Gfo_qarg_itm(byte[] key_bry, byte[] val_bry) {this.key_bry = key_bry; this.val_bry = val_bry;}
	public byte[] Key_bry() {return key_bry;} private byte[] key_bry;
	public byte[] Val_bry() {return val_bry;} private byte[] val_bry;
	public Gfo_qarg_itm Val_bry_(byte[] v) {val_bry = v; return this;}
	public static final    Gfo_qarg_itm[] Ary_empty = new Gfo_qarg_itm[0];
	public static Gfo_qarg_itm new_key_(String key) {return new Gfo_qarg_itm(Bry_.new_u8(key), Bry_.Empty);}
	public static Gfo_qarg_itm[] Ary(String... kvs) {
		int len = kvs.length;
		Gfo_qarg_itm[] rv = new Gfo_qarg_itm[len / 2];
		String key = null;
		for (int i = 0; i < len; ++i) {
			String s = kvs[i];
			if (i % 2 == 0)
				key = s;
			else
				rv[i / 2] = new Gfo_qarg_itm(Bry_.new_u8(key), Bry_.new_u8(s));
		}
		return rv;
	}
	public static String To_str(Gfo_qarg_itm[] ary) {
		int len = ary.length;
		Bry_bfr bfr = Bry_bfr_.New();
		for (int i = 0; i < len; ++i) {
			Gfo_qarg_itm itm = ary[i];
			bfr.Add(itm.Key_bry()).Add_byte_eq();
			if (itm.Val_bry() != null)
				bfr.Add(itm.Val_bry());
			bfr.Add_byte_nl();
		}
		return bfr.To_str_and_clear();
	}
}
