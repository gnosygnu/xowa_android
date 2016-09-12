package gplx.xowa; import gplx.*;
import gplx.xowa.langs.*;
import gplx.xowa.wikis.pages.*; import gplx.xowa.wikis.pages.lnkis.*; import gplx.xowa.wikis.pages.dbs.*; import gplx.xowa.wikis.pages.redirects.*; import gplx.xowa.wikis.pages.hdumps.*; import gplx.xowa.wikis.pages.htmls.*; import gplx.xowa.wikis.pages.wtxts.*;
public interface Xoa_page {
	Xow_wiki				Wiki();
	Xoa_url					Url(); byte[] Url_bry_safe();
	Xoa_ttl					Ttl();
	Xopg_db_data			Db();
	Xopg_redirect_mgr		Redirect_trail();
	Xopg_html_data			Html_data();
	Xopg_hdump_data			Hdump_mgr();
	Xopg_wtxt_data			Wtxt();
	Xol_lang_itm			Lang();

	Xoa_page__commons_mgr	Commons_mgr();
	boolean					Xtn__timeline_exists();
	boolean					Xtn__gallery_exists();
}
