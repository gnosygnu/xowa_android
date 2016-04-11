package gplx.xowa.addons.apps.searchs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*;
import gplx.xowa.bldrs.wkrs.*; import gplx.xowa.addons.apps.searchs.bldrs.cmds.*;
public class Xoax_builds_search_addon implements Xoax_addon_itm, Xoax_addon_itm__bldr {
	public Xob_cmd[] Cmds_ary() {
		return new Xob_cmd[]
		{ Xobldr__link__link_score.Prototype
		, Xobldr__page__page_score.Prototype
		, Xobldr__word__link_count.Prototype
		};
	}

	public static final    byte[] ADDON_KEY = Bry_.new_a7("xowa.builds.search");
	public byte[] Addon__key()	{return ADDON_KEY;}
}
