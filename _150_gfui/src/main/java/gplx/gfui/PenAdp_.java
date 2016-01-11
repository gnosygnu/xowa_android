package gplx.gfui; import gplx.*;
public class PenAdp_ {//_20101212
	public static PenAdp as_(Object obj) {return obj instanceof PenAdp ? (PenAdp)obj : null;}
	public static PenAdp cast(Object obj) {try {return (PenAdp)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, PenAdp.class, obj);}}
	public static PenAdp black_() {return new_(ColorAdp_.Black, 1);}
	public static PenAdp new_(ColorAdp color) {return new_(color, 1);}
	public static PenAdp new_(ColorAdp color, float width) {return new PenAdp(color, width);}
}
