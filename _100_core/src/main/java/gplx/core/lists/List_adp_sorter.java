package gplx.core.lists; import gplx.*; import gplx.core.*;
public class List_adp_sorter {
	private ComparerAble comparer = null;
	public void Sort(Object[] orig, int origLen) {Sort(orig, origLen, true, null);}
	public void Sort(Object[] orig, int origLen, boolean asc, ComparerAble comparer) {
		this.comparer = comparer;
		Object[] temp = new Object[origLen];
		MergeSort(asc, orig, temp, 0, origLen - 1);
		this.comparer = null;
	}
	void MergeSort(boolean asc, Object[] orig,Object[] temp, int lhs, int rhs) {
		if (lhs < rhs) {
			int mid = (lhs + rhs) / 2;
			MergeSort(asc, orig, temp, lhs, mid);
			MergeSort(asc, orig, temp, mid + 1, rhs);
			Combine(asc, orig, temp, lhs, mid + 1, rhs);
		}
	}
	private void Combine(boolean asc, Object[] orig, Object[] temp, int lhsPos, int rhsPos, int rhsEnd) {
		int lhsEnd = rhsPos - 1;
		int tmpPos = lhsPos;
		int aryLen = rhsEnd - lhsPos + 1;
	    
		while (lhsPos <= lhsEnd && rhsPos <= rhsEnd) {
			int compareVal = 0;
			if (comparer != null)
				compareVal = ComparerAble_.Compare(comparer, orig[lhsPos], orig[rhsPos]);
			else {
				Comparable lhsComp = (Comparable)orig[lhsPos];	//#<>System.Comparable~Comparable
				compareVal = lhsComp == null ? CompareAble_.Less : lhsComp.compareTo(orig[rhsPos]);
			}
			if (!asc) compareVal *= -1;
			if (compareVal <= CompareAble_.Same) // NOTE: (a) must be < 0; JAVA's String.compareTo returns -number based on position; (b) must be <= else sorting sorted list will change order; EX: sorting (a,1;a,2) on fld0 will switch to (a,2;a,1)
				temp[tmpPos++] = orig[lhsPos++];
			else
				temp[tmpPos++] = orig[rhsPos++];
		}
	    
		while (lhsPos <= lhsEnd)	// Copy rest of first half
			temp[tmpPos++] = orig[lhsPos++];
		while (rhsPos <= rhsEnd)	// Copy rest of right half
			temp[tmpPos++] = orig[rhsPos++];
		for (int i = 0; i < aryLen; i++, rhsEnd--)
			orig[rhsEnd] = temp[rhsEnd];
	}

	public static List_adp_sorter new_() {return new List_adp_sorter();} List_adp_sorter() {}
}
