package gplx.xowa.xtns.wbases.claims.enums; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*; import gplx.xowa.xtns.wbases.claims.*;
public class Wbase_enum_itm {
	public Wbase_enum_itm(byte tid, String key_str) {
		this.tid = tid;
		this.key_str = key_str;
		this.key_bry = Bry_.new_u8(key_str);
	}
	public byte Tid()				{return tid;} private final    byte tid;
	public String Key_str()			{return key_str;} private final    String key_str;
	public byte[] Key_bry()			{return key_bry;} private final    byte[] key_bry;
}