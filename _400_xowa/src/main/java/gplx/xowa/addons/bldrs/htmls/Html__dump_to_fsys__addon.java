package gplx.xowa.addons.bldrs.htmls; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*;
import gplx.xowa.bldrs.wkrs.*;
public class Html__dump_to_fsys__addon implements Xoax_addon_itm, Xoax_addon_itm__bldr {
	public Xob_cmd[] Bldr_cmds() {
		return new Xob_cmd[] 
		{ Html__dump_to_fsys__cmd.Prototype
		};
	}

	public String Addon__key() {return "xowa.builds.htmls";}
}
