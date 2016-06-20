package gplx.xowa.specials; import gplx.*; import gplx.xowa.*;
public interface Xow_special_page {
	Xow_special_meta	Special__meta();
	Xow_special_page	Special__clone();
	void				Special__gen(Xow_wiki wikii, Xoa_page pagei, Xoa_url url, Xoa_ttl ttl);
}
