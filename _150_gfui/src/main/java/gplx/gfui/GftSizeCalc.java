package gplx.gfui; import gplx.*;
public interface GftSizeCalc {
	int Key();
	int Calc(GftGrid grid, GftBand band, GftItem owner, GftItem item, int ownerWidth);
	GftSizeCalc Clone();
}
class GftSizeCalc_pct implements GftSizeCalc {	
	public int Key() {return KEY;} public static final int KEY = 1;
	public float Val() {return pct;} float pct;		
	public int Calc(GftGrid grid, GftBand band, GftItem owner, GftItem item, int ownerWidth) {
		return Int_.Mult(ownerWidth, pct / 100);
	}
	public GftSizeCalc Clone() {return new GftSizeCalc_pct(pct);}
	public GftSizeCalc_pct(float v) {pct = v;}
}
class GftSizeCalc_abs implements GftSizeCalc {	
	public int Key() {return KEY;} public static final int KEY = 2;
	public int Val() {return abs;} int abs;
	public int Calc(GftGrid grid, GftBand band, GftItem owner, GftItem item, int ownerWidth) {
		return abs;
	}
	public GftSizeCalc Clone() {return new GftSizeCalc_abs(abs);}
	public GftSizeCalc_abs(int v) {abs = v;}
}
class GftSizeCalc_num implements GftSizeCalc {	
	public int Key() {return KEY;} public static final int KEY = 3;
	public int Val() {return num;} int num;
	public int Calc(GftGrid grid, GftBand band, GftItem owner, GftItem item, int ownerWidth) {
		return owner.Gft_w() / num;
	}
	public GftSizeCalc Clone() {return new GftSizeCalc_num(num);}
	public GftSizeCalc_num(int num) {this.num = num;}
}
class GftSizeCalc_var implements GftSizeCalc {	
	public int Key() {return KEY;} public static final int KEY = 4;
	public int Val() {return num;} int num;
	public int Calc(GftGrid grid, GftBand band, GftItem owner, GftItem item, int ownerWidth) {
		GfuiElem elem = GfuiElem_.as_(item);
		return elem == null ? item.Gft_w() : elem.Width();
	}
	public GftSizeCalc Clone() {return new GftSizeCalc_var(num);}
	public GftSizeCalc_var(int num) {this.num = num;}
}