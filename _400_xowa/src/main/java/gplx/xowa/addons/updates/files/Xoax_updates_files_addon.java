package gplx.xowa.addons.updates.files; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.updates.*;
import gplx.xowa.bldrs.wkrs.*;
import gplx.xowa.addons.builds.utils_rankings.bldrs.*;
public class Xoax_updates_files_addon implements Xoax_addon_itm, Xoax_addon_itm__bldr {
	public Xob_cmd[] Cmds_ary() {
		return new Xob_cmd[] 
		{ Xobldr__deletion_db__make.Prototype
		, Xobldr__deletion_db__exec.Prototype
		, Xobldr__deletion_db__small_files.Prototype
		, Xobldr__deletion_db__temp.Prototype
		};
	}

	public static final    byte[] ADDON_KEY = Bry_.new_a7("xowa.updates.files");
	public byte[] Addon__key()	{return ADDON_KEY;}
}
