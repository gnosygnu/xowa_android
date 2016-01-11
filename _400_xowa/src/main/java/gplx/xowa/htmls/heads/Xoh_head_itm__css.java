package gplx.xowa.htmls.heads; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
import gplx.xowa.guis.*;
public class Xoh_head_itm__css extends Xoh_head_itm__base {
	@Override public byte[] Key() {return Xoh_head_itm_.Key__css;}
	@Override public int Flags() {return Flag__css_text;}
	@Override public void Write_css_text(Xoae_app app, Xowe_wiki wiki, Xoae_page page, Xoh_head_wtr wtr) {
		wtr.Write_css_style_itm(app.Ctg_mgr().Missing_ctg_cls_css());
		if (app.Html_mgr().Page_mgr().Font_enabled())
			wtr.Write_css_style_itm(app.Html_mgr().Page_mgr().Font_css_bry());
		byte[] css_xtn = app.Gui_mgr().Html_mgr().Css_xtn();
		if (Bry_.Len_gt_0(css_xtn))
			wtr.Write_css_style_itm(css_xtn);
	}
}
