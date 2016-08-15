package gplx.xowa.xtns.lst; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*; import gplx.xowa.parsers.hdrs.*;
import gplx.xowa.wikis.nss.*; import gplx.xowa.wikis.pages.wtxts.*;
public class Lst_pfunc_itm_ {
	private static final byte Include_between = 0, Include_to_eos = 1, Include_to_bos = 2;
	public static void Sect_include(Bry_bfr bfr, Xop_ctx sub_ctx, byte[] src, byte[] lst_bgn, byte[] lst_end) {
		if		(lst_end == Null_arg) {		// no end arg; EX: {{#lst:page|bgn}}; NOTE: different than {{#lst:page|bgn|}}
			if	(lst_bgn == Null_arg) {		// no bgn arg; EX: {{#lst:page}}
				bfr.Add(src);				// write all and exit
				return;
			}
			else							// bgn exists; set end to bgn; EX: {{#lst:page|bgn}} is same as {{#lst:page|bgn|bgn}}; NOTE: {{#lst:page|bgn|}} means write from bgn to eos
				lst_end = lst_bgn;				
		}
		byte include_mode = Include_between;
		if		(Bry_.Len_eq_0(lst_end))
			include_mode = Include_to_eos;
		else if (Bry_.Len_eq_0(lst_bgn))
			include_mode = Include_to_bos;				
		int bgn_pos = 0; boolean bgn_found = false; int src_page_bry_len = src.length;
		Lst_section_nde_mgr section_mgr = sub_ctx.Lst_section_mgr();	// get section_mgr from Parse
		int sections_len = section_mgr.Len();
		for (int i = 0; i < sections_len; i++) {
			Lst_section_nde section = section_mgr.Get_at(i);
			byte section_tid = section.Name_tid();
			byte[] section_key = section.Section_name();
			if		(section_tid == Lst_section_nde.Xatr_bgn && Bry_.Eq(section_key, lst_bgn)) {
				int sect_bgn_rhs = section.Xnde().Tag_close_end();
				if (include_mode == Include_to_eos) {					// write from cur to eos; EX: {{#lst:page|bgn|}}
					bfr.Add_mid(src, sect_bgn_rhs, src_page_bry_len);
					return;
				}
				else {													// bgn and end
					if (!bgn_found) {									// NOTE: !bgn_found to prevent "resetting" of dupe; EX: <s begin=key0/>a<s begin=key0/>b; should start from a not b
						bgn_pos = sect_bgn_rhs;
						bgn_found = true;
					}
				}
			}
			else if (section_tid == Lst_section_nde.Xatr_end && Bry_.Eq(section_key, lst_end)) {
				int sect_end_lhs = section.Xnde().Tag_open_bgn();
				if (include_mode == Include_to_bos) {					// write from bos to cur; EX: {{#lst:page||end}}
					bfr.Add_mid(src, 0, sect_end_lhs);
					return;
				}
				else {
					if (bgn_found) {									// NOTE: bgn_found to prevent writing from bos; EX: a<s end=key0/>b should not write anything 
						bfr.Add_mid(src, bgn_pos, sect_end_lhs);
						bgn_found = false;
					}
				}
			}
		}
		if (bgn_found)	// bgn_found, but no end; write to end of page; EX: "a <section begin=key/> b" -> " b"
			bfr.Add_mid(src, bgn_pos, src_page_bry_len);
	}
	public static void Sect_exclude(Bry_bfr bfr, Xop_ctx sub_ctx, byte[] src, byte[] sect_exclude, byte[] sect_replace) {
		if		(Bry_.Len_eq_0(sect_exclude)) {	// no exclude arg; EX: {{#lstx:page}} or {{#lstx:page}}
			bfr.Add(src);							// write all and exit
			return;
		}
		Lst_section_nde_mgr section_mgr = sub_ctx.Lst_section_mgr();	// get section_mgr from Parse
		int sections_len = section_mgr.Len();
		int bgn_pos = 0;
		for (int i = 0; i < sections_len; i++) {
			Lst_section_nde section = section_mgr.Get_at(i);
			byte section_tid = section.Name_tid();
			byte[] section_key = section.Section_name();
			if		(section_tid == Lst_section_nde.Xatr_bgn && Bry_.Eq(section_key, sect_exclude)) {	// exclude section found
				bfr.Add_mid(src, bgn_pos, section.Xnde().Tag_open_bgn());									// write everything from bgn_pos up to exclude
			}
			else if (section_tid == Lst_section_nde.Xatr_end && Bry_.Eq(section_key, sect_exclude)) {	// exclude end found
				if (sect_replace != null)
					bfr.Add(sect_replace);					// write replacement
				bgn_pos = section.Xnde().Tag_close_end();	// reset bgn_pos
			}
		}
		bfr.Add_mid(src, bgn_pos, src.length);
	}
	public static void Hdr_include(Bry_bfr bfr, byte[] src, Xopg_toc_mgr toc_mgr, byte[] lhs_hdr, byte[] rhs_hdr) {// REF.MW:LabeledSectionTransclusion.class.php|pfuncIncludeHeading; MW does regex on text; XO uses section_itms
		// get <hdr> idxs
		int len = toc_mgr.Len();
		int lhs_idx = Match_or_neg1(toc_mgr, len, src, lhs_hdr, 0)			; if (lhs_idx == -1) return;
		int rhs_idx = Match_or_neg1(toc_mgr, len, src, rhs_hdr, lhs_idx + 1);

		// get snip_bgn
		Xop_hdr_tkn lhs_tkn = toc_mgr.Get_at(lhs_idx);
		int snip_bgn = lhs_tkn.Src_end();

		// get snip_end
		int snip_end = -1;
		if (rhs_idx == -1) {		// rhs_idx missing or not supplied
			rhs_idx = lhs_idx + 1;
			if (rhs_idx < len) {	// next hdr after lhs_hdr exists; try to get next "matching" hdr; EX: h2 should match next h2; PAGE:en.w:10s_BC; DATE:2016-08-13
				for (int i = rhs_idx; i < len; ++i) {
					Xop_hdr_tkn rhs_tkn = toc_mgr.Get_at(i);
					if (rhs_tkn.Num() == lhs_tkn.Num()) {
						snip_end = rhs_tkn.Src_bgn();
						break;
					}
				}
			}
			if (snip_end == -1)		// no matching rhs exists, or rhs is last; get till EOS
				snip_end = src.length;
		}
		else {
			Xop_hdr_tkn rhs_tkn = toc_mgr.Get_at(rhs_idx);
			snip_end = rhs_tkn.Src_bgn();
		}
		bfr.Add_mid(src, snip_bgn, snip_end);
	}
	private static int Match_or_neg1(Xopg_toc_mgr toc_mgr, int hdrs_len, byte[] src, byte[] match, int hdrs_bgn) {
		for (int i = hdrs_bgn; i < hdrs_len; ++i) {
			Xop_hdr_tkn hdr = toc_mgr.Get_at(i);
			int txt_bgn = hdr.Src_bgn() + hdr.Num();	// skip "\n=="; 1=leading \n
			if (hdr.Src_bgn() != Xop_parser_.Doc_bgn_char_0)
				++txt_bgn;

			// get txt_end; note that this needs to handle multiple trailing \n which is included in hdr.Src_end()
			int txt_end = Bry_find_.Find_fwd(src, Bry__hdr_end, txt_bgn);				// find "=\n"
			txt_end = Bry_find_.Find_bwd__skip(src, txt_end, txt_bgn, Byte_ascii.Eq);	// skip bwd to get to pos before 1st "="; EX: "===\n" -> find "=="

			// remove ws
			txt_bgn = Bry_find_.Find_fwd_while_ws(src, txt_bgn, txt_end);
			txt_end = Bry_find_.Find_bwd__skip_ws(src, txt_end, txt_bgn);
			if (Bry_.Eq(src, txt_bgn, txt_end, match)) return i;
		}
		return -1;
	}
	private static final    byte[] Bry__hdr_end = Bry_.new_a7("=\n");
	public static final    byte[] Null_arg = null;
}
