package gplx.dbs.sqls; import gplx.*; import gplx.dbs.*;
import gplx.core.gfo_ndes.*;
import gplx.core.lists.*;
public class Sql_order_by_sorter implements ComparerAble {
	public int compare(Object lhsObj, Object rhsObj) {
		GfoNde lhs = (GfoNde)lhsObj; GfoNde rhs = (GfoNde)rhsObj;
		Sql_order_by_itm item = null; Object lhsData = null, rhsData = null;
		for (int i = 0; i < items.length; i++) {
			item = items[i];
			lhsData = lhs.Read(item.Name()); rhsData = rhs.Read(item.Name());
			int compare = CompareAble_.Compare_obj(lhsData, rhsData);
			if (compare == CompareAble_.Same) continue;
			int ascendingVal = item.Ascending() ? 1 : -1;
			return compare * ascendingVal;
		}
		return CompareAble_.Same;
	}
	Sql_order_by_itm[] items;
	public static ComparerAble new_(Sql_order_by_itm[] items) {
		Sql_order_by_sorter rv = new Sql_order_by_sorter();
		rv.items = items;
		return rv;
	}
}
