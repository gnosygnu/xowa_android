package gplx.core.primitives; import gplx.*; import gplx.core.*;
public class Int_list {
	private int[] ary = Int_.Ary_empty; private int ary_len, ary_max;
	public void Add(int uid) {
		int new_len = ary_len + 1;
		if (new_len > ary_max) {
			ary_max += 16;
			int[] new_ary = new int[ary_max];
			Int_.Ary_copy_to(ary, ary_len, new_ary);
			ary = new_ary;
		}
		ary[ary_len] = uid;
		ary_len = new_len;
	}
	public int Len() {return ary_len;}
	public int Get_at(int i) {return ary[i];}
	public void Clear() {
		ary = Int_.Ary_empty;
		ary_len = ary_max = 0;
	}
	public static Int_list new_(int... ary) {
		Int_list rv = new Int_list();
		int len = ary.length;
		rv.ary = ary; rv.ary_len = len; rv.ary_max = len;
		return rv;
	}
}
