package gplx.xowa; import gplx.*;
import gplx.xowa.wikis.*;
public class Xow_wiki_ {
	public static void Create_sql_backend(Xow_wiki wiki, gplx.xowa.wikis.data.Xowd_core_db_props core_db_props, gplx.xowa.bldrs.infos.Xob_info_session session) {
		if (wiki.Type_is_edit()) {
			Xowe_wiki wikie = (Xowe_wiki)wiki;
			wikie.Db_mgr_create_as_sql();	// edit-wikis are created with text-backend; convert to sql
			wiki.Data__core_mgr().Init_by_make(core_db_props, session);	// make core_db			
		}
		else {
			Xowv_wiki wikiv = (Xowv_wiki)wiki;
			wikiv.Init_by_make(core_db_props, session);	// make core_db			
		}
	}
}
