package gplx.xowa.specials.statistics; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*;
import gplx.core.brys.fmtrs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.msgs.*; import gplx.xowa.langs.numbers.*;
import gplx.xowa.wikis.nss.*;
public class Xop_statistics_page implements Xows_page {
	private Xop_statistics_stats_page_grp stats_page = new Xop_statistics_stats_page_grp();
//		private Xop_statistics_stats_wiki_grp stats_wiki = new Xop_statistics_stats_wiki_grp();
	private Xop_statistics_stats_ns_grp stats_ns = new Xop_statistics_stats_ns_grp();
	public Xows_special_meta Special_meta() {return Xows_special_meta_.Itm__statistics;}
	public void Special_gen(Xowe_wiki wiki, Xoae_page page, Xoa_url url, Xoa_ttl ttl) {
		byte[] html = Build_html(wiki);
		page.Html_data().Html_restricted_n_();	// [[Special:]] pages allow all HTML
		page.Data_raw_(html);
	}
	public byte[] Build_html(Xowe_wiki wiki) {
		Bry_bfr tmp_bfr = wiki.Utl__bfr_mkr().Get_m001();
		stats_page.Wiki_(wiki);
//			stats_wiki.Wiki_(wiki);
		stats_ns.Wiki_(wiki);
		fmtr_all.Bld_bfr_many(tmp_bfr, stats_page, stats_ns);
		return tmp_bfr.To_bry_and_rls();
	}
	private Bry_fmtr fmtr_all = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	(	"<div id=\"mw-content-text\">"
	,	"<table class=\"wikitable mw-statistics-table\">~{page_stats}~{ns_stats}"
	,	"</table>"
	,	"</div>"
	), "page_stats", "ns_stats");
}
class Xop_statistics_stats_page_grp implements gplx.core.brys.Bfr_arg {
	public void Wiki_(Xowe_wiki v) {this.wiki = v;} private Xowe_wiki wiki;
	public void Bfr_arg__add(Bry_bfr bfr) {			
		byte[] lbl_header_pages = wiki.Msg_mgr().Val_by_id(Xol_msg_itm_.Id_statistics_header_pages);
		byte[] lbl_articles = wiki.Msg_mgr().Val_by_id(Xol_msg_itm_.Id_statistics_articles);
		byte[] lbl_pages = wiki.Msg_mgr().Val_by_id(Xol_msg_itm_.Id_statistics_pages);
		byte[] lbl_pages_desc = wiki.Msg_mgr().Val_by_id(Xol_msg_itm_.Id_statistics_pages_desc);
		Xol_num_mgr num_mgr = wiki.Lang().Num_mgr();
		fmtr_page.Bld_bfr_many(bfr, lbl_header_pages, lbl_articles, lbl_pages, lbl_pages_desc , num_mgr.Format_num(wiki.Stats().NumArticles()), num_mgr.Format_num(wiki.Stats().NumPages()));
	}
	private Bry_fmtr fmtr_page = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	(	""
	,	"  <tr>"
	,	"    <th colspan=\"2\">~{lbl_header_pages}</th>"
	,	"  </tr>"
	,	"  <tr class=\"mw-statistics-articles\">"
	,	"    <td>~{lbl_articles}</td>"
	,	"    <td class=\"mw-statistics-numbers\" style='text-align:right'>~{page_count_main}</td>"
	,	"  </tr>"
	,	"  <tr class=\"mw-statistics-pages\">"
	,	"    <td>~{lbl_pages}<br /><small class=\"mw-statistic-desc\"> ~{lbl_pages_desc}</small></td>"
	,	"    <td class=\"mw-statistics-numbers\" style='text-align:right'>~{page_count_all}</td>"
	,	"  </tr>"
	), "lbl_header_pages", "lbl_articles", "lbl_pages", "lbl_pages_desc", "page_count_main", "page_count_all");
}
class Xop_statistics_stats_ns_grp implements gplx.core.brys.Bfr_arg {
	private Xop_statistics_stats_ns_itm ns_itm_fmtr = new Xop_statistics_stats_ns_itm();
	public void Wiki_(Xowe_wiki v) {this.wiki = v; ns_itm_fmtr.Wiki_(v);} private Xowe_wiki wiki;
	public void Bfr_arg__add(Bry_bfr bfr) {
		byte[] lbl_header_ns = wiki.Msg_mgr().Val_by_id(Xol_msg_itm_.Id_statistics_header_ns);
		fmtr_ns_grp.Bld_bfr_many(bfr, lbl_header_ns, ns_itm_fmtr);
	}
	private Bry_fmtr fmtr_ns_grp = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	(	""
	,	"  <tr>"
	,	"    <th colspan=\"2\">~{lbl_header_ns}</th>"
	,	"  </tr>~{ns_itms}"
	), "lbl_header_ns", "ns_itms");
}
class Xop_statistics_stats_ns_itm implements gplx.core.brys.Bfr_arg {
	public void Wiki_(Xowe_wiki v) {this.wiki = v;} private Xowe_wiki wiki;
	public void Bfr_arg__add(Bry_bfr bfr) {
		Xow_ns_mgr ns_mgr = wiki.Ns_mgr();
		int ns_len = ns_mgr.Count();
		for (int i = 0; i < ns_len; i++) {
			Xow_ns ns = ns_mgr.Ids_get_at(i);
			if (ns.Is_meta()) continue;
			if (ns.Count() == 0) continue;
			byte[] ns_name = ns.Id_is_main() ? wiki.Msg_mgr().Val_by_id(Xol_msg_itm_.Id_ns_blankns) : ns.Name_ui();
			fmtr_ns_itm.Bld_bfr_many(bfr, ns_name, wiki.Lang().Num_mgr().Format_num(ns.Count()));
		}
	}
	private Bry_fmtr fmtr_ns_itm = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	(	""
	,	"  <tr>"
	,	"    <td>~{ns_name}</td>"
	,	"    <td style='text-align:right'>~{ns_count}</td>"
	,	"  </tr>"
	), "ns_name", "ns_count");
}
class Xop_statistics_stats_wiki_grp implements gplx.core.brys.Bfr_arg {
	public void Wiki_(Xowe_wiki v) {this.wiki = v;} private Xowe_wiki wiki;
	public void Bfr_arg__add(Bry_bfr bfr) {
		fmtr_wiki.Bld_bfr_many(bfr, wiki.Db_mgr().Tid_name(), wiki.Fsys_mgr().Root_dir().Raw(), Byte_.To_str(wiki.Db_mgr().Category_version()), wiki.Maint_mgr().Wiki_dump_date().XtoStr_fmt_iso_8561());
	}
	private Bry_fmtr fmtr_wiki = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	(	""	
	,	"  <tr>"
	,	"    <th colspan=\"2\">Wiki statistics</th>"
	,	"  </tr>"
	,	"  <tr>"
	,	"    <td>Wiki format</td>"
	,	"    <td>~{wiki_format}</td>"
	,	"  </tr>"
	,	"  <tr>"
	,	"    <td>Wiki location</td>"
	,	"    <td>~{wiki_url}</td>"
	,	"  </tr>"
	,	"  <tr>"
	,	"    <td>Category level</td>"
	,	"    <td>~{ctg_version}</td>"
	,	"  </tr>"
	,	"  <tr>"
	,	"    <td>Last page updated on</td>"
	,	"    <td>~{page_modified_max}</td>"
	,	"  </tr>"
	), "wiki_format", "wiki_url", "ctg_version", "page_modified_max");
}
