package gplx.xowa.xtns.math; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.htmls.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.logs.*; import gplx.xowa.parsers.xndes.*; import gplx.xowa.parsers.htmls.*;
public class Math_nde implements Xox_xnde {
	public Xop_xnde_tkn Xnde() {throw Err_.new_unimplemented();}
	public void Xatr__set(Xowe_wiki wiki, byte[] src, Mwh_atr_itm xatr, Object xatr_id_obj) {}
	public void Xtn_parse(Xowe_wiki wiki, Xop_ctx ctx, Xop_root_tkn root, byte[] src, Xop_xnde_tkn xnde) {
		Xof_math_mgr math_mgr = wiki.Appe().File_mgr().Math_mgr();
		boolean log_wkr_enabled = Log_wkr != Xop_log_basic_wkr.Null; if (log_wkr_enabled) Log_wkr.Log_end_xnde(ctx.Page(), Xop_log_basic_wkr.Tid_math, src, xnde);
		if (math_mgr.Enabled() && math_mgr.Renderer_is_mathjax())
			ctx.Page().Html_data().Head_mgr().Itm__mathjax().Enabled_y_();
	}
	public void Xtn_write(Bry_bfr bfr, Xoae_app app, Xop_ctx ctx, Xoh_html_wtr html_wtr, Xoh_wtr_ctx hctx, Xop_xnde_tkn xnde, byte[] src) {
		app.File_mgr().Math_mgr().Html_wtr().Write(html_wtr, ctx, hctx, bfr, src, xnde);
	}
	public static Xop_log_basic_wkr Log_wkr = Xop_log_basic_wkr.Null;
}
