package gplx.xowa.htmls.heads; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
public class Xoh_head_itm__timeline extends Xoh_head_itm__base {
	@Override public byte[] Key() {return Xoh_head_itm_.Key__timeline;} 
	@Override public int Flags() {return Flag__js_include;}
	@Override public void Write_js_include(Xoae_app app, Xowe_wiki wiki, Xoae_page page, Xoh_head_wtr wtr) {
		if (Url_js  == null) Url_js = app.Fsys_mgr().Bin_any_dir().GenSubFil_nest("xowa", "html", "res", "src", "xowa", "timeline", "timeline.js").To_http_file_bry();
		wtr.Write_js_include(Url_js);
	}
	private static byte[] Url_js;
}
