package gplx.xowa.parsers.htmls; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.xowa.parsers.xndes.*;
public class Mwh_doc_wkr_ {
	public static Hash_adp_bry Nde_regy__mw() {
		Xop_xnde_tag[] ary = Xop_xnde_tag_.Ary;
		int len = ary.length;
		Hash_adp_bry rv = Hash_adp_bry.ci_a7();
		for (int i = 0; i < len; ++i) {
			Xop_xnde_tag itm = ary[i];
			rv.Add(itm.Name_bry(), itm);
		}
		return rv;
	}
}
