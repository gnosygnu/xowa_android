package gplx.xowa.htmls.heads; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
import gplx.xowa.guis.*;
public class Xoh_head_itm__mathjax extends Xoh_head_itm__base {
	@Override public byte[] Key() {return Xoh_head_itm_.Key__mathjax;}
	@Override public int Flags() {return Flag__js_include;}
	@Override public void Write_js_include(Xoae_app app, Xowe_wiki wiki, Xoae_page page, Xoh_head_wtr wtr) {
		if (Url_mathjax == null) Url_mathjax = app.Fsys_mgr().Bin_any_dir().GenSubFil_nest("xowa", "xtns", "Math", "modules", "mathjax", "xowa_mathjax.js").To_http_file_bry();
		wtr.Write_js_include(Url_mathjax);
	}
	private static byte[] Url_mathjax;
}
