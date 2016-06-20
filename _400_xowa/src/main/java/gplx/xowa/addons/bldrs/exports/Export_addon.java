package gplx.xowa.addons.bldrs.exports; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*;
import gplx.xowa.bldrs.wkrs.*;
public class Export_addon implements Xoax_addon_itm, Xoax_addon_itm__bldr {
	public Xob_cmd[] Bldr_cmds() {
		return new Xob_cmd[] 
		{ gplx.xowa.addons.bldrs.exports.splits.Split_bldr_cmd.Prototype
		, gplx.xowa.addons.bldrs.exports.merges.Merge_bldr_cmd.Prototype
		, gplx.xowa.addons.bldrs.exports.packs.splits.Pack_split_bldr_cmd.Prototype
		, gplx.xowa.addons.bldrs.exports.packs.files.Pack_file_bldr_cmd.Prototype
		};
	}

	public String Addon__key() {return "xowa.builds.repacks";}
}
