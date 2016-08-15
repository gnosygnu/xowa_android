package gplx.xowa.xtns.lst; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*; import gplx.xowa.parsers.hdrs.*;
import gplx.xowa.wikis.nss.*; import gplx.xowa.wikis.pages.wtxts.*;
public class Lst_pfunc_itm {
	public byte[]				Itm_src()	{return itm_src;} private final    byte[] itm_src;
	public Xop_ctx				Itm_ctx()	{return itm_ctx;} private final    Xop_ctx itm_ctx;
	public Lst_section_nde_mgr	Sec_mgr()	{return sec_mgr;} private final    Lst_section_nde_mgr sec_mgr;
	public Xopg_toc_mgr			Toc_mgr()	{return toc_mgr;} private final    Xopg_toc_mgr toc_mgr;

	public Lst_pfunc_itm(byte[] itm_src, Xop_ctx itm_ctx, Lst_section_nde_mgr sec_mgr, Xopg_toc_mgr toc_mgr) {
		this.itm_src = itm_src; this.itm_ctx = itm_ctx;
		this.sec_mgr = sec_mgr; this.toc_mgr = toc_mgr;
	}
	public static Lst_pfunc_itm New_or_null(Xop_ctx ctx, byte[] ttl_bry) {
		Xowe_wiki wiki = ctx.Wiki();
		Xoa_ttl ttl = wiki.Ttl_parse(ttl_bry); if (ttl == null) return null;		// EX:{{#lst:<>}} -> ""
		byte[] itm_src;
		Xop_ctx itm_ctx;
		Xot_defn_tmpl tmpl = (Xot_defn_tmpl)wiki.Cache_mgr().Lst_cache().Get_by_key(ttl_bry);
		if (tmpl == null) {	// cache transclusions to prevent multiple parsings; DATE:2014-02-22
			itm_ctx = Xop_ctx.New__sub__reuse_page(ctx).Ref_ignore_(true);
			byte[] src_page_bry = wiki.Cache_mgr().Page_cache().Get_or_load_as_src(ttl);
			if (src_page_bry == null) return null; // {{#lst:missing}} -> ""
			Xoae_page page = ctx.Page();
			if (!page.Tmpl_stack_add(ttl.Full_db())) return null;
			tmpl = wiki.Parser_mgr().Main().Parse_text_to_defn_obj(itm_ctx, itm_ctx.Tkn_mkr(), ttl.Ns(), ttl_bry, src_page_bry);	// NOTE: parse as tmpl to ignore <noinclude>
			Bry_bfr tmp_bfr = wiki.Utl__bfr_mkr().Get_m001();
			page.Tmpl_stack_del();									// take template off stack; evaluate will never recurse, and will fail if ttl is still on stack; DATE:2014-03-10
			tmpl.Tmpl_evaluate(itm_ctx, Xot_invk_temp.Page_is_caller, tmp_bfr);
			itm_src = tmp_bfr.To_bry_and_rls();
			if (!page.Tmpl_stack_add(ttl.Full_db())) return null;	// put template back on stack; 
			Xop_root_tkn root = wiki.Parser_mgr().Main().Parse_text_to_wdom(itm_ctx, itm_src, true);	// NOTE: pass itm_ctx as old_ctx b/c entire document will be parsed, and references outside the section should be ignored;
			itm_src = root.Data_mid();	// NOTE: must set src to root.Data_mid() which is result of parse; else <nowiki> will break text; DATE:2013-07-11
			wiki.Cache_mgr().Lst_cache().Add(tmpl, Xow_ns_case_.Tid__all);
			page.Tmpl_stack_del();
			tmpl.Data_mid_(itm_src);
			tmpl.Ctx_(itm_ctx);
		}
		else {
			itm_src = tmpl.Data_mid();
			itm_ctx = tmpl.Ctx();
		}
		return new Lst_pfunc_itm(itm_src, itm_ctx, null, null);
	}
	public static Lst_pfunc_itm New_hdr_or_null(Xop_ctx ctx, byte[] ttl_bry) {
		Xowe_wiki wiki = ctx.Wiki();
		Xoa_ttl ttl = wiki.Ttl_parse(ttl_bry); if (ttl == null) return null;		// EX:{{#lst:<>}} -> ""
		Lst_pfunc_itm rv = (Lst_pfunc_itm)wiki.Cache_mgr().Lst_cache2().Get_by_bry(ttl_bry);
		if (rv == null) {	// cache transclusions to prevent multiple parsings; DATE:2014-02-22
			// get sub_src;
			Xop_ctx sub_ctx = Xop_ctx.New__top(wiki).Ref_ignore_(true);
			byte[] sub_src = wiki.Cache_mgr().Page_cache().Get_or_load_as_src(ttl); if (sub_src == null) return null; // {{#lst:missing}} -> ""

			// parse page; note adding to stack to prevent circular recursions
			Xoae_page page = ctx.Page();
			if (!page.Tmpl_stack_add(ttl.Full_db())) return null;
			Xop_root_tkn root = wiki.Parser_mgr().Main().Parse_text_to_wdom(sub_ctx, sub_src, true);	// NOTE: parse as defn will drop <onlyinclude>; PAGE:en.w:10s_BC; DATE:2016-08-13
			page.Tmpl_stack_del();

			// HACK: parse page again b/c transcluded {{#lst}} will add their hdrs to toc_mgr; PAGE:en.w:Germany_national_football_team DATE:2016-08-13
			sub_src = root.Data_mid();	// NOTE: must set src to root.Data_mid() which is result of parse; else <nowiki> will break text; DATE:2013-07-11
			sub_ctx.Page().Wtxt().Toc().Clear();
			root = wiki.Parser_mgr().Main().Parse_text_to_wdom(sub_ctx, sub_src, true);
			sub_src = root.Data_mid();	// NOTE: must call root.Data_mid() again b/c previous src may have nowiki which will get removed in 2nd pass; see TEST:Tmpl_w_nowiki; DATE:2016-08-13

			// add to cache
			rv = new Lst_pfunc_itm
			( sub_src
			, null, Clone(sub_ctx.Lst_section_mgr()), Clone(sub_ctx.Page().Wtxt().Toc(), sub_src, ttl_bry));
			wiki.Cache_mgr().Lst_cache2().Add(ttl_bry, rv);
		}
		return rv;
	}
	private static Xopg_toc_mgr Clone(Xopg_toc_mgr prime, byte[] src, byte[] ttl_bry) {
		Xopg_toc_mgr rv = new Xopg_toc_mgr();
		int len = prime.Len();
		int src_len = src.length;
		for (int i = 0; i < len; ++i) {
			Xop_hdr_tkn hdr = prime.Get_at(i);
			if (hdr.Src_bgn() > src_len || hdr.Src_end() > src_len)	// DEBUG:handle random cases where hdr is out of bounds of source; PAGE:en.w:Germany_national_football_team DATE:2016-08-13
				Gfo_usr_dlg_.Instance.Warn_many("", "", "lsth:headers are not in bounds of source; ttl=~{0} src=~{1} hdr_bgn=~{2} hdr_end=~{3}", ttl_bry, src_len, hdr.Src_bgn(), hdr.Src_end());
			rv.Add(hdr);
		}
		return rv;
	}
	private static Lst_section_nde_mgr Clone(Lst_section_nde_mgr src) {
		Lst_section_nde_mgr rv = new Lst_section_nde_mgr();
		int len = src.Len();
		for (int i = 0; i < len; ++i)
			rv.Add(src.Get_at(i));
		return rv;
	}
}
