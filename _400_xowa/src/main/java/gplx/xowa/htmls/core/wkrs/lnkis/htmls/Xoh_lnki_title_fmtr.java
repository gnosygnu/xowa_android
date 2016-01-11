package gplx.xowa.htmls.core.wkrs.lnkis.htmls; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*; import gplx.xowa.htmls.core.wkrs.lnkis.*;
import gplx.langs.htmls.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.xndes.*; import gplx.xowa.parsers.lnkis.*; import gplx.xowa.parsers.tmpls.*;
public class Xoh_lnki_title_fmtr implements gplx.core.brys.Bfr_arg {
	public Xoh_lnki_title_fmtr Set(byte[] src, Xop_tkn_itm tkn) {this.src = src; this.tkn = tkn; return this;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		Bld_recurse(bfr, tkn);
	}
	public void Bld_recurse(Bry_bfr bfr, Xop_tkn_itm tkn) {
		switch (tkn.Tkn_tid()) {
			case Xop_tkn_itm_.Tid_newLine: case Xop_tkn_itm_.Tid_space: case Xop_tkn_itm_.Tid_txt:	// leaf tkns which will have no subs
				Write_atr_text(bfr, src, tkn.Src_bgn(), tkn.Src_end());
				break;
			case Xop_tkn_itm_.Tid_arg_nde:															// caption tkns have no subs; just a key and a val; recurse val
				Arg_nde_tkn arg_nde = (Arg_nde_tkn)tkn;
				Bld_recurse(bfr, arg_nde.Val_tkn());
				break;
			case Xop_tkn_itm_.Tid_lnki:
				Xop_lnki_tkn tkn_as_lnki = (Xop_lnki_tkn)tkn;
				if (tkn_as_lnki.Caption_exists())
					Bld_recurse(bfr, tkn_as_lnki.Caption_tkn());
				else {
					if (tkn_as_lnki.Ttl() != null) {	// guard against invalid ttls
						byte[] ttl_bry = tkn_as_lnki.Ttl().Page_txt();
						Write_atr_text(bfr, ttl_bry, 0, ttl_bry.length); // handle titles with quotes; PAGE:s.w:Styx_(band) DATE:2015-11-29
					}
				}
				if (tkn_as_lnki.Tail_bgn() != -1)
					bfr.Add_mid(src, tkn_as_lnki.Tail_bgn(), tkn_as_lnki.Tail_end());
				break;
			default:	// all other tkns, just iterate over subs for txt tkns
				if (tkn.Tkn_tid() == Xop_tkn_itm_.Tid_xnde) {
					Xop_xnde_tkn xnde = (Xop_xnde_tkn)tkn; 
					if (xnde.Tag().Id() == Xop_xnde_tag_.Tid_ref) {	// if ref, disable tkn
						gplx.xowa.xtns.cites.Ref_nde ref_xnde = (gplx.xowa.xtns.cites.Ref_nde)xnde.Xnde_xtn();
						ref_xnde.Exists_in_lnki_title_(true);	// ref found during html_title_wkr's generation; mark ref; will be ignored by references_html_wtr later; DATE:2014-03-05
					}
				}
				int len = tkn.Subs_len();
				for (int i = 0; i < len; i++) {
					Xop_tkn_itm sub = tkn.Subs_get(i);
					Bld_recurse(bfr, sub);
				}
				break;
		}
	}
	public static void Write_atr_text(Bry_bfr bfr, byte[] src, int bgn, int end) {
		for (int i = bgn; i < end; i++) {
			byte b = src[i];
			switch (b) {
				case Byte_ascii.Nl: case Byte_ascii.Cr: case Byte_ascii.Tab:		// NOTE: escape ws so that it renders correctly in tool tips
					bfr.Add_byte_space();
					break;
				case Byte_ascii.Quote:		bfr.Add(Gfh_entity_.Quote_bry); break;
				case Byte_ascii.Lt:			bfr.Add(Gfh_entity_.Lt_bry); break;
				case Byte_ascii.Gt:			bfr.Add(Gfh_entity_.Gt_bry); break;
				case Byte_ascii.Amp:		bfr.Add(Gfh_entity_.Amp_bry); break;
				default:					bfr.Add_byte(b); break;
			}
		}
	}
	byte[] src; Xop_tkn_itm tkn;
	public static final byte[] Escape_bgn = Bry_.new_a7("&#");
}
