package gplx;
//#{import
//#}
import gplx.core.lists.*;
public class CompareAble_ {
	public static Comparable as_(Object obj) {return obj instanceof Comparable ? (Comparable)obj : null;}
	public static int Compare_obj(Object lhs, Object rhs) {return CompareComparables(as_(lhs), as_(rhs));}
	public static int CompareComparables(Comparable lhs, Comparable rhs) {
		if		(lhs == null && rhs == null)	return CompareAble_.Same;
		else if (lhs == null)					return CompareAble_.More;
		else if (rhs == null)					return CompareAble_.Less;
		else									return Compare(lhs, rhs);
	}

	public static boolean Is_more(Comparable lhs, Comparable rhs)			{return Is(More, lhs, rhs);}
	public static boolean Is_moreOrSame(Comparable lhs, Comparable rhs)		{return Is(MoreOrSame, lhs, rhs);}
	public static boolean Is_less(Comparable lhs, Comparable rhs)			{return Is(Less, lhs, rhs);}
	public static boolean Is_lessOrSame(Comparable lhs, Comparable rhs)		{return Is(LessOrSame, lhs, rhs);}
	public static boolean Is_same(Comparable lhs, Comparable rhs)			{return Is(Same, lhs, rhs);}
	public static boolean Is(int expt, Comparable lhs, Comparable rhs) {
		int actl = CompareComparables(lhs, rhs);
		if (actl == Same && expt % 2 == Same)	// actl=Same and expt=(Same||MoreOrSame||LessOrSame)
			return true;
		else
			return (actl * expt) > 0;			// actl=More||Less; expd will match if on same side of 0 (ex: expt=Less; actl=Less; -1 * -1 = 1)
	}
//		public static int FindSlot(ComparerAble comparer, Object[] ary, Object itm) {return FindSlot(comparer, ary, itm, false);}
	public static int FindSlot(ComparerAble comparer, Object[] ary, Object itm) {if (itm == null) throw Err_.new_null();
		int aryLen = ary.length;
		switch (aryLen) {
			case 0: throw Err_.new_wo_type("ary cannot have 0 itms");
			case 1: return 0;
		}
		int lo = -1, hi = aryLen - 1; // NOTE: -1 is necessary; see test
		int curPos = (hi - lo) / 2;
		int delta = 1;
		while (true) {
			Object curSeg = ary[curPos];
			int comp = curSeg == null ? CompareAble_.More : comparer.compare(itm, curSeg);	// nulls should only happen for lastAry //#<>comparer.Compare~comparer.compare
//				if (dbg) {
//					Tfds.Write(curPos, itm.toString(), comp, comp.toString(), curSeg.toString());
//				}
			if		(comp == CompareAble_.Same) return curPos;
			else if	(comp >  CompareAble_.Same) {lo = curPos; delta = 1;}
			else if	(comp <  CompareAble_.Same) {hi = curPos; delta = -1;}
			int dif = hi - lo;
			if (dif == 1 || dif == 0)	return hi;	// NOTE: can be 0 when ary.length == 1 || 2; also, sometimes 0 in some situations
			else						curPos += (dif / 2) * delta;
		}
	}
	public static int Compare(Comparable lhs, Comparable rhs) {return lhs.compareTo(rhs);}	//#<>compareTo~compareTo

	public static final int 
		  More = 1
		, Less = -1
		, Same = 0
		, MoreOrSame = 2
		, LessOrSame = -2
		, ReverseMult = -1
		, OffsetCompare = 1 // handle srcPos >= 1 -> srcPosChk > 0
		;
	public static int Multiplier(boolean v) {return v ? 1 : -1;}
}
