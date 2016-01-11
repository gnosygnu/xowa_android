package gplx.core.primitives; import gplx.*; import gplx.core.*;
public class Byte_obj_ref {
	public byte Val() {return val;} private byte val;
	public Byte_obj_ref Val_(byte v) {val = v; return this;}
	@Override public int hashCode() {return val;}
	@Override public boolean equals(Object obj) {return obj == null ? false : val == ((Byte_obj_ref)obj).Val();}
	@Override public String toString() {return Int_.To_str(val);}
        public static Byte_obj_ref zero_() {return new_(Byte_.Zero);}
        public static Byte_obj_ref new_(byte val) {
		Byte_obj_ref rv = new Byte_obj_ref();
		rv.val = val;
		return rv;
	}	private Byte_obj_ref() {}
}
