package gplx.xowa.users.bmks; import gplx.*; import gplx.xowa.*; import gplx.xowa.users.*;
import gplx.core.primitives.*;
import gplx.xowa.htmls.bridges.dbuis.tbls.*;
import gplx.xowa.users.data.*; import gplx.xowa.specials.*;
public class Xows_bmk_page implements Xows_page {
	public Xows_special_meta Special_meta() {return Xows_special_meta_.Itm__bookmarks;}
	public void Special_gen(Xowe_wiki wiki, Xoae_page page, Xoa_url url, Xoa_ttl ttl) {
		Xoa_app app = wiki.App();
		Dbui_tbl_itm__bmk ui_tbl = Dbui_tbl_itm__bmk.get_or_new(app, app.User().User_db_mgr().Bmk_mgr().Tbl__itm());
		page.Html_data().Head_mgr().Itm__dbui().Init(app).Enabled_y_();
		Bry_bfr bfr = wiki.Utl__bfr_mkr().Get_m001();
		ui_tbl.Select(bfr, Xoud_bmk_mgr.Owner_root);
		page.Hdump_data().Body_(bfr.To_bry_and_rls());
	}
}