package gplx.xowa.wikis.caches; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
public class Xow_page_cache_itm {
	public Xow_page_cache_itm(Xoa_ttl ttl, byte[] wtxt__direct, byte[] wtxt__redirect) {
		this.ttl = ttl; this.wtxt__direct = wtxt__direct; this.wtxt__redirect = wtxt__redirect;
	}
	public Xoa_ttl Ttl() {return ttl;} private Xoa_ttl ttl;
	public byte[] Wtxt__direct()	{return wtxt__direct;} private byte[] wtxt__direct;
	public byte[] Wtxt__redirect()	{return wtxt__redirect;} private byte[] wtxt__redirect;
	public byte[] Wtxt__redirect_or_direct() {
		return wtxt__redirect == null ? wtxt__direct : wtxt__redirect;
	}
	public static final    Xow_page_cache_itm Null = null;
}
