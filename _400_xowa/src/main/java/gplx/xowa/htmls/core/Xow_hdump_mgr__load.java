package gplx.xowa.htmls.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
import gplx.core.ios.*;
import gplx.xowa.htmls.heads.*; import gplx.xowa.htmls.core.makes.*; import gplx.xowa.htmls.core.hzips.*;
import gplx.xowa.wikis.data.*; import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.wikis.pages.*; import gplx.xowa.wikis.pages.skins.*;
public class Xow_hdump_mgr__load {
	private final Xow_wiki wiki; private final Xoh_hzip_mgr hzip_mgr; private final Io_stream_zip_mgr zip_mgr;
	private final Xoh_page tmp_hpg; private final Bry_bfr tmp_bfr; private final Xowd_page_itm tmp_dbpg = new Xowd_page_itm();		
	private Hash_adp_bry page_overrides;
	public Xow_hdump_mgr__load(Xow_wiki wiki, Xoh_hzip_mgr hzip_mgr, Io_stream_zip_mgr zip_mgr, Xoh_page tmp_hpg, Bry_bfr tmp_bfr) {
		this.wiki = wiki; this.hzip_mgr = hzip_mgr; this.zip_mgr = zip_mgr; this.tmp_hpg = tmp_hpg; this.tmp_bfr = tmp_bfr;
		this.make_mgr = new Xoh_make_mgr(wiki.App().Usr_dlg(), wiki.App().Fsys_mgr(), gplx.langs.htmls.encoders.Gfo_url_encoder_.Fsys_lnx, wiki.Domain_bry());			
	}
	public Xoh_make_mgr Make_mgr() {return make_mgr;} private final Xoh_make_mgr make_mgr;
	public void Load(Xoae_page wpg) {
		Load(tmp_hpg, wpg.Ttl());
		wpg.Hdump_data().Body_(tmp_hpg.Body());
		// wpg.Root_(new Xop_root_tkn());
		Fill_page(wpg, tmp_hpg);
	}
	public boolean Load(Xoh_page hpg, Xoa_ttl ttl) {
		synchronized (tmp_dbpg) {
			if (page_overrides == null) page_overrides = Init_page_overrides(wiki);
			if (!Load__dbpg(wiki, tmp_dbpg.Clear(), hpg, ttl)) return Load__fail(hpg);		// nothing in "page" table
			Xowd_db_file html_db = wiki.Data__core_mgr().Dbs__get_at(tmp_dbpg.Html_db_id());
			hpg.Init(hpg.Wiki(), hpg.Url(), ttl, tmp_dbpg.Id());
			if (!html_db.Tbl__html().Select_by_page(hpg)) return Load__fail(hpg);			// nothing in "html_page" table
			byte[] src = Parse(hpg, hpg.Body_zip_tid(), hpg.Body_hzip_tid(), hpg.Body());
			hpg.Body_(src);
			return true;
		}
	}
	public byte[] Decode_as_bry(Bry_bfr bfr, Xoh_page hpg, byte[] src, boolean mode_is_diff) {hzip_mgr.Hctx().Mode_is_diff_(mode_is_diff); hzip_mgr.Decode(bfr, wiki, hpg, src); return bfr.To_bry_and_clear();}
	private byte[] Parse(Xoh_page hpg, int zip_tid, int hzip_tid, byte[] src) {
		if (zip_tid > gplx.core.ios.Io_stream_.Tid_raw)
			src = zip_mgr.Unzip((byte)zip_tid, src);
		if (hzip_tid == Xoh_hzip_dict_.Hzip__v1) {
			byte[] page_override_src = (byte[])page_overrides.Get_by_bry(hpg.Ttl().Page_db());
			if (page_override_src != null) src = page_override_src;
			hpg.Section_mgr().Add(0, 2, Bry_.Empty, Bry_.Empty).Content_bgn_(0);	// +1 to skip \n
			src = Decode_as_bry(tmp_bfr.Clear(), hpg, src, Bool_.N);
			hpg.Section_mgr().Set_content(hpg.Section_mgr().Len() - 1, src, src.length);
		}
		else
			src = make_mgr.Parse(src, hpg, hpg.Wiki());
		return src;
	}
	private void Fill_page(Xoae_page wpg, Xoh_page hpg) {
		Xopg_html_data html_data = wpg.Html_data();
		html_data.Display_ttl_(tmp_hpg.Display_ttl());
		html_data.Content_sub_(tmp_hpg.Content_sub());			
		html_data.Xtn_skin_mgr().Add(new Xopg_xtn_skin_itm_stub(tmp_hpg.Sidebar_div()));
		Xoh_head_mgr wpg_head = html_data.Head_mgr();
		Xopg_module_mgr hpg_head = hpg.Head_mgr();			
		wpg_head.Itm__mathjax().Enabled_		(hpg_head.Math_exists());
		wpg_head.Itm__popups().Bind_hover_area_	(hpg_head.Imap_exists());
		wpg_head.Itm__gallery().Enabled_		(hpg_head.Gallery_packed_exists());
		wpg_head.Itm__hiero().Enabled_			(hpg_head.Hiero_exists());
	}
	private static boolean Load__fail(Xoh_page hpg) {hpg.Exists_n_(); return false;}
	private static boolean Load__dbpg(Xow_wiki wiki, Xowd_page_itm dbpg, Xoh_page hpg, Xoa_ttl ttl) {
		wiki.Data__core_mgr().Tbl__page().Select_by_ttl(dbpg, ttl.Ns(), ttl.Page_db());
		if (dbpg.Redirect_id() != -1) Load__dbpg__redirects(wiki, dbpg);
		return dbpg.Html_db_id() != -1;
	}
	private static void Load__dbpg__redirects(Xow_wiki wiki, Xowd_page_itm dbpg) {
		int redirect_count = 0;
		while (redirect_count < 5) {
			int redirect_id = dbpg.Redirect_id();
			wiki.Data__core_mgr().Tbl__page().Select_by_id(dbpg, redirect_id);
			if (redirect_id == -1) break;
		}
	}
	private static Hash_adp_bry Init_page_overrides(Xow_wiki wiki) {
		Hash_adp_bry rv = Hash_adp_bry.cs();
		Io_url[] page_urls = Io_mgr.Instance.QueryDir_fils(wiki.Fsys_mgr().Root_dir().GenSubDir_nest("data", "page"));
		int page_urls_len = page_urls.length;
		for (int i = 0; i < page_urls_len; ++i) {
			Io_url page_url = page_urls[i];
			byte[] page_raw = Io_mgr.Instance.LoadFilBry(page_url);
			int page_raw_len = page_raw.length;
			int page_nl = Bry_find_.Find_fwd(page_raw, Byte_ascii.Nl, 0, page_raw_len); if (page_nl == Bry_find_.Not_found) continue;
			byte[] page_ttl_bry = Bry_.Mid(page_raw, 0, page_nl);
			byte[] page_src_bry = Bry_.Mid(page_raw, page_nl + 1, page_raw_len);				
			rv.Add_bry_obj(page_ttl_bry, page_src_bry);
		}
		return rv;
	}
}
