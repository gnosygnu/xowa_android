package gplx.core.primitives; import gplx.*; import gplx.core.*;
public class String_obj_ref {
	public String Val() {return val;} public String_obj_ref Val_(String v) {val = v; return this;} private String val;
	public String_obj_ref Val_null_() {return Val_(null);}
	@Override public String toString() {return val;}
	public static String_obj_ref empty_() {return new_("");}
	public static String_obj_ref null_() {return new_(null);}
        public static String_obj_ref new_(String val) {
		String_obj_ref rv = new String_obj_ref();
		rv.val = val;
		return rv;
	}	String_obj_ref() {}
}
