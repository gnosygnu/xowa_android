package gplx.xowa.addons.builds.utils_rankings; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.builds.*;
import gplx.xowa.bldrs.wkrs.*;
import gplx.xowa.addons.builds.utils_rankings.bldrs.*;
public class Xoax_builds_utils_rankings_addon implements Xoax_addon_itm, Xoax_addon_itm__bldr {
	public Xob_cmd[] Cmds_ary() {
		return new Xob_cmd[]
		{ Sqlite_percentile_cmd.Prototype
		};
	}

	public static final    byte[] ADDON_KEY = Bry_.new_a7("xowa.builds.utils_rankings");
	public byte[] Addon__key()	{return ADDON_KEY;}
}
