package gplx.xowa.addons.wikis.searchs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*;
import gplx.xowa.bldrs.wkrs.*; import gplx.xowa.addons.wikis.searchs.bldrs.cmds.*;
public class Xoax_builds_search_addon implements Xoax_addon_itm, Xoax_addon_itm__bldr {
	public Xob_cmd[] Bldr_cmds() {
		return new Xob_cmd[]
		{ Xobldr__link__link_score.Prototype
		, Xobldr__page__page_score.Prototype
		, Xobldr__word__link_count.Prototype
		};
	}

	public String Addon__key() {return "xowa.builds.search";}
}
