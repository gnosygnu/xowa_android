package gplx.xowa.htmls.core.hzips; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*;
import gplx.xowa.htmls.core.wkrs.*; import gplx.xowa.htmls.core.wkrs.tocs.*;
class Xoh_bfr_mgr {
	private Xoh_page hpg;
	private Bry_bfr html_bfr;
	private Bry_bfr bfr_0 = Bry_bfr_.New(), bfr_1 = Bry_bfr_.New();
	private boolean toc_mode_is_pgbnr;
	public Bry_bfr Init(Xoh_page hpg, Bry_bfr html_bfr) {
		this.hpg = hpg; this.html_bfr = html_bfr;
		this.toc_mode_is_pgbnr = false;
		return bfr_0;
	}
	public Bry_bfr Split_by_toc(Xoh_data_itm data_itm) {
		hpg.Hdump_mgr().Toc_wtr().Exists_y_();
		this.toc_mode_is_pgbnr = ((Xoh_toc_data)data_itm).Toc_mode() == Xoh_toc_data.Toc_mode__pgbnr;
		if (toc_mode_is_pgbnr)
			hpg.Html_data().Head_mgr().Itm__pgbnr().Enabled_y_();
		return bfr_1;
	}
	public void Commit() {
		html_bfr.Add_bfr_and_clear(bfr_0);

		// calc toc by iterating sections
		if (hpg.Hdump_mgr().Toc_wtr().Exists())
			html_bfr.Add(hpg.Hdump_mgr().Toc_wtr().To_html(toc_mode_is_pgbnr));

		html_bfr.Add_bfr_and_clear(bfr_1);
	}
}
