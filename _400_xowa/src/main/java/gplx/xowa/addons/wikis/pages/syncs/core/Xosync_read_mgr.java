package gplx.xowa.addons.wikis.pages.syncs.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.pages.*; import gplx.xowa.addons.wikis.pages.syncs.*;
import gplx.dbs.*;
import gplx.xowa.htmls.*;
import gplx.xowa.addons.wikis.pages.syncs.dbs.*;
import gplx.xowa.apps.apis.xowa.addons.bldrs.*;
import gplx.xowa.wikis.data.tbls.*;
public class Xosync_read_mgr {
	private final    Xowd_page_itm tmp_dbpg = new Xowd_page_itm();
	private Db_conn sync_conn; private Xosync_sync_tbl sync_tbl;
	private final    Xosync_update_mgr update_mgr = new Xosync_update_mgr();
	public void Auto_update(Xow_wiki wiki, Xoa_page page, Xoa_ttl page_ttl) {
		if (wiki.Domain_itm().Domain_type_id() == gplx.xowa.wikis.domains.Xow_domain_tid_.Tid__home) return;
		if (page_ttl.Ns().Id_is_special()) return;

		Xoapi_sync_api sync_api = wiki.App().Api_root().Addon().Bldr().Sync();
		if (!sync_api.Auto_enabled()) return;
		if (!sync_api.Auto_page_matcher().Match(page_ttl.Full_db())) return;

		wiki.Data__core_mgr().Db__core().Tbl__page().Select_by_ttl(tmp_dbpg, page_ttl.Ns(), page_ttl.Page_db());

		if (sync_conn == null) {
			Io_url sync_db_url = wiki.Fsys_mgr().Root_dir().GenSubFil(wiki.Domain_str() + "-sync.xowa");
			sync_conn = Db_conn_bldr.Instance.Get_or_autocreate(true, sync_db_url);
			sync_tbl = new Xosync_sync_tbl(sync_conn);
			sync_conn.Meta_tbl_assert(sync_tbl);
		}
		DateAdp sync_date = sync_tbl.Select_sync_date_or_min(tmp_dbpg.Id());
		if (Datetime_now.Get().Diff(sync_date).Total_mins().To_int() < sync_api.Auto_interval()) return;
		
		Xoa_app app = wiki.App();
		Xoh_page hpg = new Xoh_page();
		update_mgr.Init_by_app(app);
		update_mgr.Init_by_page(wiki, hpg);
		update_mgr.Update(app.Wmf_mgr().Download_wkr(), wiki, page_ttl);

		sync_tbl.Upsert(tmp_dbpg.Id(), Datetime_now.Get());
	}
}
