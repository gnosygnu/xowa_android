package gplx.xowa.addons.bldrs.utils_rankings; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*;
import gplx.xowa.bldrs.wkrs.*;
import gplx.xowa.addons.bldrs.utils_rankings.bldrs.*;
public class Xoax_builds_utils_rankings_addon implements Xoax_addon_itm, Xoax_addon_itm__bldr {
	public Xob_cmd[] Bldr_cmds() {
		return new Xob_cmd[]
		{ Sqlite_percentile_cmd.Prototype
		};
	}

	public String Addon__key() {return "xowa.builds.utils_rankings";}
}
