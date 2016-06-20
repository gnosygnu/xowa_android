package gplx.gfui; import gplx.*;
public class DirInt {
	public int Val() {return val;} int val;
	public DirInt Rev() {return this == Fwd ? Bwd : Fwd;}
	public int CompareToRng(int v, int lo, int hi) {
		if		(v < lo)	return -1 * val;
		else if (v > hi)	return  1 * val;
		else				return 0;
	}
	public int GetValByDir(int ifBwd, int ifFwd) {
		return this == Bwd ? ifBwd : ifFwd;
	}
	public boolean BoundFail(int i, int bound) {return this == Bwd ? i < bound : i > bound;}
	DirInt(int v) {this.val = v;}
	public static final    DirInt
		  Fwd = new DirInt(1)
		, Bwd = new DirInt(-1);
}
