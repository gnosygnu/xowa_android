package gplx.xowa.wikis.tdbs.hives; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.tdbs.*;
public class Bry_comparer_bgn_eos implements gplx.core.lists.ComparerAble {
	public Bry_comparer_bgn_eos(int bgn) {this.bgn = bgn;} private int bgn;
	public int compare(Object lhsObj, Object rhsObj) {
		byte[] lhs = (byte[])lhsObj, rhs = (byte[])rhsObj;
		return Bry_.Compare(lhs, bgn, lhs.length, rhs, bgn, rhs.length);
	}
}
