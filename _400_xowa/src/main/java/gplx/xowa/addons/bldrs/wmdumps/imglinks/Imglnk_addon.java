package gplx.xowa.addons.bldrs.wmdumps.imglinks; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.wmdumps.*;
import gplx.xowa.bldrs.wkrs.*;
import gplx.xowa.addons.bldrs.wmdumps.pagelinks.bldrs.*;
public class Imglnk_addon implements Xoax_addon_itm, Xoax_addon_itm__bldr {
	public Xob_cmd[] Bldr_cmds() {
		return new Xob_cmd[]
		{ Imglnk_bldr_cmd.Prototype
		};
	}

	public String Addon__key() {return "xowa.builds.imglinks";}
}
