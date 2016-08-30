package gplx.xowa.xtns.lst; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.parsers.*; 
class Lst_pfunc_lstx_ {
	public static void Sect_exclude(Bry_bfr bfr, Lst_section_nde_mgr sec_mgr, byte[] src, byte[] sect_exclude, byte[] sect_replace) {
		if		(Bry_.Len_eq_0(sect_exclude)) {	// no exclude arg; EX: {{#lstx:page}} or {{#lstx:page}}
			bfr.Add(src);							// write all and exit
			return;
		}
		int sections_len = sec_mgr.Len();
		int bgn_pos = 0;
		for (int i = 0; i < sections_len; i++) {
			Lst_section_nde section = sec_mgr.Get_at(i);
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
	public static final    byte[] Null_arg = null;
}
