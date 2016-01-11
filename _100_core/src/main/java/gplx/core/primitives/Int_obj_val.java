package gplx.core.primitives; import gplx.*; import gplx.core.*;
public class Int_obj_val implements CompareAble {
	public int Val() {return val;} int val;
	@Override public String toString() {return Int_.To_str(val);}
	@Override public int hashCode() {return val;}
	@Override public boolean equals(Object obj) {return obj == null ? false : val == ((Int_obj_val)obj).Val();}
	public int compareTo(Object obj) {Int_obj_val comp = (Int_obj_val)obj; return Int_.Compare(val, comp.val);}
        public static Int_obj_val neg1_() {return new_(-1);}
        public static Int_obj_val zero_() {return new_(0);}
        public static Int_obj_val new_(int val) {
		Int_obj_val rv = new Int_obj_val();
		rv.val = val;
		return rv;
	}	Int_obj_val() {}
}
