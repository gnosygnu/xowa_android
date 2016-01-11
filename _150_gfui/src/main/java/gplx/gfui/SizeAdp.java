package gplx.gfui; import gplx.*;
public class SizeAdp {//_20101206
	public int Width() {return width;} int width;
	public int Height() {return height;} int height;
	public int AxisLength(GfuiAxisType axis) {return axis == GfuiAxisType.X ? width : height;}
	public		SizeAdp Op_add(int w, int h) {return SizeAdp_.new_(width + w, height + h);}
	@gplx.Internal protected	SizeAdp Op_add(SizeAdp s) {return SizeAdp_.new_(width + s.width, height + s.height);}
	@gplx.Internal protected	SizeAdp Op_subtract(int val) {return SizeAdp_.new_(width - val, height - val);}
	@gplx.Internal protected	SizeAdp Op_subtract(int w, int h) {return SizeAdp_.new_(width - w, height - h);}
	public String To_str() {return String_.Concat_any(width, ",", height);}
	public boolean Eq(Object o) {
		SizeAdp comp = (SizeAdp)o; if (comp == null) return false;
		return width == comp.width && height == comp.height;
	}
	@Override public String toString() {return To_str();}
	@Override public boolean equals(Object obj) {return Eq(obj);}
	@Override public int hashCode() {return super.hashCode();}
	@gplx.Internal protected SizeAdp(int width, int height) {this.width = width; this.height = height;}
}
