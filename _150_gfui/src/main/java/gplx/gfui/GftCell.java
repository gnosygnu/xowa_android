package gplx.gfui; import gplx.*;
public class GftCell {
	public GftSizeCalc Len0() {return len0;} public GftCell Len0_(GftSizeCalc c) {len0 = c; return this;} GftSizeCalc len0 = new GftSizeCalc_num(1);
	public GftCell Clone() {
		GftCell rv = new GftCell();
		rv.len0 = len0.Clone();
		return rv;
	}		
}
