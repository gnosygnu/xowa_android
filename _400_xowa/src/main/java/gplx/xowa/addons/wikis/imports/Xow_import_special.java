package gplx.xowa.addons.wikis.imports; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*;
import gplx.xowa.specials.*; import gplx.core.net.*; import gplx.core.net.qargs.*; import gplx.xowa.wikis.pages.*;
import gplx.core.ios.*;	
public class Xow_import_special implements Xow_special_page {
	public void Special__gen(Xow_wiki wiki, Xoa_page page, Xoa_url url, Xoa_ttl ttl) {
		Gfo_qarg_mgr url_args = new Gfo_qarg_mgr().Init(url.Qargs_ary());

		// get path
		String owner_str = url_args.Read_str_or_null("path");
		if (owner_str == null) {
			Xopage_html_data.err_("url has unknown path").Apply(page);
			return;
		}

		new Xow_import_html(Io_url_.new_dir_(owner_str)).Bld_page_by_mustache(wiki.App(), page, this);
	}

	Xow_import_special(Xow_special_meta special__meta) {this.special__meta = special__meta;}
	public Xow_special_meta Special__meta()		{return special__meta;} private final    Xow_special_meta special__meta;
	public Xow_special_page Special__clone()	{return this;}
	public static final    Xow_special_page Prototype = new Xow_import_special(Xow_special_meta.New_xo("XowaWikiImport", "Import wiki", "XowaFileBrowser"));
}
