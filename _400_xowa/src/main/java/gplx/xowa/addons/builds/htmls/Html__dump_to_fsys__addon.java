package gplx.xowa.addons.builds.htmls; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.builds.*;
import gplx.xowa.bldrs.wkrs.*;
public class Html__dump_to_fsys__addon implements Xoax_addon_itm, Xoax_addon_itm__bldr {
	public Xob_cmd[] Cmds_ary() {
		return new Xob_cmd[] 
		{ Html__dump_to_fsys__cmd.Prototype
		};
	}

	public static final    byte[] ADDON_KEY = Bry_.new_a7("xowa.builds.htmls");
	public byte[] Addon__key()	{return ADDON_KEY;}
}
