package gplx.langs.htmls.clses; import gplx.*; import gplx.langs.*; import gplx.langs.htmls.*;
public class Gfh_class_ {
	public static boolean Has(byte[] src, int src_bgn, int src_end, byte[] cls) {
		int cls_bgn = src_bgn; 
		int pos = src_bgn;
		while (true) {
			boolean pos_is_last = pos == src_end;
			byte b = pos_is_last ? Byte_ascii.Space : src[pos];
			if (b == Byte_ascii.Space) {
				if (cls_bgn != -1) {
					if (Bry_.Match(src, cls_bgn, pos, cls))return true;
					cls_bgn = -1;
				}
			}
			else {
				if (cls_bgn == -1) cls_bgn = pos;
			}
			if (pos_is_last) break;
			++pos;
		}
		return false;
	}
	public static byte Find_1st(byte[] src, int src_bgn, int src_end, Hash_adp_bry hash) {
		int cls_bgn = src_bgn; 
		int pos = src_bgn;
		while (true) {
			boolean pos_is_last = pos == src_end;
			byte b = pos_is_last ? Byte_ascii.Space : src[pos];
			if (b == Byte_ascii.Space) {
				if (cls_bgn != -1) {
					byte rv = hash.Get_as_byte_or(src, cls_bgn, pos, Byte_.Max_value_127);
					if (rv != Byte_.Max_value_127) return rv;
					cls_bgn = -1;
				}
			}
			else {
				if (cls_bgn == -1) cls_bgn = pos;
			}
			if (pos_is_last) break;
			++pos;
		}
		return Byte_.Max_value_127;
	}
}
