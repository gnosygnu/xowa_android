package gplx.core.brys; import gplx.*; import gplx.core.*;
public class Bry_bfr_able_ {
	public static byte[][] To_bry_ary(Bry_bfr tmp_bfr, Bry_bfr_able[] ary) {
		int len = ary.length;
		byte[][] rv = new byte[len][];
		for (int i = 0; i < len; ++i) {
			Bry_bfr_able itm = ary[i];
			if (itm != null) {
				itm.To_bfr(tmp_bfr);
				rv[i] = tmp_bfr.To_bry_and_clear();
			}
		}
		return rv;
	}
	public static byte[] To_bry_or_null(Bry_bfr tmp_bfr, Bry_bfr_able itm) {
		if (itm == null) return null;
		itm.To_bfr(tmp_bfr);
		return tmp_bfr.To_bry_and_clear();
	}
}
