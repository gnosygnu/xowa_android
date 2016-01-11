package gplx.xowa.xtns; import gplx.*; import gplx.xowa.*;
import gplx.xowa.parsers.htmls.*; import gplx.xowa.parsers.xndes.*;
public class Xox_xnde_ {
	public static Mwh_atr_itm[] Xatr__set(Xowe_wiki wiki, Mwh_atr_itm_owner owner, Hash_adp_bry hash, byte[] src, Xop_xnde_tkn xnde) {
		Mwh_atr_itm[] rv = wiki.Appe().Parser_mgr().Xnde__parse_atrs(src, xnde.Atrs_bgn(), xnde.Atrs_end());
		int len = rv.length;
		for (int i = 0; i < len; ++i) {
			Mwh_atr_itm xatr = rv[i]; if (xatr.Invalid()) continue;
			owner.Xatr__set(wiki, src, xatr, hash.Get_by_bry(xatr.Key_bry()));
		}
		return rv;
	}
}
