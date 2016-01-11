package gplx.core.primitives; import gplx.*; import gplx.core.*;
public class Byte_obj_val {
	public byte Val() {return val;} private byte val;
	@Override public String toString() {return Int_.To_str(val);}
	@Override public int hashCode() {return val;}
	@Override public boolean equals(Object obj) {return obj == null ? false : val == ((Byte_obj_val)obj).Val();}
        public static Byte_obj_val new_(byte val) {
		Byte_obj_val rv = new Byte_obj_val();
		rv.val = val;
		return rv;
	}	private Byte_obj_val() {}
}
