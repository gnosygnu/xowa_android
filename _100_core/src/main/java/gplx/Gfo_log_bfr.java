package gplx;
public class Gfo_log_bfr {
	private Bry_bfr bfr = Bry_bfr_.Reset(255);
	public Gfo_log_bfr Add(String s) {
		bfr.Add_str_a7(DateAdp_.Now().XtoUtc().XtoStr_fmt_yyyyMMdd_HHmmss_fff());
		bfr.Add_byte_space();
		bfr.Add_str_u8(s);
		bfr.Add_byte_nl();
		return this;
	}
	public String Xto_str() {return bfr.To_str_and_clear();}
}
