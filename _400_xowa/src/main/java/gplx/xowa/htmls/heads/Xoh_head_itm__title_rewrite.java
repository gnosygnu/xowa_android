package gplx.xowa.htmls.heads; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
import gplx.xowa.guis.*;
import gplx.xowa.apps.apis.xowa.html.modules.*;
public class Xoh_head_itm__title_rewrite extends Xoh_head_itm__base {
	@Override public byte[] Key() {return Xoh_head_itm_.Key__title_rewrite;}
	@Override public int Flags() {return Flag__js_tail_script;}
	@Override public void Write_js_tail_script(Xoae_app app, Xowe_wiki wiki, Xoae_page page, Xoh_head_wtr wtr) {
		wtr.Write_js_tail_load_lib(app.Fsys_mgr().Bin_any_dir().GenSubFil_nest("xowa", "html", "res", "src", "gadgets", "titleRewrite", "titleRewrite.js"));
	}
}
