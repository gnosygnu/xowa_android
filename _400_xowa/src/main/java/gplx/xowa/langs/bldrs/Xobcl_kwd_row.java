package gplx.xowa.langs.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*;
public class Xobcl_kwd_row {
	public Xobcl_kwd_row(byte[] key, byte[][] itms) {
		this.key = key; this.itms = itms;
		for (byte[] itm : itms)
			itms_hash.Add(itm, itm);
	} 
	public byte[] Key() {return key;} private byte[] key;
	public byte[][] Itms() {return itms;} private byte[][] itms;
	public boolean Itms_has(byte[] key) {return itms_hash.Has(key);} private Ordered_hash itms_hash = Ordered_hash_.New_bry();
}
