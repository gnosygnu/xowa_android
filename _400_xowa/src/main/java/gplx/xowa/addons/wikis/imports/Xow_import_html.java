package gplx.xowa.addons.wikis.imports; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*;
import gplx.xowa.specials.*; import gplx.langs.mustaches.*; import gplx.xowa.wikis.pages.*; import gplx.xowa.wikis.pages.tags.*;
import gplx.core.ios.*; 
class Xow_import_html extends Xow_special_wtr__base {
	private final    Io_url owner_url;
	public Xow_import_html(Io_url owner_url) {this.owner_url = owner_url;}
	@Override protected Io_url Get_addon_dir(Xoa_app app)			{return app.Fsys_mgr().Http_root().GenSubDir_nest("bin", "any", "xowa", "addon", "wiki", "import");}
	@Override protected Io_url Get_mustache_fil(Io_url addon_dir)	{return addon_dir.GenSubFil_nest("bin", "xow_import.mustache.html");}
	@Override protected Mustache_doc_itm Bld_mustache_root(Xoa_app app) {
		IoItmDir owner_dir = Io_mgr.Instance.QueryDir_args(owner_url).DirInclude_(true).ExecAsDir();
		return Xow_import_doc.New(owner_dir);
	}
	@Override protected void Bld_tags(Xoa_app app, Io_url addon_dir, Xopage_html_data page_data) {
		Xopg_tag_mgr head_tags = page_data.Head_tags();
		Xopg_tag_wtr_.Add__xocss	(head_tags, app.Fsys_mgr().Http_root());
		Xopg_tag_wtr_.Add__xohelp	(head_tags, app.Fsys_mgr().Http_root());
		head_tags.Add(Xopg_tag_itm.New_css_file(addon_dir.GenSubFil_nest("bin", "xow_import.css")));
	}
}
