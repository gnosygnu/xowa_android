package gplx.xowa.addons.sqlite_utils.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.sqlite_utils.*;
class Str_ary_ {
	public static String[][] To_str_ary_ary(String v, String val_dlm, String row_dlm) {// "a|b|c`"
		String[] rows_ary = String_.Split(v, row_dlm);
		int rv_len = rows_ary.length;
		String[][] rv = new String[rv_len][];
		for (int i = 0; i < rv_len; ++i) {
			String row = rows_ary[i];
			String[] vals_ary = String_.Split(row, val_dlm);
			int vals_len = vals_ary.length;
			String[] rv_row = new String[vals_len];
			rv[i] = rv_row;
			for (int j = 0; j < vals_len; ++j)
				rv[i][j] = vals_ary[j];
		}
		return rv;
	}
}
