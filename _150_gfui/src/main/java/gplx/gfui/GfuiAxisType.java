package gplx.gfui; import gplx.*;
public class GfuiAxisType {//_20101206
	public int Val() {return val;} int val;
	public GfuiAxisType CrossAxis() {return val == GfuiAxisType.X.val ? GfuiAxisType.Y : GfuiAxisType.X;}
	GfuiAxisType(int v) {this.val = v;}
	public static final GfuiAxisType X = new GfuiAxisType(1);
	public static final GfuiAxisType Y = new GfuiAxisType(2);
}
