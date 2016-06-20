package gplx.xowa.addons.wikis.registrys; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*;
import gplx.xowa.specials.*;
public class Wiki_registry_addon implements Xoax_addon_itm, Xoax_addon_itm__special {
	public Xow_special_page[] Special_pages() {
		return new Xow_special_page[]
		{ gplx.xowa.addons.wikis.registrys.lists.Xow_list_special.Prototype
		, gplx.xowa.addons.wikis.registrys.infos.Xow_info_special.Prototype
		};
	}

	public String Addon__key() {return "xowa.apps.wikis.registrys";}
}
