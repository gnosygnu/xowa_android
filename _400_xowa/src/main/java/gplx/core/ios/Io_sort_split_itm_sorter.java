package gplx.core.ios; import gplx.*; import gplx.core.*;
public class Io_sort_split_itm_sorter implements gplx.core.lists.ComparerAble {
	public int compare(Object lhsObj, Object rhsObj) {
		Io_sort_split_itm lhs = (Io_sort_split_itm)lhsObj, rhs = (Io_sort_split_itm)rhsObj;
		return Bry_.Compare(lhs.Bfr(), lhs.Key_bgn(), lhs.Key_end(), rhs.Bfr(), rhs.Key_bgn(), rhs.Key_end());
	}
	public static final    Io_sort_split_itm_sorter Instance = new Io_sort_split_itm_sorter(); Io_sort_split_itm_sorter() {}	// TS.static
}
