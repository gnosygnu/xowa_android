package gplx.xowa.addons.bldrs.xodirs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*;
import gplx.xowa.specials.*;
import gplx.xowa.addons.wikis.imports.*;
public class Xobc_xodir_addon implements Xoax_addon_itm, Xoax_addon_itm__special, Xoax_addon_itm__init {
	public Xow_special_page[] Special_pages() {
		return new Xow_special_page[]
		{ Xobc_xodir_special.Prototype
		};
	}
	public void Init_addon_by_app(Xoa_app app) {
	}
	public void Init_addon_by_wiki(Xow_wiki wiki) {
		Xow_import_addon addon = Xow_import_addon.Addon__get(wiki);
		addon.Dir_selected_cbks__add(Xow_import_dir_cbk__xodir.Instance);
	}

	public String Addon__key() {return ADDON__KEY;} private static final String ADDON__KEY = "xowa.bldrs.xodirs";
}
class Xow_import_dir_cbk__xodir implements Xow_import_dir_cbk {
	public String Key() {return "xodir";}
	public void Cbk__dir_selected(Xow_wiki wiki, Xoa_page page, String path) {
		// save to prefs
		wiki.App().User().User_db_mgr().Cfg().Set_app_str("xowa.xodir.custom_dir", path);

		// redirect to import_dir
		page.Redirect().Itms__add__special(wiki, Xobc_xodir_special.Prototype.Special__meta());
	}
        public static Xow_import_dir_cbk__xodir Instance = new Xow_import_dir_cbk__xodir(); Xow_import_dir_cbk__xodir() {}
}
