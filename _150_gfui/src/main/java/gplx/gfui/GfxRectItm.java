package gplx.gfui; import gplx.*;
import gplx.core.strings.*;
public class GfxRectItm extends GfxItm_base {
	public float Width() {return width;} float width;
	public ColorAdp Color() {return color;} ColorAdp color;

	@Override public String toString() {return String_.Concat(super.toString(), String_bldr_.new_().Add_kv_obj("width", width).Add_kv("color", color.XtoHexStr()).To_str());}
	@Override public int hashCode() {return this.toString().hashCode();}
	@Override public boolean equals(Object obj) {
		GfxRectItm comp = GfxRectItm.as_(obj); if (comp == null) return false;
		return super.equals(comp) && width == comp.width && color.Eq(comp.color);	//#<>.Equals~.equals
	}
	public static GfxRectItm new_(PointAdp pos, SizeAdp size, float width, ColorAdp color) {
		GfxRectItm rv = new GfxRectItm();
		rv.ctor_GfxItmBase(pos, size);
		rv.width = width; rv.color = color;
		return rv;
	}	GfxRectItm() {}
	@gplx.New public static GfxRectItm as_(Object obj) {return obj instanceof GfxRectItm ? (GfxRectItm)obj : null;}
}
