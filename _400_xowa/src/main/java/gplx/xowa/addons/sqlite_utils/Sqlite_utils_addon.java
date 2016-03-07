package gplx.xowa.addons.sqlite_utils; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*;
public class Sqlite_utils_addon implements Xoax_addon_itm {
	public Sqlite_utils_addon(Xow_wiki wiki) {
	}
	public byte[]				Addon__key() {return Key_const;} public static final byte[] Key_const = Bry_.new_a7("xowa.sqlite_utils");
	public static Sqlite_utils_addon Get(Xow_wiki wiki) {
		Sqlite_utils_addon rv = (Sqlite_utils_addon)wiki.Addon_mgr().Itms__get_or_null(Key_const);
		if (rv == null) {
			rv = new Sqlite_utils_addon(wiki);
			wiki.Addon_mgr().Itms__add(rv);
		}
		return rv;
	}
}
