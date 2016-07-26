package gplx.xowa.addons.bldrs.mass_parses.parses; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.mass_parses.*;
import gplx.dbs.*;
import gplx.xowa.wikis.caches.*;
class Xomp_tmpl_cache_bldr {
	public static Xow_page_cache New(Xowe_wiki wiki, boolean fill_all) {
		Xow_page_cache rv = new Xow_page_cache(wiki);
		if (fill_all) Fill_all(rv, wiki);
		return rv;
	}
	private static void Fill_all(Xow_page_cache cache, Xowe_wiki wiki) {
		String sql = String_.Concat_lines_nl_skip_last	// ANSI.Y
		( "SELECT  pp.page_id"
		, ",       pp.page_namespace"
		, ",       pp.page_title"
		, ",       pp.page_text_db_id"
		, ",       pp.page_redirect_id"
		, "FROM    page pp"
		, "WHERE   pp.page_namespace IN (10, 828)"
		);

		Xomp_text_db_loader text_db_loader = new Xomp_text_db_loader(wiki);

		// load pages
		int count = 0;
		List_adp redirect_list = List_adp_.New();
		Ordered_hash page_regy = Ordered_hash_.New();
		Db_rdr rdr = wiki.Data__core_mgr().Db__core().Tbl__page().Conn().Stmt_sql(sql).Exec_select__rls_auto();
		try {
			while (rdr.Move_next()) {
				// get ttl
				Xoa_ttl page_ttl = wiki.Ttl_parse(rdr.Read_int("page_namespace"), rdr.Read_bry_by_str("page_title"));

				// add to text_db_loader
				int page_id = rdr.Read_int("page_id"); 
				int page_redirect_id = rdr.Read_int("page_redirect_id");
				Xow_page_cache_itm itm = new Xow_page_cache_itm(page_ttl, Bry_.Empty, Bry_.Empty);
				itm.Set_page_ids(page_id, page_redirect_id);
				text_db_loader.Add(rdr.Read_int("page_text_db_id"), itm);
				cache.Add(page_ttl.Full_db(), itm);
				page_regy.Add(page_id, itm);

				if (page_redirect_id != -1)
					redirect_list.Add(itm);
				if ((++count % 10000) == 0)
					Gfo_usr_dlg_.Instance.Prog_many("", "", "loading tmpls: ~{0}", count);
			}
		} finally {rdr.Rls();}

		// load wikitext
		text_db_loader.Load();

		// handle redirects
		int redirect_len = redirect_list.Len();
		for (int i = 0; i < redirect_len; ++i) {
			Xow_page_cache_itm src_itm = (Xow_page_cache_itm)redirect_list.Get_at(i);
			Xow_page_cache_itm trg_itm = (Xow_page_cache_itm)page_regy.Get_by(src_itm.Redirect_id());
			if (trg_itm == null) {
				Gfo_usr_dlg_.Instance.Prog_many("", "", "missing redirect for tmpl: ~{0}", src_itm.Ttl().Full_db());
				continue;
			}
			src_itm.Set_redirect_bry(trg_itm.Wtxt__direct());
		}
	}
}
