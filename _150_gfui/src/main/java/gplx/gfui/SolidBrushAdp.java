package gplx.gfui; import gplx.*;
public class SolidBrushAdp  {//_20101212
	public ColorAdp Color() {return color;} ColorAdp color;
	//#{UnderBrush
	//#}
	@Override public String toString() {return color.XtoHexStr();}
	public boolean Eq(Object obj) {
		SolidBrushAdp comp = SolidBrushAdp_.as_(obj); if (comp == null) return false;
		return color.Eq(comp.color);
	}
	@gplx.Internal protected static SolidBrushAdp new_(ColorAdp color) {
		SolidBrushAdp rv = new SolidBrushAdp();
		rv.color = color;
		return rv;
	}
}
