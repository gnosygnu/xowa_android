package gplx.xowa.wikis.caches; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
public class Xow_page_cache_itm {
	public Xow_page_cache_itm(Xoa_ttl ttl, byte[] wtxt, byte[] redirected_src_wtxt) {
		this.ttl = ttl; this.wtxt = wtxt; this.redirected_src_wtxt = redirected_src_wtxt;
	}
	public Xoa_ttl Ttl() {return ttl;} private Xoa_ttl ttl;
	public byte[] Wtxt() {return wtxt;} private byte[] wtxt;
	public byte[] Redirected_src_wtxt() {return redirected_src_wtxt;} private byte[] redirected_src_wtxt;
	public static final Xow_page_cache_itm Null = null;
}
