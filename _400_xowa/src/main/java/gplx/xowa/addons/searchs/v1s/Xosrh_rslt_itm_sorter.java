package gplx.xowa.addons.searchs.v1s; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.xowa.wikis.data.tbls.*;
public class Xosrh_rslt_itm_sorter implements gplx.core.lists.ComparerAble {
	public byte Tid() {return tid;} public Xosrh_rslt_itm_sorter Tid_(byte v) {tid = v; return this;} private byte tid = Tid_len_dsc;
	public int compare(Object lhsObj, Object rhsObj) {
		Xowd_page_itm lhs = (Xowd_page_itm)lhsObj;
		Xowd_page_itm rhs = (Xowd_page_itm)rhsObj;
		if		(lhs == null || rhs == null || tid == Tid_none)	return CompareAble_.Same;
//			else if	(lhs == null)					return CompareAble_.Less;
//			else if	(rhs == null)					return CompareAble_.More;
		else {
			switch (tid) {
				case Tid_len_dsc:	return Int_.Compare(lhs.Text_len(), rhs.Text_len()) * -1;
				case Tid_ttl_asc:	return Bry_.Compare(lhs.Ttl_page_db(), rhs.Ttl_page_db());
				case Tid_id:		return Int_.Compare(lhs.Id(), rhs.Id());
				default:			throw Err_.new_unhandled(tid);
			}
		}
	}
	public static final byte Tid_none = 0, Tid_len_dsc = 1, Tid_ttl_asc = 2, Tid_id = 3;
	public static byte parse(String v) {
		if		(String_.Eq(v, "none"))			return Tid_none;
		else if	(String_.Eq(v, "len_desc"))		return Tid_len_dsc;
		else if	(String_.Eq(v, "title_asc"))	return Tid_ttl_asc;
		else									throw Err_.new_unhandled(v);
	}
	public static String Xto_url_arg(byte v) {
		switch (v) {
			case Tid_none:		return "";
			case Tid_len_dsc:	return "&xowa_sort=len_desc";
			case Tid_ttl_asc:	return "&xowa_sort=title_asc";
			default:			throw Err_.new_unhandled(v);
		}
	}
}
