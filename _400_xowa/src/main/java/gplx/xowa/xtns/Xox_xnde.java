package gplx.xowa.xtns; import gplx.*; import gplx.xowa.*;
import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.htmls.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.xndes.*;
public interface Xox_xnde {
	void Xtn_parse(Xowe_wiki wiki, Xop_ctx ctx, Xop_root_tkn root, byte[] src, Xop_xnde_tkn xnde);
	void Xtn_write(Bry_bfr bfr, Xoae_app app, Xop_ctx ctx, Xoh_html_wtr html_wtr, Xoh_wtr_ctx hctx, Xoae_page wpg, Xop_xnde_tkn xnde, byte[] src);
}
