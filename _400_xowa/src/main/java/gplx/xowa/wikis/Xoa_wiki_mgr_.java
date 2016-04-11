package gplx.xowa.wikis; import gplx.*; import gplx.xowa.*;
import gplx.dbs.*;
import gplx.dbs.cfgs.*; import gplx.xowa.wikis.data.*; import gplx.xowa.bldrs.infos.*;
public class Xoa_wiki_mgr_ {
	public static Xow_wiki Import_by_url(Xoa_app app, Xoa_wiki_mgr wiki_mgr, Io_url url) {
		Db_conn conn = Db_conn_bldr.Instance.Get_or_noop(url); if (conn == Db_conn_.Noop) return null;	// invalid url
		Db_cfg_tbl cfg_tbl = Xowd_cfg_tbl_.Get_or_null(conn); if (cfg_tbl == null) return null;	// no xowa_cfg;
		byte[] wiki_domain = cfg_tbl.Select_bry(Xow_cfg_consts.Grp__bldr_session, Xob_info_session.Cfg_key__wiki_domain); if (wiki_domain == null) return null;
		Io_url wiki_root_dir = url.OwnerDir();
		Xow_wiki rv = wiki_mgr.Make(wiki_domain, wiki_root_dir);
		wiki_mgr.Add(rv);
		// byte[] modified_last	= cfg_tbl.Select_bry(Xow_cfg_consts.Grp__wiki_init, "props.modified_latest");
		app.User().User_db_mgr().Site_mgr().Import(rv.Domain_str(), rv.Domain_str(), wiki_root_dir.Raw(), "");
		return rv;
	}
}
