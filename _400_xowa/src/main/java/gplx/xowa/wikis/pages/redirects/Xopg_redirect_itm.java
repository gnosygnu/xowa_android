package gplx.xowa.wikis.pages.redirects; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.pages.*;
public class Xopg_redirect_itm {
	public Xopg_redirect_itm(Xoa_url url, Xoa_ttl ttl, byte[] wikitext) {
		this.url = url;
		this.ttl = ttl;
		this.wikitext = wikitext;
	}
	public Xoa_url		Url()				{return url;} private final    Xoa_url url;					// EX: "en.wikipedia.org/wiki/A"
	public Xoa_ttl		Ttl()				{return ttl;} private final    Xoa_ttl ttl;					// EX: "A"
	public byte[]		Wikitext()			{return wikitext;} private final    byte[] wikitext;		// EX: "#REDIRECT [[A]]"
	public boolean			By_wikitext()		{return wikitext != null;}									// true if by "#REDIRECT [[A]]"; false if by Special:Random, Special:Search, etc.; 
}
