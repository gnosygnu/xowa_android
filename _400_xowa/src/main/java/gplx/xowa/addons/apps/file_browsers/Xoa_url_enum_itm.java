package gplx.xowa.addons.apps.file_browsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*;
public class Xoa_url_enum_itm {
	private final    Hash_adp_bry hash = Hash_adp_bry.cs();
	public Xoa_url_enum_itm(byte[] key) {this.key = key;}
	public byte[] Key() {return key;} private final    byte[] key;
	public Xoa_url_enum_itm Add(String key, int val) {
		hash.Add_bry_int(Bry_.new_u8(key), val);
		return this;
	}
	public int Get_as_int_or(byte[] val, int or) {return hash.Get_as_int_or(val, or);}
}
