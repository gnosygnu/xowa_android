package gplx.xowa; import gplx.*;
import gplx.xowa.wikis.pages.*;
public interface Xoa_page {
	Xow_wiki				Wiki();
	Xoa_url					Url();
	byte[]					Url_bry_safe();
	Xoa_ttl					Ttl();
	boolean					Exists();

	Xoa_page__commons_mgr	Commons_mgr();
	void					Xtn_gallery_packed_exists_y_();
}