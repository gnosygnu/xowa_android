package gplx.core.net.qargs; import gplx.*; import gplx.core.*; import gplx.core.net.*;
public class Gfo_qarg_enum_itm {
	private final    Hash_adp_bry hash = Hash_adp_bry.cs();
	public Gfo_qarg_enum_itm(String key) {this.key = Bry_.new_u8(key);}
	public Gfo_qarg_enum_itm(byte[] key) {this.key = key;}
	public byte[] Key() {return key;} private final    byte[] key;
	public Gfo_qarg_enum_itm Add(String key, int val) {
		hash.Add_bry_int(Bry_.new_u8(key), val);
		return this;
	}
	public int Get_as_int_or(byte[] val, int or) {return hash.Get_as_int_or(val, or);}
}
