package gplx.xowa.addons.wikis.pages.syncs.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.pages.*; import gplx.xowa.addons.wikis.pages.syncs.*;
import gplx.xowa.files.downloads.*;
import gplx.xowa.wikis.data.*;
import gplx.xowa.htmls.*; import gplx.langs.htmls.docs.*;
import gplx.xowa.htmls.core.wkrs.*; import gplx.xowa.htmls.core.wkrs.txts.*; import gplx.xowa.htmls.core.hzips.*;
import gplx.xowa.htmls.core.dbs.*;
import gplx.xowa.addons.wikis.pages.syncs.wmapis.*;
import gplx.xowa.addons.wikis.pages.syncs.core.parsers.*;
public class Xosync_update_mgr {
	private final    Xoh_hzip_bfr bfr = new Xoh_hzip_bfr(Io_mgr.Len_mb, Bool_.N, Byte_.Max_value_127);
	private final    Gfh_doc_parser hdoc_parser_mgr;
	private final    Xoh_hdoc_ctx hctx = new Xoh_hdoc_ctx();
	private final    Xosync_hdoc_wtr hdoc_bldr = new Xosync_hdoc_wtr();
	private final    Xosync_hdoc_parser hdoc_parser_wkr;
	private final    Xowd_html_tbl_mgr html_tbl_mgr = new Xowd_html_tbl_mgr();
	public Xosync_update_mgr() {
		hdoc_parser_wkr = new Xosync_hdoc_parser(hdoc_bldr);
		hdoc_parser_mgr = new Gfh_doc_parser(new Xoh_txt_parser(hdoc_bldr), hdoc_parser_wkr);
	}
	public void Init_by_app(Xoa_app app) {
		hctx.Init_by_app(app);
	}
	public void Init_by_page(Xow_wiki wiki, Xoa_page page) {
		hctx.Init_by_page(wiki, page);
		page.Hdump_mgr().Clear();
	}
	public void Update(Xof_download_wkr download_wkr, Xow_wiki wiki, Xoa_ttl page_ttl) {
		Xoh_page hpg = (Xoh_page)hctx.Page();

		// call wmf api
		Xowm_parse_wmf parse_wkr = new Xowm_parse_wmf();
		Xowm_parse_data data = parse_wkr.Get_parse_or_null(download_wkr, wiki, page_ttl);
		if (data == null) return;

		// parse
		Parse(hpg, wiki, hctx.Page__url(), data.Revn_html());

		// get existing html_tbl
		Xow_db_file html_db = html_tbl_mgr.Get_html_db(wiki);
		html_tbl_mgr.Save_html(wiki, html_db, data.Page_id(), data.Revn_id(), hpg.Db().Html().Html_bry());

		// download files
		hpg.Ctor_by_hview(wiki, wiki.Utl__url_parser().Parse(page_ttl.Full_db()), page_ttl, data.Page_id());
		gplx.xowa.files.Xof_file_wkr file_thread = new gplx.xowa.files.Xof_file_wkr
			( wiki.File__orig_mgr(), wiki.File__bin_mgr(), wiki.File__mnt_mgr()
			, wiki.App().User().User_db_mgr().Cache_mgr(), wiki.File__repo_mgr(), gplx.xowa.guis.cbks.js.Xog_js_wkr_.Noop, hpg, hpg.Hdump_mgr().Imgs()
			);
		gplx.core.threads.Gfo_thread_pool thread_pool = new gplx.core.threads.Gfo_thread_pool();
		thread_pool.Add_at_end(file_thread);
		thread_pool.Run();
	}
	public void Parse(Xoh_page hpg, Xow_wiki wiki, byte[] page_url, byte[] src) {
		int src_len = src.length;

		// init_by_page for bldr, parser, hdoc
		hctx.Init_by_page(wiki, hpg);
		hdoc_bldr.Init_by_page(bfr, hpg, hctx, src, 0, src_len);
		hdoc_parser_wkr.Init_by_page(hctx, src, 0, src_len);

		// parse
		hdoc_parser_mgr.Parse(page_url, src, 0, src_len);
		hpg.Db().Html().Html_bry_(bfr.To_bry_and_clear());
	}
}
