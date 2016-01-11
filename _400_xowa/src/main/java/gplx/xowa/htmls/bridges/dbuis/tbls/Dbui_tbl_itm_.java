package gplx.xowa.htmls.bridges.dbuis.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.bridges.*; import gplx.xowa.htmls.bridges.dbuis.*;
public class Dbui_tbl_itm_ {
	public static int Calc_width(Dbui_tbl_itm tbl) {
		int rv = 40; // 40 for button col
		Dbui_col_itm[] ary = tbl.Cols();
		int len = ary.length;
		for (int i = 0; i < len; ++i) {
			rv += ary[i].Width();
		}
		return rv;
	}
}
