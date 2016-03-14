package gplx.xowa.addons.searchs.searchers.rslts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.xowa.wikis.data.tbls.*;
public class Srch_rslt_row_ {
	public static boolean Redirect_handle(Srch_rslt_list rslts, Srch_rslt_row cur_row) {
		int redirect_id = cur_row.Page_redirect_id;
		if (redirect_id != Srch_rslt_row.Page_redirect_id_null) {
			Srch_rslt_row trg_row = rslts.Ids__get(cur_row.Page_redirect_id);
			if (trg_row == null) {
				rslts.Ids__add(redirect_id, cur_row);
			}
			else	// ignore; page is redirect to existing item
				return false;
		}
		if (rslts.Ids__get(cur_row.Page_id) != null) return false;
		return true;
	}
	public static void Format_ttl(Srch_ctx ctx, Xowd_page_tbl page_tbl, Xowd_page_itm tmp_page_itm, Srch_rslt_row row) {
		// highlight row
		try {row.Page_ttl_display = ctx.Highlight_mgr.Highlight(Xoa_ttl.Replace_unders(row.Page_ttl_wo_ns));}
		catch (Exception e) {Xoa_app_.Usr_dlg().Warn_many("", "", "highlight failed; ttl=~{0} err=~{1}", row.Page_ttl_wo_ns, Err_.Message_gplx_log(e));}

		int redirect_id = row.Page_redirect_id; if (redirect_id == -1) return;
		if (!page_tbl.Select_by_id(tmp_page_itm, redirect_id)) {Xoa_app_.Usr_dlg().Warn_many("", "", "page not found for redirect_id; redirect_id=~{0}", redirect_id); return;}

		// format page ttl; EX: "Page original <-- Page redirect"
		Bry_bfr tmp_bfr = ctx.Tmp_bfr;
		tmp_bfr.Add(row.Page_ttl_display);
		tmp_bfr.Add(Bry__redirect_dlm);
		tmp_bfr.Add(Xoa_ttl.Replace_unders(tmp_page_itm.Ttl_page_db()));
		row.Page_ttl_display = tmp_bfr.To_bry_and_clear();
	}	private static final byte[] Bry__redirect_dlm = Bry_.new_a7("&nbsp;&#8594;&nbsp;");	// 8592; 8594
}
