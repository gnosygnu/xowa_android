package gplx.core.brys.args; import gplx.*; import gplx.core.*; import gplx.core.brys.*;
public class Bfr_arg__bry_ary implements Bfr_arg {
	private byte[][] bry_ary;
	public Bfr_arg__bry_ary Set(byte[]... v) {this.bry_ary = v; return this;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		for (byte[] bry : bry_ary)
			bfr.Add(bry);
	}
}
