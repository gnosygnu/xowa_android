package gplx.xowa.xtns.pfuncs.ifs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.core.envs.*; import gplx.core.caches.*;
import gplx.xowa.apps.wms.apis.*; import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.wikis.nss.*;
public class Pfunc_ifexist_mgr {
	private final    Xowd_page_itm db_page = Xowd_page_itm.new_tmp();
	public boolean Exists(Xowe_wiki wiki, byte[] raw_bry) {
		if (Bry_.Len_eq_0(raw_bry)) return false;	// return early; NOTE: {{autolink}} can pass in "" (see test)
		Xoa_ttl ttl = Xoa_ttl.Parse(wiki, raw_bry); if (ttl == null) return false;
		byte[] ttl_bry = ttl.Page_db();				// NOTE: must use Page_db; EX: {{#ifexist:File:Peter & Paul fortress in SPB 03.jpg|y|n}}

		// try to get from cache
		Gfo_cache_mgr cache_mgr = wiki.Cache_mgr().Ifexist_cache();
		Pfunc_ifexist_itm cache_itm = (Pfunc_ifexist_itm)cache_mgr.Get_by_key(ttl_bry);
		if (cache_itm != null) return cache_itm.Exists();

		cache_itm = new Pfunc_ifexist_itm(ttl_bry);
		cache_mgr.Add(ttl_bry, cache_itm, 1);
		db_page.Clear();
		Xow_ns ttl_ns = ttl.Ns();
		boolean rv = false;
		switch (ttl_ns.Id()) {
			case Xow_ns_.Tid__special:	rv = true; break; // NOTE: some pages call for [[Special]]; always return true for now; DATE:2014-07-17
			case Xow_ns_.Tid__media:	rv = Find_ttl_for_media_ns(cache_itm, wiki, ttl_ns, ttl_bry); break;
			default:					rv = Find_ttl_in_db(cache_itm, wiki, ttl_ns, ttl_bry); break;
		}
		cache_itm.Exists_(rv);
		return rv;
	}
	private boolean Find_ttl_in_db(Pfunc_ifexist_itm itm, Xowe_wiki wiki, Xow_ns ns, byte[] ttl_bry) {
		boolean rv = wiki.Db_mgr().Load_mgr().Load_by_ttl(db_page, ns, ttl_bry);
		if (	!rv
			&&	wiki.Lang().Vnt_mgr().Enabled()) {
			Xowd_page_itm page = wiki.Lang().Vnt_mgr().Convert_mgr().Convert_ttl(wiki, ns, ttl_bry);
			if (page != Xowd_page_itm.Null)
				rv = page.Exists();
		}
		itm.Exists_(rv);
		return rv;
	}
	private boolean Find_ttl_for_media_ns(Pfunc_ifexist_itm itm, Xowe_wiki wiki, Xow_ns ns, byte[] ttl_bry) {
		Xow_ns file_ns = wiki.Ns_mgr().Ns_file();
		boolean rv = Find_ttl_in_db(itm, wiki, file_ns, ttl_bry); if (rv) return true;		// rarely true, but check local wiki's [[File:]] table anyway
		Xowe_wiki commons_wiki = wiki.Appe().Wiki_mgr().Wiki_commons();
		boolean env_is_testing = Env_.Mode_testing();
		if (	commons_wiki != null														// null check
			&&	(	commons_wiki.Init_assert().Db_mgr().Tid() == gplx.xowa.wikis.dbs.Xodb_mgr_sql.Tid_sql	// make sure tid=sql; tid=txt automatically created for online images; DATE:2014-09-21
				||	env_is_testing
				)
			) {
			file_ns = commons_wiki.Ns_mgr().Ns_file();
			return Find_ttl_in_db(itm, commons_wiki, file_ns, ttl_bry);						// accurate test using page table in commons wiki (provided commons is up to date)
		}
		else {
			if (!env_is_testing)
				wiki.File_mgr().Init_file_mgr_by_load(wiki);								// NOTE: must init Fsdb_mgr (else conn == null), and with bin_wkrs (else no images will ever load); DATE:2014-09-21
			return wiki.File_mgr().Exists(ttl_bry);											// less-accurate test using either (1) orig_wiki table in local wiki (v2) or (2) meta_db_mgr (v1)
		}
	}
 	}
class Pfunc_ifexist_itm implements Rls_able {
	public Pfunc_ifexist_itm(byte[] ttl) {this.ttl = ttl;}
	public byte[] Ttl() {return ttl;} private byte[] ttl;
	public boolean Exists() {return exists;} public void Exists_(boolean v) {exists = v;} private boolean exists;
	public void Rls() {}
}