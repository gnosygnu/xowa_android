package gplx.xowa.addons.bldrs.hdumps.diffs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.hdumps.*;
import gplx.xowa.bldrs.wkrs.*;
public class Dumpdiff_addon implements Xoax_addon_itm, Xoax_addon_itm__bldr {
	public Xob_cmd[] Bldr_cmds() {
		return new Xob_cmd[] 
		{ Dumpdiff_cmd.Prototype
		};
	}

	public String Addon__key() {return "xowa.builds.hdumps.diff";}
}
