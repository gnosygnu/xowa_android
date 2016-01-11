package gplx.core.primitives; import gplx.*; import gplx.core.*;
public class Int_obj_ref {
	public int Val() {return val;} public Int_obj_ref Val_(int v) {val = v; return this;} int val;
	public int Val_add() {val++; return val;}
	public int Val_add_post() {return val++;}
	public int Val_add(int v) {val += v; return val;}		
	public Int_obj_ref Val_zero_() {val = 0; return this;}
	public Int_obj_ref Val_neg1_() {val = -1; return this;}
	public String Val_as_str() {return Int_.To_str(val);}
	@Override public String toString() {return Int_.To_str(val);}
	@Override public int hashCode() {return val;}
	@Override public boolean equals(Object obj) {return val == ((Int_obj_ref)obj).Val();}
        public static Int_obj_ref neg1_() {return new_(-1);}
        public static Int_obj_ref zero_() {return new_(0);}
        public static Int_obj_ref new_(int val) {
		Int_obj_ref rv = new Int_obj_ref();
		rv.val = val;
		return rv;
	}	Int_obj_ref() {}

	public static int[] Ary_xto_int_ary(Int_obj_ref[] ary) {
		int len = ary.length;
		int[] rv = new int[len];
		for (int i = 0; i < len; ++i)
			rv[i] = ary[i].val;
		return rv;
	}
}
