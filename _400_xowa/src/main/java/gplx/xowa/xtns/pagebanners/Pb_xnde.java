package gplx.xowa.xtns.pagebanners; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.htmls.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.logs.*; import gplx.xowa.parsers.xndes.*; import gplx.xowa.parsers.htmls.*;
public class Pb_xnde implements Xox_xnde {
//		private Pb_xtn xtn;
	public void Xatr__set(Xowe_wiki wiki, byte[] src, Mwh_atr_itm xatr, Object xatr_id_obj) {}
	public void Xtn_parse(Xowe_wiki wiki, Xop_ctx ctx, Xop_root_tkn root, byte[] src, Xop_xnde_tkn xnde) {
		ctx.Para().Process_block__xnde(xnde.Tag(), Xop_xnde_tag.Block_bgn);
//			this.xtn = (Pb_xtn)wiki.Xtn_mgr().Get_or_fail(Pb_xtn.Xtn_key_static);
//			xtn.Xtn_init_assert(wiki);
//			ctx.Cur_page().Html_data().Head_mgr().Itm__graph().Enabled_y_();
//			boolean log_wkr_enabled = Log_wkr != Xop_log_basic_wkr.Null; if (log_wkr_enabled) Log_wkr.Log_end_xnde(ctx.Cur_page(), Xop_log_basic_wkr.Tid_graph, src, xnde);
		ctx.Para().Process_block__xnde(xnde.Tag(), Xop_xnde_tag.Block_end);
	}
	public void Xtn_write(Bry_bfr bfr, Xoae_app app, Xop_ctx ctx, Xoh_html_wtr html_wtr, Xoh_wtr_ctx hctx, Xop_xnde_tkn xnde, byte[] src) {
//			bfr.Add(Html__div_bgn);
//			bfr.Add_mid(src, xnde.Tag_open_end(), xnde.Tag_close_bgn());
//			bfr.Add(Html__div_end);
	}
	public static Xop_log_basic_wkr Log_wkr = Xop_log_basic_wkr.Null;
//		private static final byte[]
//		  Html__div_bgn = Bry_.new_a7("<div class='mw-wiki-graph'>\n")
//		, Html__div_end = Bry_.new_a7("</div>\n")
//		;
}
