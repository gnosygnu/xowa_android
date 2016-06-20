package gplx.xowa.addons.bldrs.centrals; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*;
import gplx.xowa.specials.*; import gplx.langs.mustaches.*; import gplx.xowa.wikis.pages.*; import gplx.xowa.wikis.pages.tags.*;
class Xobc_task_html extends Xow_special_wtr__base {
	@Override protected Io_url Get_addon_dir(Xoa_app app)			{return app.Fsys_mgr().Http_root().GenSubDir_nest("bin", "any", "xowa", "addon", "bldr", "central");}
	@Override protected Io_url Get_mustache_fil(Io_url addon_dir)	{return addon_dir.GenSubFil_nest("bin", "xobc.main.mustache.html");}
	@Override protected Mustache_doc_itm Bld_mustache_root(Xoa_app app) {
		return new Xobc_task_doc(gplx.core.envs.Op_sys.Cur().Tid_is_drd(), "/site/home/wiki/App/Import/Download_Central");
	}
	@Override protected void Bld_tags(Xoa_app app, Io_url addon_dir, Xopage_html_data page_data) {
		Xopg_tag_mgr head_tags = page_data.Head_tags();
		Xopg_alertify_.Add_tags	(head_tags, app.Fsys_mgr().Http_root());
		Xopg_tag_wtr_.Add__xocss	(head_tags, app.Fsys_mgr().Http_root());
		Xopg_tag_wtr_.Add__xohelp	(head_tags, app.Fsys_mgr().Http_root());
		Xopg_tag_wtr_.Add__mustache	(head_tags, app.Fsys_mgr().Http_root());
		Xopg_tag_wtr_.Add__jquery	(head_tags, app.Fsys_mgr().Http_root());
		Xopg_tag_wtr_.Add__notifyjs	(head_tags, app.Fsys_mgr().Http_root());

		head_tags.Add(Xopg_tag_itm.New_css_file(addon_dir.GenSubFil_nest("js", "xo.log.css")));
		head_tags.Add(Xopg_tag_itm.New_css_file(addon_dir.GenSubFil_nest("bin", "xobc.css")));
		head_tags.Add(Xopg_tag_itm.New_htm_frag(addon_dir.GenSubFil_nest("bin", "xobc.row.mustache.html"), "xobc.row"));
		head_tags.Add(Xopg_tag_itm.New_js_file(addon_dir.GenSubFil_nest("js", "xo.log.js")));
		head_tags.Add(Xopg_tag_itm.New_js_file(addon_dir.GenSubFil_nest("js", "xo.app.js")));
		head_tags.Add(Xopg_tag_itm.New_js_file(addon_dir.GenSubFil_nest("js", "xo.app." + (gplx.core.envs.Op_sys.Cur().Tid_is_drd() ? "drd" : "swt") + ".js")));
		head_tags.Add(Xopg_tag_itm.New_js_file(addon_dir.GenSubFil_nest("js", "xo.elem.js")));
		head_tags.Add(Xopg_tag_itm.New_js_file(addon_dir.GenSubFil_nest("js", "xo.tmpl.js")));
		head_tags.Add(Xopg_tag_itm.New_js_file(addon_dir.GenSubFil_nest("js", "xo.notify.js")));
		head_tags.Add(Xopg_tag_itm.New_js_file(addon_dir.GenSubFil_nest("js", "xo.server.js")));
		head_tags.Add(Xopg_tag_itm.New_js_file(addon_dir.GenSubFil_nest("js", "xobc.util.js")));
		head_tags.Add(Xopg_tag_itm.New_js_file(addon_dir.GenSubFil_nest("js", "xobc.js")));
	}
}
