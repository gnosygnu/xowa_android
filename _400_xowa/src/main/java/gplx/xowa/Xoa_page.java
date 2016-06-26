package gplx.xowa; import gplx.*;
import gplx.xowa.wikis.pages.*; import gplx.xowa.wikis.pages.lnkis.*;
public interface Xoa_page {
	Xow_wiki				Wiki();
	Xoa_url					Url(); byte[] Url_bry_safe();
	Xoa_ttl					Ttl();
	boolean					Exists();
	Xopg_revision_data		Revision_data();
	Xopg_html_data			Html_data();
	Xopg_lnki_list			Redlink_list();
	byte[]					Redirect_to_ttl(); void Redirect_to_ttl_(byte[] v);

	Xoa_page__commons_mgr	Commons_mgr();
	void					Xtn_gallery_packed_exists_y_();
	boolean					Xtn__timeline_exists();
	boolean					Xtn__gallery_exists();
}
