package gplx.xowa; import gplx.*;
import gplx.xowa.apps.apis.xowa.bldrs.imports.*; import gplx.xowa.wikis.data.*; import gplx.xowa.bldrs.infos.*;
public class Xowe_wiki_ {
	public static void Create(Xowe_wiki wiki, long src_fil_len, String src_fil_name) {
		wiki.Db_mgr_create_as_sql(); // create db_mgr as sql
		Xoapi_import import_api = wiki.Appe().Api_root().Bldr().Wiki().Import();
		Xowd_core_db_props db_mgr_props = import_api.New_props(wiki.Domain_str(), src_fil_len);
		Xob_info_session info_session = Xob_info_session.new_(import_api.User_name(), wiki.Domain_str(), src_fil_name);
		wiki.Db_mgr_as_sql().Core_data_mgr().Init_by_make(db_mgr_props, info_session);	// make core_db
	}
}
