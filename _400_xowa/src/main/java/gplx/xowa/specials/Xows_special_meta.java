package gplx.xowa.specials; import gplx.*; import gplx.xowa.*;
public class Xows_special_meta {
	public Xows_special_meta(int src, int uid, String key_str) {
		this.src = src; this.uid = uid; this.key_str = key_str;
		this.key_bry = Bry_.new_u8(key_str);
		this.ttl_str = "Special:" + key_str;	// canonical name
		this.ttl_bry = Bry_.new_u8(ttl_str);
	}
	public int Src() {return src;} private final int src;
	public int Uid() {return uid;} private final int uid;
	public String Key_str() {return key_str;} private final String key_str;
	public byte[] Key_bry() {return key_bry;} private final byte[] key_bry;
	public String Ttl_str() {return ttl_str;} private final String ttl_str;
	public byte[] Ttl_bry() {return ttl_bry;} private final byte[] ttl_bry;
	public boolean Match_ttl(Xoa_ttl ttl) {
		return ttl.Ns().Id_is_special() && Bry_.Eq(ttl.Root_txt(), key_bry);
	}
}
