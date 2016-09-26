package gplx.core.lists.binary_searches; import gplx.*; import gplx.core.*; import gplx.core.lists.*;
import gplx.core.lists.*;
interface Binary_search_cmp {
	int Compare(Object comp);
}
class Binary_search_cmp__comparable implements Binary_search_cmp {
	private final    CompareAble val;
	public Binary_search_cmp__comparable(CompareAble val) {this.val = val;}
	public int Compare(Object comp) {
		return val.compareTo((CompareAble)comp);
	}
}
class Binary_search_cmp__comparer implements Binary_search_cmp {
	private final    Binary_comparer comparer; private final    Object val;
	public Binary_search_cmp__comparer(Binary_comparer comparer, Object val) {this.comparer = comparer; this.val = val;}
	public int Compare(Object comp) {
		return comparer.Compare_val_to_obj(val, comp);
	}
}
