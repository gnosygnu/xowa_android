package gplx.langs.htmls; import gplx.*; import gplx.langs.*;
public class Gfh_selecter {
	public static Gfh_nde[] Select(byte[] src, Gfh_nde[] ary, Hash_adp_bry hash) {
		List_adp list = List_adp_.new_();
		int xndes_len = ary.length;
		for (int i = 0; i < xndes_len; i++) {
			Gfh_nde hnde = ary[i];
			int[] atrs = hnde.Atrs();
			int atrs_len = atrs.length;
			for (int j = 0; j < atrs_len; j += 5) {
				int atr_key_bgn = atrs[j + 1];
				int atr_key_end = atrs[j + 2];
				if (hash.Get_by_mid(src, atr_key_bgn, atr_key_end) != null) {
					list.Add(hnde);
					break;
				}
			}
		}
		Gfh_nde[] rv = (Gfh_nde[])list.To_ary(Gfh_nde.class);
		list.Clear();
		return rv;
	}
}
