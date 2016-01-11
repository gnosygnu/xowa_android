package gplx.core.primitives; import gplx.*; import gplx.core.*;
public class Int_ary_bldr {
	public Int_ary_bldr(int len) {ary = new int[len];}
	public Int_ary_bldr Set(int idx, int val) {ary[idx] = val; return this;}
	public int[] Xto_int_ary() {return ary;} private int[] ary;
}
