package gplx.xowa.addons.apps.file_browsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*;
import gplx.xowa.specials.*;
public class Fbrow_addon implements Xoax_addon_itm, Xoax_addon_itm__sp {
	public Xows_page[] Pages_ary() {
		return new Xows_page[]
		{ Fbrow_special_page.Prototype
		, Wikis_list_page.Prototype
		};
	}

	public static final    byte[] ADDON_KEY = Bry_.new_a7("xowa.apps.file_browsers");
	public byte[] Addon__key()	{return ADDON_KEY;}
}
