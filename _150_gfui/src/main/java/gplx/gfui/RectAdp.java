package gplx.gfui; import gplx.*;
public class RectAdp {//_20101206
	public SizeAdp Size() {return size;} SizeAdp size = SizeAdp_.Zero;
	public PointAdp Pos() {return pos;} PointAdp pos = PointAdp_.Zero;
	public int Width() {return size.Width();} public int Height() {return size.Height();}
	public int X() {return pos.X();} public int Y() {return pos.Y();}
	public PointAdp CornerTL() {return pos;}
	public PointAdp CornerTR() {return PointAdp_.new_(pos.X() + size.Width(), pos.Y());}
	public PointAdp CornerBL() {return PointAdp_.new_(pos.X(), pos.Y() + size.Height());}
	public PointAdp CornerBR() {return PointAdp_.new_(pos.X() + size.Width(), pos.Y() + size.Height());}
	@gplx.Internal protected boolean ContainsPoint(PointAdp point) {
		return point.X() >= pos.X() && point.X() <= pos.X() + size.Width()
			&& point.Y() >= pos.Y() && point.Y() <= pos.Y() + size.Height();
	}
	public RectAdp Op_add(RectAdp v) {
		return new RectAdp(pos.Op_add(v.Pos()), size.Op_add(v.Size()));		
	}
	@Override public String toString() {return String_.Concat_any(pos, ";", size);}
	@Override public boolean equals(Object obj) {
		RectAdp comp = (RectAdp)obj;
		return size.Eq(comp.size) && pos.Eq(comp.pos);
	}
	@Override public int hashCode() {return super.hashCode();}
	public String Xto_str() {return String_.Concat_any(pos, ",", size);}

	@gplx.Internal protected RectAdp(PointAdp pos, SizeAdp size) {this.pos = pos; this.size = size;}
}
