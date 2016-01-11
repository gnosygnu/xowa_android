package gplx.gfui; import gplx.*;
import gplx.core.interfaces.*;
public class SizeAdpF implements ParseAble {//_20101206
	public float Width() {return width;} float width;
	public float Height() {return height;} float height;
	public Object ParseAsObj(String raw) {return SizeAdp_.parse(raw);}
	@Override public String toString() {return String_.Concat_any(width, ":", height);}
	@gplx.Internal protected SizeAdpF(float width, float height) {this.width = width; this.height = height;}
}
