package gplx.xowa.wikis.ctgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.xowa.langs.*; import gplx.xowa.htmls.core.htmls.*;
interface Xoctg_fmtr_itm extends gplx.core.brys.Bfr_arg {
	int Grp_end_idx();
	boolean Grp_end_at_col();
	int Col_idx(); void Col_idx_(int col_idx, int col_bgn);
	void Init_from_all(Xowe_wiki wiki, Xol_lang_itm lang, Xoh_wtr_ctx hctx, Xoctg_view_ctg ctg, Xoctg_fmtr_all mgr, Xoctg_view_grp itms_list, int itms_list_len);
	void Init_from_grp(byte[] grp_ttl, int i);
}
