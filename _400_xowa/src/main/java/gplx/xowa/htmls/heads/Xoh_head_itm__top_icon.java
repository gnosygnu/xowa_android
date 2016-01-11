package gplx.xowa.htmls.heads; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
import gplx.xowa.guis.*;
import gplx.xowa.apps.apis.xowa.html.modules.*;
public class Xoh_head_itm__top_icon extends Xoh_head_itm__base {
	@Override public byte[] Key() {return Xoh_head_itm_.Key__top_icon;}
	@Override public int Flags() {return Flag__css_include | Flag__js_tail_script;}
	@Override public void Write_css_include(Xoae_app app, Xowe_wiki wiki, Xoae_page page, Xoh_head_wtr wtr) {
		if (Css_url == null) Css_url = app.Fsys_mgr().Bin_any_dir().GenSubFil_nest("xowa", "html", "res", "src", "gadgets", "top-icon", "top-icon.css").To_http_file_bry();
		wtr.Write_css_include(Css_url);
	}
	@Override public void Write_js_tail_script(Xoae_app app, Xowe_wiki wiki, Xoae_page page, Xoh_head_wtr wtr) {
		wtr.Write_js_tail_load_lib(app.Fsys_mgr().Bin_any_dir().GenSubFil_nest("xowa", "html", "res", "src", "gadgets", "top-icon", "top-icon.js"));
	}
	private static byte[] Css_url;
}
