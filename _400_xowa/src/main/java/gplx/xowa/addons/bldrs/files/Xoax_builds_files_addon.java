package gplx.xowa.addons.bldrs.files; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*;
import gplx.xowa.bldrs.wkrs.*;
import gplx.xowa.addons.bldrs.files.cmds.*;
public class Xoax_builds_files_addon implements Xoax_addon_itm, Xoax_addon_itm__bldr {
	public Xob_cmd[] Bldr_cmds() {
		return new Xob_cmd[]
		{ Xobldr__lnki_temp__create.Prototype
		, Xobldr__lnki_regy__create.Prototype
		, Xobldr__page_regy__create.Prototype
		, Xobldr__orig_regy__create.Prototype
		, Xobldr__xfer_temp__insert_thm.Prototype
		, Xobldr__xfer_temp__insert_orig.Prototype
		, Xobldr__xfer_regy__create.Prototype
		, Xobldr__xfer_regy__update_downloaded.Prototype

		, Xobldr__fsdb_db__create_data.Prototype
		, Xobldr__fsdb_db__create_orig.Prototype
		, Xobldr__page_file_map__create.Prototype

		, Xobldr__text_db__make_page.Prototype
		, Xobldr__text_db__drop_page.Prototype
		, Xobldr__redirect__create.Prototype
		, Xobldr__image__create.Prototype
		};
	}

	public String Addon__key() {return "xowa.builds.images";}
}
