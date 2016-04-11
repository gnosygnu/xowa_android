package gplx.xowa.addons; import gplx.*; import gplx.xowa.*;
public class Xoax_addon_mgr {
	private final    Ordered_hash hash = Ordered_hash_.New_bry();
	// THREAD: must synchronized else two search tabs will fail on startup
	public Xoax_addon_itm	Itms__get_or_null(byte[] key) {synchronized (hash) {return (Xoax_addon_itm)hash.Get_by(key);}}
	public void				Itms__add_many(Xoax_addon_itm... ary) {
		for (Xoax_addon_itm itm : ary)
			Itms__add(itm);
	}
	public void				Itms__add(Xoax_addon_itm itm) {
		synchronized (hash) {
			Xoa_app_.Usr_dlg().Log_many("", "", "addons.init: ~{0}", itm.Addon__key());
			hash.Add(itm.Addon__key(), itm);
		}
	}
	public Xoax_addon_mgr Add_dflts_by_app(Xoa_app app) {
		app.Bldr().Cmd_regy().Add_many
		( gplx.xowa.bldrs.cmds.utils.Xob_alert_cmd.Prototype
		);
		app.Addon_mgr().Itms__add_many
		( new gplx.xowa.addons.builds.files				.Xoax_builds_files_addon()
		, new gplx.xowa.addons.builds.pagelinks			.Xoax_builds_pagelinks_addon()
		, new gplx.xowa.addons.builds.utils_rankings	.Xoax_builds_utils_rankings_addon()
		, new gplx.xowa.addons.apps.searchs				.Xoax_builds_search_addon()
		, new gplx.xowa.addons.apps.file_browsers		.Fbrow_addon()
		, new gplx.xowa.addons.updates.files			.Xoax_updates_files_addon()
		);
		return this;
	}
	public void Run_by_app(Xoa_app app) {
		int len = hash.Len();
		for (int i = 0; i < len; ++i) {
			Xoax_addon_itm addon = (Xoax_addon_itm)hash.Get_at(i);
			if (Type_adp_.Implements_intf_obj(addon, Xoax_addon_itm__bldr.class)) {
				Xoax_addon_itm__bldr addon_bldr = (Xoax_addon_itm__bldr)addon;
				app.Bldr().Cmd_regy().Add_many(addon_bldr.Cmds_ary());
			}
			if (Type_adp_.Implements_intf_obj(addon, Xoax_addon_itm__sp.class)) {
				Xoax_addon_itm__sp addon_sp = (Xoax_addon_itm__sp)addon;
				app.Special_regy().Add_many(addon_sp.Pages_ary());
			}
		}
	}
}
