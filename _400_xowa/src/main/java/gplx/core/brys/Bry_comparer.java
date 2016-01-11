package gplx.core.brys; import gplx.*; import gplx.core.*;
import gplx.core.lists.*;
public class Bry_comparer implements ComparerAble {
	public int compare(Object lhsObj, Object rhsObj) {
		byte[] lhs = (byte[])lhsObj, rhs = (byte[])rhsObj;
		return Bry_.Compare(lhs, 0, lhs.length, rhs, 0, rhs.length);
	}
	public static final Bry_comparer Instance = new Bry_comparer(); Bry_comparer() {}
}
