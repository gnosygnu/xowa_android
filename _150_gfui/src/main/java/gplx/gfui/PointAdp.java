package gplx.gfui; import gplx.*;
public class PointAdp implements To_str_able {//_20101206
	public int X() {return x;} final    int x;
	public int Y() {return y;} final    int y;
	public		PointAdp Op_add(PointAdp val) {return new PointAdp(x + val.x, y + val.y);}
	public		PointAdp Op_add(int xv, int yv) {return new PointAdp(x + xv, y + yv);}
	public 		PointAdp Op_add(int i) {return new PointAdp(x + i, y + i);}
	public		PointAdp Op_subtract(PointAdp val) {return new PointAdp(x - val.x, y - val.y);}
	public boolean Eq(Object compObj) {
		PointAdp comp = PointAdp_.as_(compObj); if (comp == null) return false;
		return x == comp.x && y == comp.y;
	}
	public String To_str() {return String_.Concat_any(x, ",", y);}
	@Override public String toString() {return To_str();}
	@Override public boolean equals(Object obj) {return Eq(obj);}
	@Override public int hashCode() {return super.hashCode();}
	public PointAdp(int x, int y) {this.x = x; this.y = y;}
}
