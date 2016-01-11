package gplx.xowa.parsers.tblws; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.xowa.parsers.xndes.*; import gplx.xowa.parsers.htmls.*;
public class Xop_tblw_td_tkn extends Xop_tkn_itm_base implements Xop_tblw_tkn {
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_tblw_td;}
	public int Tblw_tid() {return Xop_xnde_tag_.Tid_td;}
	public int Atrs_bgn() {return atrs_bgn;} private int atrs_bgn = Xop_tblw_wkr.Atrs_null;
	public int Atrs_end() {return atrs_end;} private int atrs_end = -1;
	public void Atrs_rng_set(int bgn, int end) {this.atrs_bgn = bgn; this.atrs_end = end;}
	public Mwh_atr_itm[] Atrs_ary() {return atrs_ary;} public Xop_tblw_tkn Atrs_ary_as_tblw_(Mwh_atr_itm[] v) {atrs_ary = v; return this;} private Mwh_atr_itm[] atrs_ary;
	public boolean Tblw_xml() {return tblw_xml;} private boolean tblw_xml;
	public int Tblw_subs_len() {return tblw_subs_len;} public void Tblw_subs_len_add_() {++tblw_subs_len;} private int tblw_subs_len;
	public Xop_tblw_td_tkn Subs_add_ary(Xop_tkn_itm... ary) {for (Xop_tkn_itm itm : ary) super.Subs_add(itm); return this;}
	public Xop_tblw_td_tkn(int bgn, int end, boolean tblw_xml) {this.tblw_xml = tblw_xml; this.Tkn_ini_pos(false, bgn, end);}
}
