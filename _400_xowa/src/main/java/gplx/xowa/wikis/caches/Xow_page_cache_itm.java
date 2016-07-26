package gplx.xowa.wikis.caches; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.xowa.wikis.data.tbls.*;
public class Xow_page_cache_itm implements Xowd_text_bry_owner {
	public Xow_page_cache_itm(Xoa_ttl ttl, byte[] wtxt__direct, byte[] wtxt__redirect) {
		this.ttl = ttl; this.wtxt__direct = wtxt__direct; this.wtxt__redirect = wtxt__redirect;
	}
	public Xoa_ttl Ttl() {return ttl;} private Xoa_ttl ttl;
	public byte[] Wtxt__direct()	{return wtxt__direct;} private byte[] wtxt__direct;
	public byte[] Wtxt__redirect()	{return wtxt__redirect;} private byte[] wtxt__redirect;
	public byte[] Wtxt__redirect_or_direct() {
		return wtxt__redirect == null ? wtxt__direct : wtxt__redirect;
	}

	// used by xomp
	public int Page_id() {return page_id;} private int page_id;
	public int Redirect_id() {return redirect_id;} private int redirect_id;
	public void Set_text_bry_by_db(byte[] v) {this.wtxt__direct = v;}
	public void Set_page_ids(int page_id, int redirect_id) {this.page_id = page_id; this.redirect_id = redirect_id;}
	public void Set_redirect_bry(byte[] trg_wtxt) {
		this.wtxt__redirect = wtxt__direct;
		this.wtxt__direct = trg_wtxt;
	}

	public static final    Xow_page_cache_itm Null = null;
	public static final    Xow_page_cache_itm Missing = new Xow_page_cache_itm(null, null, null);
}
