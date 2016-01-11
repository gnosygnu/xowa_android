package gplx.xowa.xtns.dynamicPageList; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
class Dpl_redirect {
	public static final byte Tid_exclude = 0, Tid_include = 1, Tid_only = 2, Tid_unknown = Byte_.Max_value_127;
	public static byte Parse(byte[] bry) {
		byte key = Dpl_itm_keys.Parse(bry, Dpl_redirect.Tid_exclude);	// NOTE: exclude is default value.
		switch (key) {
			case Dpl_itm_keys.Key_exclude: 			return Tid_exclude;
			case Dpl_itm_keys.Key_include: 			return Tid_include;
			case Dpl_itm_keys.Key_only: 			return Tid_only;
			default:								throw Err_.new_unhandled(key);
		}
	}
}
