package gplx.xowa.users.history; import gplx.*; import gplx.xowa.*; import gplx.xowa.users.*;
public class Xou_history_sorter implements gplx.core.lists.ComparerAble {
	public boolean Ascending() {return ascending;} public Xou_history_sorter Ascending_(boolean v) {ascending = v; return this;} private boolean ascending = false;
	public int Sort_fld() {return sort_fld;} public Xou_history_sorter Sort_fld_(int v) {sort_fld = v; return this;} private int sort_fld = Xou_history_itm.Fld_view_end;
	public int compare(Object lhsObj, Object rhsObj) {
		Xou_history_itm lhs = (Xou_history_itm)lhsObj, rhs = (Xou_history_itm)rhsObj;
		int comp = CompareAble_.Compare_obj(lhs.Fld(sort_fld), rhs.Fld(sort_fld));
		if (!ascending) comp *= -1;
		return comp;
	}
}
