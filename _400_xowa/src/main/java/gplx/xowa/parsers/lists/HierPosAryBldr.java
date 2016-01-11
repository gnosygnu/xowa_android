package gplx.xowa.parsers.lists; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.strings.*;
public class HierPosAryBldr {
	public void Init() {
		int ary_max = ary.length;
		for (int i = 0; i < ary_max; i++)
			ary[i] = 0;
		aryIdx = -1;
		root = 0;
	}
	public void MoveDown() {
		aryIdx += 1;
		if (aryIdx == 0)
			ary[aryIdx] = root;
		else
			ary[aryIdx] = 0;
	}
	public void MoveUp() {
		aryIdx -= 1;
		MoveNext();
	}
	public void MoveNext() {
		if (aryIdx == -1)
			root += 1;
		else
			ary[aryIdx] += 1;
	}
	public boolean Dirty() {return aryIdx > -1 || root > 0;}
	public int[] XtoIntAry() {
		if (aryIdx == -1) return Int_.Ary_empty;
		int[] rv = new int[aryIdx + 1];
		for (int i = 0; i < aryIdx + 1; i++)
			rv[i] = ary[i];
		return rv;
	}
	public String To_str() {
		String_bldr sb = String_bldr_.new_();
		for (int i = 0; i < aryIdx; i++)
			sb.Add_spr_unless_first(Int_.To_str(ary[i]), " ", i);
		return sb.To_str();
	}
	int[] ary; int aryIdx = -1; int root = -1;
	public HierPosAryBldr(int ary_max)	{ary = new int[ary_max]; this.Init();}
}
