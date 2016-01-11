package gplx.gfui; import gplx.*;
public class SolidBrushAdp_ {//_20101212
	public static SolidBrushAdp as_(Object obj) {return obj instanceof SolidBrushAdp ? (SolidBrushAdp)obj : null;}
	public static SolidBrushAdp cast(Object obj) {try {return (SolidBrushAdp)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, SolidBrushAdp.class, obj);}}
	public static final SolidBrushAdp Black = new_(ColorAdp_.Black);
	public static final SolidBrushAdp White = new_(ColorAdp_.White);
	public static final SolidBrushAdp Null = new_(ColorAdp_.Null);
	public static SolidBrushAdp new_(ColorAdp color) {return SolidBrushAdpCache.Instance.Get_by(color);}
}
class SolidBrushAdpCache {//_20101212 // NOTE: Cache SolidBrushAdp b/c (a) java does not have Brush obj; (b) SolidBrushAdp is immutable
	public SolidBrushAdp Get_by(ColorAdp color) {
		SolidBrushAdp rv = (SolidBrushAdp)hash.Get_by(color.Value());
		if (rv == null) {
			rv = SolidBrushAdp.new_(color);
			hash.Add(color.Value(), rv);
		}
		return rv;
	}
	Hash_adp hash = Hash_adp_.new_();
	public static final SolidBrushAdpCache Instance = new SolidBrushAdpCache(); SolidBrushAdpCache() {}
}
