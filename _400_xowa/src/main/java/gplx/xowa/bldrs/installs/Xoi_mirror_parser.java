package gplx.xowa.bldrs.installs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
public class Xoi_mirror_parser {
	public String[] Parse(String raw_str) {
		if (String_.Len_eq_0(raw_str)) return String_.Ary_empty;
		byte[] raw = Bry_.new_u8(raw_str);
		List_adp rv = List_adp_.new_();
		int pos = 0;
		while (true) {
			int bgn = Bry_find_.Find_fwd(raw, CONST_href_bgn, pos); if (bgn == Bry_find_.Not_found) break;
			bgn += CONST_href_bgn.length;			
			int end = Bry_find_.Find_fwd(raw, CONST_href_end, bgn); if (end == Bry_find_.Not_found) return String_.Ary_empty;
			byte[] date = Bry_.Mid(raw, bgn, end); 
			pos = end + CONST_href_end.length;			
			if (Bry_.Match(date, CONST_date_parent_dir)) continue;
			int date_pos_last = date.length - 1;
			if (date_pos_last == -1) return String_.Ary_empty;
			if (date[date_pos_last] == Byte_ascii.Slash) date = Bry_.Mid(date, 0, date_pos_last);	// trim trailing /; EX: "20130101/" -> "20130101" 
			rv.Add(String_.new_u8(date));
		}
		return rv.To_str_ary();
	}	static final byte[] CONST_href_bgn = Bry_.new_a7("<a href=\""), CONST_href_end = Bry_.new_a7("\""), CONST_date_parent_dir = Bry_.new_a7("../");
	public static String Find_last_lte(String[] ary, String comp) {	// assuming sorted ary, find last entry that is lte comp
		int len = ary.length;
		for (int i = len - 1; i > -1; i--) {
			String itm = ary[i];			
			if (CompareAble_.Is_lessOrSame(itm, comp)) return itm;
		}
		return "";
	}
}