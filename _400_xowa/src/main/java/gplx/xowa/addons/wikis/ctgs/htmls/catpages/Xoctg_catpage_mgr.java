package gplx.xowa.addons.wikis.ctgs.htmls.catpages; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.ctgs.*; import gplx.xowa.addons.wikis.ctgs.htmls.*;
import gplx.xowa.wikis.dbs.*; import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.msgs.*; import gplx.xowa.htmls.core.htmls.*;
import gplx.xowa.wikis.nss.*;
import gplx.xowa.addons.wikis.ctgs.htmls.catpages.doms.*; import gplx.xowa.addons.wikis.ctgs.htmls.catpages.fmts.*; import gplx.xowa.addons.wikis.ctgs.htmls.catpages.utls.*; import gplx.xowa.addons.wikis.ctgs.htmls.catpages.urls.*;
public class Xoctg_catpage_mgr {
	private final    Hash_adp_bry cache = Hash_adp_bry.cs();
	private final    Xoctg_catpage_loader loader = new Xoctg_catpage_loader();
	private final    Xoctg_fmt_grp fmt_subcs = Xoctg_fmt_grp.New__subc(), fmt_pages = Xoctg_fmt_grp.New__page(), fmt_files = Xoctg_fmt_grp.New__file();
	public int Grp_max() {return grp_max;} private int grp_max = Grp_max_dflt;
	public Xoctg_fmt_grp Fmt(byte tid) {
		switch (tid) {
			case Xoa_ctg_mgr.Tid__subc: return fmt_subcs;
			case Xoa_ctg_mgr.Tid__page: return fmt_pages;
			case Xoa_ctg_mgr.Tid__file: return fmt_files;
			default: throw Err_.new_unhandled(tid);
		}
	}
	public void Free_mem_all() {cache.Clear();}
	public Xoctg_catpage_ctg Get_or_load_or_null(Xow_wiki wiki, Xoa_ttl cat_ttl) {
		// load categories from cat dbs; exit if not found
		Xoctg_catpage_ctg ctg = (Xoctg_catpage_ctg)cache.Get_by(cat_ttl.Full_db());
		if (ctg == null) {
			if (gplx.core.envs.Env_.Mode_testing()) return null;	// needed for dpl test
			ctg = loader.Load_by_ttl_or_null(wiki, cat_ttl);
			if (ctg == null) return null;	// not in cache or db; exit
			cache.Add(cat_ttl.Full_db(), ctg);
		}
		return ctg;
	}
	public void Write_catpage(Bry_bfr bfr, Xow_wiki wiki, Xoa_page page, Xoh_wtr_ctx hctx) {
		try	{
			// load categories from cat dbs; exit if not found
			Xoctg_catpage_ctg ctg = Get_or_load_or_null(wiki, page.Ttl());
			if (ctg == null) return;

			// filter subs; need for large categories like de.w:Category:Mann
			Xoctg_catpage_url catpage_url = Xoctg_catpage_url_parser.Parse(page.Url());
			Xoctg_catpage_filter.Filter(grp_max, catpage_url, ctg);

			// write html
			Xol_lang_itm lang = page.Lang();
			fmt_subcs.Write_catpage_grp(bfr, wiki, lang, ctg, grp_max);
			fmt_pages.Write_catpage_grp(bfr, wiki, lang, ctg, grp_max);
			fmt_files.Write_catpage_grp(bfr, wiki, lang, ctg, grp_max);
		}
		catch (Exception e) {
			Xoa_app_.Usr_dlg().Warn_many("", "", "failed to generate category: title=~{0} err=~{1}", page.Url_bry_safe(), Err_.Message_gplx_log(e));
		}
	}
	public void Cache__add(byte[] ttl, Xoctg_catpage_ctg ctg) {
		cache.Del(ttl);
		cache.Add(ttl, ctg);
	}
	public void Grp_max_(int v) {grp_max = v;}	// TEST:
	public static int Grp_max_dflt = 200;
}
