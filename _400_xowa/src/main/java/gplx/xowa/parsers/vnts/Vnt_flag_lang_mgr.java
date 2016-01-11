package gplx.xowa.parsers.vnts; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.xowa.langs.vnts.*;
class Vnt_flag_lang_mgr {
	private final Ordered_hash regy = Ordered_hash_.New_bry();
	public int Count() {return regy.Count();}
	public boolean Has(byte[] vnt) {return regy.Has(vnt);}
	public void Clear() {regy.Clear();}
	public void Add(Xol_vnt_itm itm) {regy.Add_if_dupe_use_1st(itm.Key(), itm);}
	public Xol_vnt_itm Get_at(int i) {return (Xol_vnt_itm)regy.Get_at(i);}
	public void To_bfr__dbg(Bry_bfr bfr) {
		int len = regy.Count();
		for (int i = 0; i < len; ++i) {
			Xol_vnt_itm itm = (Xol_vnt_itm)regy.Get_at(i);
			if (bfr.Len_gt_0()) bfr.Add_byte_semic();
			bfr.Add(itm.Key());
		}
	}
}
