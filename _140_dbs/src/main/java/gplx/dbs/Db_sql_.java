package gplx.dbs; import gplx.*;
public class Db_sql_ {
	public static String Make_by_fmt(String[] lines, Object... args) {
		Bry_bfr bfr = Bry_bfr_.New();
		int len = lines.length;
		for (int i = 0; i < len; ++i) {
			if (i != 0) bfr.Add_byte_nl();
			bfr.Add_str_u8(lines[i]);
		}
		String fmt = bfr.To_str_and_clear();
		return String_.Format(fmt, args);
	}
	public static byte[] Escape_arg(byte[] raw) {
		int len = raw.length;
		Bry_bfr bfr = null;
		boolean dirty = false;

		for (int i = 0; i < len; ++i) {
			byte b = raw[i];
			if (b == Byte_ascii.Apos) {
				if (bfr == null) {
					dirty = true;
					bfr = Bry_bfr_.New();
					bfr.Add_mid(raw, 0, i);
				}
				bfr.Add_byte_apos().Add_byte_apos();
			}
			else {
				if (dirty) {
					bfr.Add_byte(b);
				}
			}
		}
		return dirty ? bfr.To_bry_and_clear() : raw;
	}
}
