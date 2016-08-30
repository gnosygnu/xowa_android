package gplx.langs.htmls.styles; import gplx.*; import gplx.langs.*; import gplx.langs.htmls.*;
public class Gfh_style_wkr__ary implements Gfh_style_wkr {
	private final    List_adp list = List_adp_.New();		
	public boolean On_atr(byte[] src, int atr_idx, int atr_val_bgn, int atr_val_end, int itm_bgn, int itm_End, int key_bgn, int key_end, int val_bgn, int val_end) {
		byte[] key = Bry_.Mid(src, key_bgn, key_end);
		byte[] val = Bry_.Mid(src, val_bgn, val_end);
		list.Add(new Gfh_style_itm(list.Count(), key, val));
		return true;
	}
	public Gfh_style_itm[] Parse(byte[] src, int src_bgn, int src_end) {
		Gfh_style_parser_.Parse(src, src_bgn, src_end, this);
		return (Gfh_style_itm[])list.To_ary_and_clear(Gfh_style_itm.class);
	}
}
