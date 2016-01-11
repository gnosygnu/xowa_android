package gplx.core.brys.args; import gplx.*; import gplx.core.*; import gplx.core.brys.*;
public class Bfr_arg__decimal_int implements Bfr_arg {
	public int Val() {return val;} public Bfr_arg__decimal_int Val_(int v) {val = v; return this;} int val;
	public Bfr_arg__decimal_int Places_(int v) {places = v; multiple = (int)Math_.Pow(10, v); return this;} int multiple = 1000, places = 3;
	public void Bfr_arg__add(Bry_bfr bfr) {
		bfr.Add_int_variable(val / multiple).Add_byte(Byte_ascii.Dot).Add_int_fixed(val % multiple, places);
	}
}
