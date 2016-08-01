package gplx.xowa.xtns.wbases.claims.enums; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*; import gplx.xowa.xtns.wbases.claims.*;
public class Wbase_claim_type {
	public Wbase_claim_type(byte tid, String key_str, String key_for_scrib) {
		this.tid = tid;
		this.key_str = key_str;
		this.key_bry = Bry_.new_u8(key_str);
		this.key_for_scrib = key_for_scrib;
	}
	public byte Tid()				{return tid;} private final    byte tid;
	public String Key_str()			{return key_str;} private final    String key_str;
	public byte[] Key_bry()			{return key_bry;} private final    byte[] key_bry;
	public String Key_for_scrib()	{return key_for_scrib;} private final    String key_for_scrib;
}
