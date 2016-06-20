package gplx.xowa.htmls.sections; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
public class Xoh_section_mgr {
	private final    List_adp list = List_adp_.New();
	public void Clear() {list.Clear();}
	public int Len() {return list.Count();}
	public Xoh_section_itm Get_at(int i) {return (Xoh_section_itm)list.Get_at(i);}
	public Xoh_section_itm Add(int uid, int level, byte[] anchor, byte[] display) {
		Xoh_section_itm rv = new Xoh_section_itm(uid, level, anchor, display);
		list.Add(rv);
		return rv;
	}
	public void Set_content(int section_idx, byte[] src, int pos) {
		Xoh_section_itm itm = this.Get_at(section_idx);
		if (pos > itm.Content_bgn())
			itm.Content_(Bry_.Mid(src, itm.Content_bgn(), pos));
		else	// TIDY:tidy will collapse "<h2>A</h2>\n\n<h2>B</h2>" to "<h2>A</h2>\n<h2>B</h2>"; this will show up as a pos < itm.Content_bgn; PAGE:en.w:Summer_solstice; DATE:2016-01-04
			itm.Content_(Bry_.Empty);
	}
	public void To_bfr(Bry_bfr bfr) {
		int len = this.Len();
		for (int i = 0; i < len; ++i) {
			Xoh_section_itm itm = this.Get_at(i);
			itm.To_bfr(bfr);
		}
	}
}
