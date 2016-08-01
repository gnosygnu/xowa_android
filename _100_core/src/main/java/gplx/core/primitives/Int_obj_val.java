package gplx.core.primitives; import gplx.*; import gplx.core.*;
public class Int_obj_val implements CompareAble {
	public Int_obj_val(int val) {this.val = val;}
	public int Val() {return val;} private final    int val;
	@Override public String toString() {return Int_.To_str(val);}
	@Override public int hashCode() {return val;}
	@Override public boolean equals(Object obj) {return obj == null ? false : val == ((Int_obj_val)obj).Val();}
	public int compareTo(Object obj) {Int_obj_val comp = (Int_obj_val)obj; return Int_.Compare(val, comp.val);}
}
