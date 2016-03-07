package gplx.xowa.addons.searchs.v1s; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.xowa.wikis.data.tbls.*;
public class Xosrh_rslt_grp {
	public Xosrh_rslt_grp(int idx) {this.idx = idx;} private Xowd_page_itm[] itms = Xowd_page_itm.Ary_empty;
	public int Idx() {return idx;} private int idx;
	public int Itms_len() {return itms_len;} private int itms_len; int itms_max;
	public int Itms_total() {return itms_total;} public Xosrh_rslt_grp Itms_total_(int v) {itms_total = v; return this;} private int itms_total;
	public Xowd_page_itm Itms_get_at(int i) {return itms[i];}
	public void Itms_add(Xowd_page_itm itm) {
		int new_itms_len = itms_len + 1;
		if (new_itms_len > itms_max) {
			itms_max = itms_max == 0 ? 2 : itms_max * 2;
			itms = (Xowd_page_itm[])Array_.Resize(itms, itms_max);
		}
		itms[itms_len] = itm;
		itms_len = new_itms_len;
	}
	public boolean Itms_full() {return itms_len >= itms_max;}
	public void Itms_clear() {
		for (int i = 0; i < itms_len; i++)
			itms[i] = null;
		itms = Xowd_page_itm.Ary_empty;
		itms_len = itms_max = 0;
	}
	public void Itms_sort(Xowd_page_itm_sorter sorter) {Array_.Sort(itms, sorter);}
	public static final Xosrh_rslt_grp[] Ary_empty = new Xosrh_rslt_grp[0];
}
