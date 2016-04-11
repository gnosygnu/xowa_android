package gplx.xowa.specials; import gplx.*; import gplx.xowa.*;
public interface Xows_page {
	Xows_special_meta	Special__meta();
	Xows_page			Special__clone();
	void				Special__gen(Xow_wiki wikii, Xoa_page pagei, Xoa_url url, Xoa_ttl ttl);
}
