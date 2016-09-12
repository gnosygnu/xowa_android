package gplx.xowa.addons.wikis.ctgs.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.ctgs.*;
import gplx.xowa.bldrs.wkrs.*;
public class Xoax_ctg_bldr_addon implements Xoax_addon_itm, Xoax_addon_itm__bldr {
	public Xob_cmd[] Bldr_cmds() {
		return new Xob_cmd[]
		{ gplx.xowa.addons.wikis.ctgs.bldrs.Xob_pageprop_cmd.Prototype
		, gplx.xowa.addons.wikis.ctgs.bldrs.Xob_catlink_cmd.Prototype
		};
	}

	public String Addon__key() {return "xowa.builds.ctgs";}
}
