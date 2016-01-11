package gplx.core.lists; import gplx.*; import gplx.core.*;
public class Binary_search_ {
	public static int Search(CompareAble[] ary, int ary_len, CompareAble val) {
		if (ary_len == 1) return 0;
		int interval = ary_len / 2;
		int pos = interval - List_adp_.Base1;
		int pos_last = ary_len - 1;
		int pos_prv = -1;
		int loop_count = 0;
		while (loop_count++ < 32) {	// 32 bit integer
			CompareAble lo = ary[pos];
			CompareAble hi = pos + 1 == ary_len ? null : ary[pos + 1];
			int adj = 0;
			int lo_comp = val.compareTo(lo);
			if (lo_comp == CompareAble_.Less)		// val is < lo; search slots below
				adj = -1;
			else {
				if (hi == null) return pos;			// hi is null when at last slot in ary
				int hi_comp = val.compareTo(hi);
				if (hi_comp == CompareAble_.More)	// val is > hi; search slots above 
					adj =  1;
				else
					return pos;						// val is > lo and < hi; return slot 
			}
			interval /= 2;
			if (interval == 0) interval = 1;		// do not allow 0 intervals; pos must always change; 
			pos += (interval * adj);
			if (pos == 0 && pos_prv == 0) break;	// NOTE: this will only happen when 1st member is not ""
			if 		(pos < 0) 			pos = 0;	
			else if (pos > pos_last)	pos = pos_last;
			pos_prv = pos;
		}
		return Int_.Min_value;	// should only occur if (a) ary's 0th slot is not ""; or (b) some unknown error
	}
}
