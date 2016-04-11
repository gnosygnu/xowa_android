package gplx.xowa.addons.builds.pagelinks; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.builds.*;
import gplx.xowa.bldrs.wkrs.*;
import gplx.xowa.addons.builds.pagelinks.bldrs.*;
public class Xoax_builds_pagelinks_addon implements Xoax_addon_itm, Xoax_addon_itm__bldr {
	public Xob_cmd[] Cmds_ary() {
		return new Xob_cmd[]
		{ Pglnk_bldr_cmd.Prototype
		};
	}

	public static final    byte[] ADDON_KEY = Bry_.new_a7("xowa.builds.pagelinks");
	public byte[] Addon__key()	{return ADDON_KEY;}
}
