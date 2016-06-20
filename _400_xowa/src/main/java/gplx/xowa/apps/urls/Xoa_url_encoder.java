package gplx.xowa.apps.urls; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*;
public class Xoa_url_encoder {
	public byte[] Encode(byte[] src) {
		int src_len = src.length;
		for (int i = 0; i < src_len; i++) {
			byte b = src[i];
			switch (b) {
				case Byte_ascii.Space:		bb.Add(Bry_underline); break;
				case Byte_ascii.Amp:		bb.Add(Bry_amp); break;
				case Byte_ascii.Apos:		bb.Add(Bry_apos); break;
				case Byte_ascii.Eq:			bb.Add(Bry_eq); break;
				case Byte_ascii.Plus:		bb.Add(Bry_plus); break;
				default:					bb.Add_byte(b); break;
				// FUTURE: html_entities, etc:
			}
		}
		return bb.To_bry_and_clear();
	}
	private static final    byte[] Bry_amp = Bry_.new_a7("%26"), Bry_eq = Bry_.new_a7("%3D")
		, Bry_plus = Bry_.new_a7("%2B"), Bry_apos = Bry_.new_a7("%27")
		, Bry_underline = new byte[] {Byte_ascii.Underline}
		;
	Bry_bfr bb = Bry_bfr_.New();
	public static final    Xoa_url_encoder Instance = new Xoa_url_encoder(); Xoa_url_encoder() {}
}
