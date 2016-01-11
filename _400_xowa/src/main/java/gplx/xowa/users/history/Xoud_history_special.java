package gplx.xowa.users.history; import gplx.*; import gplx.xowa.*; import gplx.xowa.users.*;
import gplx.xowa.specials.*;
public class Xoud_history_special implements Xows_page {
	public Xows_special_meta Special_meta() {return Xows_special_meta_.Itm__page_history;}
	public void Special_gen(Xowe_wiki wiki, Xoae_page page, Xoa_url url, Xoa_ttl ttl) {
		Xoa_app app = wiki.App();
		Dbui_tbl_itm__history ui_tbl = Dbui_tbl_itm__history.get_or_new(app, app.User().User_db_mgr().Db_file().Tbl__history());
		page.Html_data().Head_mgr().Itm__dbui().Init(app).Enabled_y_();
		Bry_bfr bfr = wiki.Utl__bfr_mkr().Get_m001();
		ui_tbl.Select(bfr, 100);
		page.Hdump_data().Body_(bfr.To_bry_and_rls());
	}
}
