package gplx.core.primitives; import gplx.*; import gplx.core.*;
public class String_obj_val implements CompareAble {
	public String Val() {return val;} private final String val;
	@Override public String toString() {return val;}
	public int compareTo(Object obj) {
		String_obj_val comp = (String_obj_val)obj;
		return String_.Compare(val, comp.val);
	}	
	public static String_obj_val new_(String val) {return new String_obj_val(val);}	String_obj_val(String val) {this.val = val;}
}
