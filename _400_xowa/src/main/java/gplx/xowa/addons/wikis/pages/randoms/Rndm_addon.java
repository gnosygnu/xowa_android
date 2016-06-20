package gplx.xowa.addons.wikis.pages.randoms; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.pages.*;
import gplx.xowa.bldrs.wkrs.*; import gplx.xowa.addons.wikis.pages.randoms.bldrs.*;
import gplx.xowa.specials.*; import gplx.xowa.addons.wikis.pages.randoms.specials.*;
import gplx.xowa.addons.wikis.pages.randoms.mgrs.*;
public class Rndm_addon implements Xoax_addon_itm, Xoax_addon_itm__bldr, Xoax_addon_itm__special {
	public Rndm_addon() {this.mgr = null;}	// prototype
	public Rndm_addon(Xow_wiki wiki) {this.mgr = new Rndm_mgr(wiki);}
	public Rndm_mgr Mgr() {return mgr;} private final    Rndm_mgr mgr;
	public Xob_cmd[] Bldr_cmds() {
		return new Xob_cmd[] 
		{ Rndm_bldr_cmd.Prototype
		};
	}
	public Xow_special_page[] Special_pages() {
		return new Xow_special_page[] 
		{ Rndm_root_special.Prototype
		, Rndm_page_special.Prototype
		};
	}

	public static Rndm_addon Get(Xow_wiki wiki) {
		Rndm_addon rv = (Rndm_addon)wiki.Addon_mgr().Itms__get_or_null(ADDON_KEY);
		if (rv == null) {
			rv = new Rndm_addon(wiki);
			wiki.Addon_mgr().Itms__add(rv);
		}
		return rv;
	}

	public String Addon__key() {return ADDON_KEY;} private static final String ADDON_KEY = "xowa.builds.randoms";

}
