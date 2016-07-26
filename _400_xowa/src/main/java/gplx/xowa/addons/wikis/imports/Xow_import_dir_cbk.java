package gplx.xowa.addons.wikis.imports; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*;
public interface Xow_import_dir_cbk {
	String Key();
	void Cbk__dir_selected(Xow_wiki wiki, Xoa_page page, String path);
}
