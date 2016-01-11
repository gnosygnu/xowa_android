package gplx.langs.gfs; import gplx.*; import gplx.langs.*;
public class Gfs_wtr {		
	public byte Quote_char() {return quote_char;} public Gfs_wtr Quote_char_(byte v) {quote_char = v; return this;} private byte quote_char = Byte_ascii.Apos;
	public Bry_bfr Bfr() {return bfr;} private Bry_bfr bfr = Bry_bfr.reset_(255);
	public void Add_grp_bgn(byte[] key) {
		bfr.Add(key);							// key
		bfr.Add_byte(Byte_ascii.Curly_bgn);		// {
	}
	public void Add_grp_end(byte[] key) {
		bfr.Add_byte(Byte_ascii.Curly_end);		// }
	}
	public void Add_set_eq(byte[] key, byte[] val) {
		bfr.Add(key);							// key
		bfr.Add_byte_eq();						// =
		bfr.Add_byte(quote_char);				// '
		Write_val(val);
		bfr.Add_byte(quote_char);				// '
		bfr.Add_byte(Byte_ascii.Semic);			// ;
	}
	private void Write_val(byte[] bry) {
		int bry_len = bry.length;
		for (int i = 0; i < bry_len; i++) {
			byte b = bry[i];
			if (b == quote_char)	// byte is quote
				bfr.Add_byte(b);	// double up
			bfr.Add_byte(b);
		}
	}
}
