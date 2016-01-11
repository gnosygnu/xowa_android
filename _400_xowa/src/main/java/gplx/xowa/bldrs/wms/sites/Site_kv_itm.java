package gplx.xowa.bldrs.wms.sites; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wms.*;
class Site_kv_itm {
	public Site_kv_itm(byte[] key, byte[] val) {this.key = key; this.val = val;}
	public byte[] Key() {return key;} private final byte[] key;
	public byte[] Val() {return val;} private final byte[] val;
}
