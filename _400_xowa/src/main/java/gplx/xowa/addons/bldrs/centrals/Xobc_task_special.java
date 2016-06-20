package gplx.xowa.addons.bldrs.centrals; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*;
import gplx.xowa.specials.*; import gplx.core.net.*; import gplx.core.net.qargs.*; import gplx.xowa.wikis.pages.*;
public class Xobc_task_special implements Xow_special_page {
	public static Xobc_task_mgr Task_mgr;
	public void Special__gen(Xow_wiki wiki, Xoa_page page, Xoa_url url, Xoa_ttl ttl) {
		// init task_mgr
		page.Html_data().Cbk_enabled_(true);	// apply cbk_enabled early to capture logging statements
		Xoa_app app = wiki.App();
		if (Task_mgr == null) Task_mgr = New_task_mgr(app);
		Task_mgr.Load_or_init();

		new Xobc_task_html().Bld_page_by_mustache(app, page, this);
	}
	private static Xobc_task_mgr New_task_mgr(Xoa_app app) {
		Io_url data_db_url = app.Fsys_mgr().Bin_addon_dir().GenSubFil_nest("bldr", "central", "bldr_central.data_db.xowa");
		app.User().User_db_mgr().Init_site_mgr();
		return new Xobc_task_mgr(app, data_db_url);
	}

	Xobc_task_special(Xow_special_meta special__meta) {this.special__meta = special__meta;}
	public Xow_special_meta Special__meta()		{return special__meta;} private final    Xow_special_meta special__meta;
	public Xow_special_page Special__clone()	{return this;}
	public static final    Xow_special_page Prototype = new Xobc_task_special(Xow_special_meta.New_xo("XowaDownloadCentral", "Download Central"));
}
