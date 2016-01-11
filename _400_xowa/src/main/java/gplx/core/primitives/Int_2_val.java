package gplx.core.primitives; import gplx.*; import gplx.core.*;
public class Int_2_val {
	public Int_2_val(int v0, int v1) {val_0 = v0; val_1 = v1;}
	public int Val_0() {return val_0;} final int val_0;
	public int Val_1() {return val_1;} final int val_1;
	public String Xto_str(Bry_bfr bfr) {return Xto_str(bfr, val_0, val_1);}
	public static final Int_2_val Null_ptr = null;
	public static Int_2_val parse(String raw) {
		String[] itms = String_.Split(raw, ',');
		if (itms.length != 2) return Null_ptr;
		int v0 = Int_.parse_or(itms[0], Int_.Min_value); if (v0 == Int_.Min_value) return Null_ptr;
		int v1 = Int_.parse_or(itms[1], Int_.Min_value); if (v1 == Int_.Min_value) return Null_ptr;
		return new Int_2_val(v0, v1);
	}
	public static String Xto_str(Bry_bfr bfr, int x, int y) {return bfr.Add_int_variable(x).Add_byte_comma().Add_int_variable(y).To_str_and_clear();}
}
