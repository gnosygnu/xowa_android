package gplx.xowa.addons.bldrs.updates.files; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.updates.*;
import gplx.xowa.bldrs.wkrs.*;
import gplx.xowa.addons.bldrs.utils_rankings.bldrs.*;
import gplx.xowa.addons.bldrs.updates.files.find_missings.*;
public class Xodel_addon implements Xoax_addon_itm, Xoax_addon_itm__bldr {
	public Xob_cmd[] Bldr_cmds() {
		return new Xob_cmd[] 
		{ Xodel_make_cmd.Prototype
		, Xodel_exec_cmd.Prototype
		, Xodel_small_cmd.Prototype
		, Xodel_find_missing_cmd.Prototype
		};
	}

	public String Addon__key() {return "xowa.updates.files";}
}
