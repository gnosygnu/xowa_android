package gplx.core.encoders; import gplx.*; import gplx.core.*;
public class Hex_utl_ {
	public static int Parse_or(byte[] src, int or) {return Parse_or(src, 0, src.length, or);}
	public static int Parse_or(byte[] src, int bgn, int end, int or) {
		int rv = 0; int factor = 1;
		byte b = Byte_.Max_value_127;
		for (int i = end - 1; i >= bgn; i--) {
			switch (src[i]) {
				case Byte_ascii.Num_0: b =  0; break; case Byte_ascii.Num_1: b =  1; break; case Byte_ascii.Num_2: b =  2; break; case Byte_ascii.Num_3: b =  3; break; case Byte_ascii.Num_4: b =  4; break;
				case Byte_ascii.Num_5: b =  5; break; case Byte_ascii.Num_6: b =  6; break; case Byte_ascii.Num_7: b =  7; break; case Byte_ascii.Num_8: b =  8; break; case Byte_ascii.Num_9: b =  9; break;
				case Byte_ascii.Ltr_A: b = 10; break; case Byte_ascii.Ltr_B: b = 11; break; case Byte_ascii.Ltr_C: b = 12; break; case Byte_ascii.Ltr_D: b = 13; break; case Byte_ascii.Ltr_E: b = 14; break; case Byte_ascii.Ltr_F: b = 15; break;
				case Byte_ascii.Ltr_a: b = 10; break; case Byte_ascii.Ltr_b: b = 11; break; case Byte_ascii.Ltr_c: b = 12; break; case Byte_ascii.Ltr_d: b = 13; break; case Byte_ascii.Ltr_e: b = 14; break; case Byte_ascii.Ltr_f: b = 15; break;
				default: b = Byte_.Max_value_127; break;
			}
			if (b == Byte_.Max_value_127) return or;
			rv += b * factor;
			factor *= 16;
		}
		return rv;
	}
	public static int Parse(String src) {
		int rv = Parse_or(src, -1); if (rv == -1) throw Err_.new_parse("HexDec", "src");
		return rv;
	}
	public static int Parse_or(String src, int or) {
		int rv = 0; int digit = 0, factor = 1, len = String_.Len(src);
		for (int i = len - 1; i > -1; --i) {
			digit = To_int(String_.CharAt(src, i));
			if (digit == -1) return or;
			rv += digit * factor;
			factor *= 16;
		}
		return rv;
	}
	public static byte[] Encode_bry(byte[] src) {
		int src_len = src.length;
		byte[] trg = new byte[src_len * 2];
		Encode_bry(src, trg);
		return trg;
	}
	public static void Encode_bry(byte[] src, byte[] trg) {
		int src_len = src.length, trg_len = trg.length;
		if (trg_len != src_len * 2) throw Err_.new_("hex", "trg.len must be src.len * 2", "src_len", src_len, "trg_len", trg_len);
		int trg_idx = -1;
		for (int src_idx = 0; src_idx < src_len; ++src_idx) {
			byte src_byte = src[src_idx];
			trg[++trg_idx] = To_byte_lcase(0xf & src_byte >>> 4);	//#<>(int)((uint)src_byte >> 4)~src_byte >>> 4
			trg[++trg_idx] = To_byte_lcase(0xf & src_byte);
		}
	}
	public static void Encode_bfr(Bry_bfr bfr, byte[] src) {
		int src_len = src.length;
		for (int src_idx = 0; src_idx < src_len; ++src_idx) {
			byte src_byte = src[src_idx];
			bfr.Add_byte(To_byte_lcase(0xf & src_byte >>> 4));	//#<>(int)((uint)src_byte >> 4)~src_byte >>> 4
			bfr.Add_byte(To_byte_lcase(0xf & src_byte));
		}
	}
	public static String To_str(int val, int pad) {
		char[] ary = new char[8]; int idx = 8; // 8 is max len of hexString; (2^4 * 8); EX: int.MaxValue = 7FFFFFFF
		do {
			int byt = val % 16;
			ary[--idx] = To_char(byt);
			val /= 16;
		}	while (val > 0);
		while (8 - idx < pad)					// pad left with zeros
			ary[--idx] = '0';
		return String_.new_charAry_(ary, idx, 8-idx);
	}
	public static void Write(byte[] bry, int bgn, int end, int val) {
		for (int i = end - 1; i > bgn - 1; i--) {
			int b = val % 16;
			bry[i] = To_byte(b);
			val /= 16;
			if (val == 0) break;
		}
	}
	public static boolean Is_hex_many(byte... ary) {
		for (byte itm : ary) {
			switch (itm) {
				case Byte_ascii.Num_0: case Byte_ascii.Num_1: case Byte_ascii.Num_2: case Byte_ascii.Num_3: case Byte_ascii.Num_4:
				case Byte_ascii.Num_5: case Byte_ascii.Num_6: case Byte_ascii.Num_7: case Byte_ascii.Num_8: case Byte_ascii.Num_9:
				case Byte_ascii.Ltr_A: case Byte_ascii.Ltr_B: case Byte_ascii.Ltr_C: case Byte_ascii.Ltr_D: case Byte_ascii.Ltr_E: case Byte_ascii.Ltr_F:
				case Byte_ascii.Ltr_a: case Byte_ascii.Ltr_b: case Byte_ascii.Ltr_c: case Byte_ascii.Ltr_d: case Byte_ascii.Ltr_e: case Byte_ascii.Ltr_f:
					break;
				default:
					return false;
			}
		}
		return true;
	}
	private static int To_int(char c) {
		switch (c) {
			case '0': return 0; case '1': return 1; case '2': return 2; case '3': return 3; case '4': return 4;
			case '5': return 5; case '6': return 6; case '7': return 7; case '8': return 8; case '9': return 9;
			case 'A': return 10; case 'B': return 11; case 'C': return 12; case 'D': return 13; case 'E': return 14; case 'F': return 15;
			case 'a': return 10; case 'b': return 11; case 'c': return 12; case 'd': return 13; case 'e': return 14; case 'f': return 15;
			default: return -1;
		}
	}
	private static char To_char(int val) {
		switch (val) {
			case 0: return '0'; case 1: return '1'; case 2: return '2'; case 3: return '3'; case 4: return '4';
			case 5: return '5'; case 6: return '6'; case 7: return '7'; case 8: return '8'; case 9: return '9';
			case 10: return 'A'; case 11: return 'B'; case 12: return 'C'; case 13: return 'D'; case 14: return 'E'; case 15: return 'F';
			default: throw Err_.new_parse("hexstring", Int_.To_str(val));
		}
	}
	private static byte To_byte(int v) {
		switch (v) {
			case  0: return Byte_ascii.Num_0; case  1: return Byte_ascii.Num_1; case  2: return Byte_ascii.Num_2; case  3: return Byte_ascii.Num_3; case  4: return Byte_ascii.Num_4;
			case  5: return Byte_ascii.Num_5; case  6: return Byte_ascii.Num_6; case  7: return Byte_ascii.Num_7; case  8: return Byte_ascii.Num_8; case  9: return Byte_ascii.Num_9;
			case 10: return Byte_ascii.Ltr_A; case 11: return Byte_ascii.Ltr_B; case 12: return Byte_ascii.Ltr_C; case 13: return Byte_ascii.Ltr_D; case 14: return Byte_ascii.Ltr_E; case 15: return Byte_ascii.Ltr_F;
			default: throw Err_.new_parse("hexstring", Int_.To_str(v));
		}
	}
	private static byte To_byte_lcase(int v) {
		switch (v) {
			case  0: return Byte_ascii.Num_0; case  1: return Byte_ascii.Num_1; case  2: return Byte_ascii.Num_2; case  3: return Byte_ascii.Num_3;
			case  4: return Byte_ascii.Num_4; case  5: return Byte_ascii.Num_5; case  6: return Byte_ascii.Num_6; case  7: return Byte_ascii.Num_7;
			case  8: return Byte_ascii.Num_8; case  9: return Byte_ascii.Num_9; case 10: return Byte_ascii.Ltr_a; case 11: return Byte_ascii.Ltr_b;
			case 12: return Byte_ascii.Ltr_c; case 13: return Byte_ascii.Ltr_d; case 14: return Byte_ascii.Ltr_e; case 15: return Byte_ascii.Ltr_f;
			default: throw Err_.new_parse("hexstring", Int_.To_str(v));
		}
	}
}
