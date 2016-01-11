package gplx.xowa.wikis.ctgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.core.brys.fmtrs.*;
import gplx.xowa.htmls.core.htmls.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.msgs.*;
class Xoctg_fmtr_grp implements gplx.core.brys.Bfr_arg {
	public void Init_from_all(Xowe_wiki wiki, Xol_lang_itm lang, Xoh_wtr_ctx hctx, Xoctg_view_ctg ctg, Xoctg_fmtr_all mgr, Xoctg_view_grp itms_list) {
		this.wiki = wiki; this.mgr = mgr; this.itms_fmtr = mgr.Fmtr_itm(); this.itms_list = itms_list; len = itms_list.Len();
		itms_fmtr.Init_from_all(wiki, lang, hctx, ctg, mgr, itms_list, len);
	}	private Xowe_wiki wiki; Xoctg_fmtr_itm itms_fmtr; Xoctg_view_grp itms_list; int len; Xoctg_fmtr_all mgr;
	public void Bfr_arg__add(Bry_bfr bfr) {
		if (mgr.Grps_enabled()) {
			if (len == 0) return;
			int cur_idx = itms_list.Bgn(); int col_bgn = cur_idx;
			byte[] ttl_char_0 = Bry_.Empty; int col_idx = -1; boolean col_bgn_needed = true;
			while (cur_idx < len) {
				Xoctg_view_itm itm = itms_list.Itms()[cur_idx];
				byte[] itm_sortkey = itm.Sort_key();
				byte[] ttl_char_0_new = gplx.core.intls.Utf8_.Get_char_at_pos_as_bry(itm_sortkey, 0);
				byte[] grp_name = ttl_char_0_new;
				if (Bry_.Eq(ttl_char_0, ttl_char_0_new)) {
					grp_name = Bry_.Add(ttl_char_0, Byte_ascii.Space_bry, wiki.Msg_mgr().Val_by_id(Xol_msg_itm_.Id_list_continues));
				}
				ttl_char_0 = ttl_char_0_new;
				if (col_bgn_needed) {
					col_bgn = cur_idx;
					itms_fmtr.Col_idx_(++col_idx, col_bgn);
					html_col_bgn.Bld_bfr_many(bfr, 100 / Xoctg_html_mgr.Cols_max);
				}
				itms_fmtr.Init_from_grp(ttl_char_0, cur_idx);
				html_tbl.Bld_bfr_many(bfr, grp_name, itms_fmtr);
				cur_idx = itms_fmtr.Grp_end_idx(); col_bgn_needed = itms_fmtr.Grp_end_at_col();
				if (col_bgn_needed)
					html_col_end.Bld_bfr_many(bfr);
			}
		}
		else {
			itms_fmtr.Col_idx_(0, len);
			itms_fmtr.Init_from_grp(Bry_.Empty, itms_list.Bgn());
			html_none.Bld_bfr_many(bfr, itms_fmtr);
		}
	}
	Bry_fmtr html_none = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
		(	""
		,	"~{itms}"
		), 	"itms"
		);
	Bry_fmtr html_tbl = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
		(	""
		,	"          <h3>~{grp_name}</h3>"
		,	"          <ul>~{itms}"
		,	"          </ul>"
		), 	"grp_name", "itms"
		);
	Bry_fmtr html_col_bgn = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
		(	""
		,	"        <td style=\"width: ~{width}%;\">"
		), 	"width"
		);
	Bry_fmtr html_col_end = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
		(	""
		,	"        </td>"
		));
	public static int Calc_col_max(int cols_total, int len, int col_idx) {
		if (len == 0) return 0;
		int col_max = ((len - 1) / cols_total) + 1;	// NOTE: example is easiest explanation; EX: 4, 5, 6 should have max of 2, so (a) subtract 1; (b) divide by 3; (c) add 1
		return col_idx <= ((len - 1) % cols_total) ? col_max : col_max - 1; // NOTE: example is easiest explanation; EX: 4=2,1,1; 5=2,2,1; 6=2,2,2
	}
}
