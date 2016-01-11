package gplx.core.brys.args; import gplx.*; import gplx.core.*; import gplx.core.brys.*;
public class Bfr_arg__int implements Bfr_arg {
	private int val, val_digits;
	public Bfr_arg__int(int v) {Set(v);}
	public Bfr_arg__int Set(int v) {
		this.val = Int_.cast(v); 
		this.val_digits = Int_.DigitCount(val);
		return this;
	}
	public void Bfr_arg__add(Bry_bfr bfr) {bfr.Add_int_digits(val_digits, val);}
}
