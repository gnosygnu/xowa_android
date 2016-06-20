package gplx.xowa.specials; import gplx.*; import gplx.xowa.*;
public class Xow_special_meta {
	public Xow_special_meta(int src, String key_str, String... aliases) {
		this.src = src; this.key_str = key_str;
		this.key_bry = Bry_.new_u8(key_str);
		this.ttl_bry = Bry_.Add(gplx.xowa.wikis.nss.Xow_ns_.Bry__special, Byte_ascii.Colon_bry, key_bry);
		this.ttl_str = String_.new_u8(ttl_bry);
		this.aliases = Bry_.Ary(aliases);
	}
	public int Src()				{return src;} private final    int src;					// either MW or XOWA
	public String Key_str()			{return key_str;} private final    String key_str;		// EX: AllPages
	public byte[] Key_bry()			{return key_bry;} private final    byte[] key_bry;
	public String Ttl_str()			{return ttl_str;} private final    String ttl_str;		// EX: Special:AllPages
	public byte[] Ttl_bry()			{return ttl_bry;} private final    byte[] ttl_bry;
	public byte[][] Aliases()		{return aliases;} private final    byte[][] aliases;	// EX: Special:RandomPage has Special:Random as alias
	public byte[] Display_ttl()		{return display_ttl;} private byte[] display_ttl; public Xow_special_meta Display_ttl_(String v) {display_ttl = Bry_.new_u8(v); return this;} 

	public boolean Match_ttl(Xoa_ttl ttl) {
		return ttl.Ns().Id_is_special() && Bry_.Eq(ttl.Root_txt(), key_bry);
	}

	public static Xow_special_meta New_xo(String key, String display, String... aliases) {
		return new Xow_special_meta(Xow_special_meta_.Src__xowa, key, aliases).Display_ttl_(display);
	}
}
