package gplx.xowa.addons.wikis.pages.syncs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.pages.*;
import gplx.xowa.bldrs.wkrs.*;
import gplx.xowa.specials.*;
import gplx.xowa.addons.wikis.pages.syncs.specials.*;
public class Xosync_addon implements Xoax_addon_itm, Xoax_addon_itm__special {
	public Xow_special_page[] Special_pages() {
		return new Xow_special_page[] 
		{ Sync_html_special.Prototype
		};
	}

	public static Xosync_addon Get(Xow_wiki wiki) {
		Xosync_addon rv = (Xosync_addon)wiki.Addon_mgr().Itms__get_or_null(ADDON_KEY);
		if (rv == null) {
			rv = new Xosync_addon();
			wiki.Addon_mgr().Itms__add(rv);
		}
		return rv;
	}

	public String Addon__key() {return ADDON_KEY;} private static final String ADDON_KEY = "xowa.pages.syncs";
}
