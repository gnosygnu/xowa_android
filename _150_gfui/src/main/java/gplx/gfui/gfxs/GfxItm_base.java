package gplx.gfui.gfxs; import gplx.*; import gplx.gfui.*;
import gplx.core.strings.*;
public abstract class GfxItm_base implements GfxItm {
	public PointAdp Pos() {return pos;} PointAdp pos = PointAdp_.Zero;
	public SizeAdp Size() {return size;} SizeAdp size = SizeAdp_.Zero;
	@Override public String toString() {return String_bldr_.new_().Add_kv_obj("pos", pos).Add_kv_obj("size", size).To_str();}
	@Override public int hashCode() {return this.toString().hashCode();}
	@Override public boolean equals(Object obj) {
		GfxItm_base comp = GfxItm_base.as_(obj); if (comp == null) return false;
		return Object_.Eq(pos, comp.pos) && Object_.Eq(size, comp.size);
	}
	@gplx.Virtual public void ctor_GfxItmBase(PointAdp posVal, SizeAdp sizeVal) {
		pos = posVal; size = sizeVal;
	}
	public static GfxItm_base as_(Object obj) {return obj instanceof GfxItm_base ? (GfxItm_base)obj : null;}
	public static GfxItm_base cast(Object obj) {try {return (GfxItm_base)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, GfxItm_base.class, obj);}}
}
