package gplx.xowa.drds.pages; import gplx.*; import gplx.xowa.*; import gplx.xowa.drds.*;
import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.htmls.*; import gplx.xowa.htmls.sections.*;
import gplx.core.net.*; import gplx.xowa.specials.xowa.file_browsers.*;
public class Xod_page_mgr {
	public Xod_page_itm Get_page(Xow_wiki wiki, Xoa_url page_url) {
		Xod_page_itm rv = new Xod_page_itm();

		// load meta info like page_id, modified, etc
		Xoa_ttl ttl = wiki.Ttl_parse(page_url.Page_bry());
		if (ttl.Ns().Id_is_special()) return Load_special(rv, wiki, ttl, page_url.Qargs_ary());
		Xowd_page_itm dbpg = new Xowd_page_itm();
		wiki.Data__core_mgr().Tbl__page().Select_by_ttl(dbpg, ttl.Ns(), ttl.Page_db());
		rv.Init_by_dbpg(ttl, dbpg);

		// load page data
		Xoh_page hpg = new Xoh_page();
		hpg.Init(wiki, Xoa_url.new_(wiki.Domain_bry(), ttl.Page_db()), ttl, 1);
		rv.Init_by_hpg(hpg);
		wiki.Html__hdump_mgr().Load_mgr().Load(hpg, ttl);
		Load_sections(rv, hpg);
		return rv;
	}
	private void Load_sections(Xod_page_itm rv, Xoh_page hpg) {
		Xoh_section_mgr section_mgr = hpg.Section_mgr();
		int len = section_mgr.Len();
		for (int i = 0; i < len; ++i) {
			Xoh_section_itm itm = section_mgr.Get_at(i);
			rv.Section_list().Add(itm);
		}
	}
	private Xod_page_itm Load_special(Xod_page_itm rv, Xow_wiki wiki, Xoa_ttl ttl, Gfo_qarg_itm[] qargs) {
		Xosp_fbrow_rslt rslt = Xosp_fbrow_special.Gen(qargs, wiki.App().Wiki_mgri());
		rv.Init(-1, -1, String_.new_u8(ttl.Page_txt()), String_.new_u8(ttl.Page_db()), null, null, DateAdp_.Now().XtoStr_fmt_iso_8561(), false, false, false, 0, "", "", "");
		rv.Init_by_hpg(new Xoh_page());
		Xoh_section_itm section = new Xoh_section_itm(1, 1, Bry_.Empty, Bry_.Empty);
		section.Content_(rslt.Html_body());
		rv.Section_list().Add(section);
		return rv;
	}
}
