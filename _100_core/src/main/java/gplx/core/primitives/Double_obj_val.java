package gplx.core.primitives; import gplx.*; import gplx.core.*;
public class Double_obj_val implements CompareAble {
	public double Val() {return val;} double val;
	@Override public String toString() {return Double_.To_str(val);}
	@Override public int hashCode() {return (int)val;}
	@Override public boolean equals(Object obj) {return obj == null ? false : val == ((Double_obj_val)obj).Val();}
	public int compareTo(Object obj) {Double_obj_val comp = (Double_obj_val)obj; return Double_.Compare(val, comp.val);}
        public static Double_obj_val neg1_() {return new_(-1);}
        public static Double_obj_val zero_() {return new_(0);}
        public static Double_obj_val new_(double val) {
		Double_obj_val rv = new Double_obj_val();
		rv.val = val;
		return rv;
	}	Double_obj_val() {}
}
