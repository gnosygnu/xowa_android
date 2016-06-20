package gplx.xowa.addons.bldrs.updates.files; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.updates.*;
import gplx.xowa.bldrs.wkrs.*;
import gplx.xowa.addons.bldrs.utils_rankings.bldrs.*;
public class Xoax_updates_files_addon implements Xoax_addon_itm, Xoax_addon_itm__bldr {
	public Xob_cmd[] Bldr_cmds() {
		return new Xob_cmd[] 
		{ Xobldr__deletion_db__make.Prototype
		, Xobldr__deletion_db__exec.Prototype
		, Xobldr__deletion_db__small_files.Prototype
		};
	}

	public String Addon__key() {return "xowa.updates.files";}
}
