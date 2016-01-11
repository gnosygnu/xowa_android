package gplx.xowa.parsers.tblws; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.xowa.parsers.xndes.*; import gplx.xowa.parsers.htmls.*;
public interface Xop_tblw_tkn extends Xop_tkn_itm {
	int Tblw_tid();
	boolean Tblw_xml();
	int Tblw_subs_len(); void Tblw_subs_len_add_();
	int Atrs_bgn();
	int Atrs_end();
	void Atrs_rng_set(int bgn, int end);
	Mwh_atr_itm[] Atrs_ary(); Xop_tblw_tkn Atrs_ary_as_tblw_(Mwh_atr_itm[] v);
}
