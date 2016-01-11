package gplx.gfui; import gplx.*;
import gplx.core.lists.*; /*ComparerAble*/
class GfuiFocusOrderer {
	public static void OrderByX(GfuiElem owner) {Order(owner, xcomparer, 0);}
	public static void OrderByY(GfuiElem owner) {Order(owner, ycomparer, 0);}
	static int Order(GfuiElem owner, ComparerAble comparer, int order) {
		List_adp list = List_adp_.new_();
		for (int i = 0; i < owner.SubElems().Count(); i++) {
			GfuiElem sub = (GfuiElem)owner.SubElems().Get_at(i);
			if (sub.Focus_idx() != NullVal) continue;
			list.Add(sub);
		}
		list.Sort_by(comparer);

		for (Object subObj : list) {
			GfuiElem sub = (GfuiElem)subObj;
			sub.Focus_idx_(order++);
			if (owner.SubElems().Count() > 0)		// subs available; recurse;
				order = Order(sub, comparer, order);
		}
		return order;
	}
	static GfuiFocusOrderer_cls_x xcomparer = new GfuiFocusOrderer_cls_x(); static GfuiFocusOrderer_cls_y ycomparer = new GfuiFocusOrderer_cls_y();
	public static final int NullVal = -1;
}
class GfuiFocusOrderer_cls_x implements ComparerAble {
	public int compare(Object lhsObj, Object rhsObj) {
		GfuiElem lhs = (GfuiElem)lhsObj, rhs = (GfuiElem)rhsObj;
		if (lhs.Y() < rhs.Y())				return CompareAble_.Less;
		else if (lhs.Y() > rhs.Y())			return CompareAble_.More;
		else								return Int_.Compare(lhs.X(), rhs.X());
	}
}
class GfuiFocusOrderer_cls_y implements ComparerAble {
	public int compare(Object lhsObj, Object rhsObj) {
		GfuiElem lhs = (GfuiElem)lhsObj, rhs = (GfuiElem)rhsObj;
		if (lhs.X() < rhs.X())				return CompareAble_.Less;
		else if (lhs.X() > rhs.X())			return CompareAble_.More;
		else								return Int_.Compare(lhs.Y(), rhs.Y());
	}
}
