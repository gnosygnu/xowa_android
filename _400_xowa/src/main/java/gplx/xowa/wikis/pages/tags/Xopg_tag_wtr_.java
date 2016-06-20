package gplx.xowa.wikis.pages.tags; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.pages.*;
public class Xopg_tag_wtr_{
	public static void Add__xocss(Xopg_tag_mgr head_tags, Io_url http_root) {
		Io_url css_dir = http_root.GenSubDir_nest("bin", "any", "xowa", "html", "res", "src", "xowa", "xocss", "core");
		head_tags.Add(Xopg_tag_itm.New_css_file(css_dir.GenSubFil_nest("xocss_core-0.0.1.css")));
		head_tags.Add(Xopg_tag_itm.New_css_file(css_dir.GenSubFil_nest("xoimg_core-0.0.1.css")));
	}
	public static void Add__xohelp(Xopg_tag_mgr head_tags, Io_url http_root) {
		Io_url css_dir = http_root.GenSubDir_nest("bin", "any", "xowa", "html", "res", "src", "xowa", "xocss", "help");
		head_tags.Add(Xopg_tag_itm.New_css_file(css_dir.GenSubFil_nest("xohelp-0.0.1.css")));
		head_tags.Add(Xopg_tag_itm.New_js_file(css_dir.GenSubFil_nest("xohelp-0.0.1.js")));
	}
	public static void Add__mustache(Xopg_tag_mgr head_tags, Io_url http_root) {
		head_tags.Add(Xopg_tag_itm.New_js_file(http_root.GenSubFil_nest("bin", "any", "xowa", "html", "res", "lib", "mustache", "mustache-2.2.1.js")));
	}
	public static void Add__jquery(Xopg_tag_mgr head_tags, Io_url http_root) {
		head_tags.Add(Xopg_tag_itm.New_js_file(http_root.GenSubFil_nest("bin", "any", "xowa", "html", "res", "lib", "jquery", "jquery-1.11.3.js")));
	}
	public static void Add__notifyjs(Xopg_tag_mgr head_tags, Io_url http_root) {
		head_tags.Add(Xopg_tag_itm.New_js_file(http_root.GenSubFil_nest("bin", "any", "xowa", "html", "res", "lib", "notifyjs", "notifyjs-0.3.1.js")));
	}
}
