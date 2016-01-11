package gplx.xowa.htmls.heads; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
import gplx.xowa.guis.*;
public class Xoh_head_itm__gallery_styles extends Xoh_head_itm__base {
	@Override public byte[] Key() {return Xoh_head_itm_.Key__gallery_styles;}
	@Override public int Flags() {return Flag__css_include;}
	@Override public void Write_css_include(Xoae_app app, Xowe_wiki wiki, Xoae_page page, Xoh_head_wtr wtr) {
		if (Url_css == null) Url_css = app.Fsys_mgr().Bin_any_dir().GenSubFil_nest("xowa", "html", "res", "src", "mediawiki.page", "mediawiki.page.gallery.css").To_http_file_bry();
		wtr.Write_css_include(Url_css);
	}
	private static byte[] Url_css;
}
