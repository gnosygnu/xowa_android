package gplx.xowa; import gplx.*;
import gplx.xowa.wikis.pages.*; import gplx.xowa.wikis.pages.lnkis.*; import gplx.xowa.wikis.pages.dbs.*; import gplx.xowa.wikis.pages.redirects.*; import gplx.xowa.wikis.pages.hdumps.*; import gplx.xowa.wikis.pages.htmls.*;
public interface Xoa_page {
	Xow_wiki				Wiki();
	Xoa_url					Url(); byte[] Url_bry_safe();
	Xoa_ttl					Ttl();
	Xopg_db_data			Db();
	Xopg_redirect_mgr		Redirect();
	Xopg_html_data			Html_data();
	Xopg_hdump_data			Hdump_mgr();

	// Xopg_lnki_list			Redlink_list();

	Xoa_page__commons_mgr	Commons_mgr();
	void					Xtn_gallery_packed_exists_y_();
	boolean					Xtn__timeline_exists();
	boolean					Xtn__gallery_exists();
}
