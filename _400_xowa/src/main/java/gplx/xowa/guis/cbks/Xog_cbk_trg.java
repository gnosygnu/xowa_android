package gplx.xowa.guis.cbks; import gplx.*; import gplx.xowa.*; import gplx.xowa.guis.*;
public class Xog_cbk_trg {
	Xog_cbk_trg(byte tid, byte[] page_ttl) {this.tid = tid; this.page_ttl = page_ttl;}
	public byte Tid() {return tid;} private final    byte tid;
	public byte[] Page_ttl() {return page_ttl;} private final    byte[] page_ttl;	// same as ttl.Full_db(); EX: Special:XowaDownloadCentral

	public static final byte Tid__cbk_enabled = 0, Tid__specific_page = 1;
	public static final    Xog_cbk_trg Any = new Xog_cbk_trg(Tid__cbk_enabled, null);
	public static Xog_cbk_trg New(byte[] page_ttl) {return new Xog_cbk_trg(Tid__specific_page, page_ttl);}
}
