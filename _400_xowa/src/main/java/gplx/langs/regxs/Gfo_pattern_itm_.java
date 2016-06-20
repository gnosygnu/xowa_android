package gplx.langs.regxs; import gplx.*; import gplx.langs.*;
public class Gfo_pattern_itm_ {
	public static final byte Tid_text = 0, Tid_wild = 1;
	public static Gfo_pattern_itm[] Compile(byte[] raw) {
		List_adp rv = List_adp_.New();
		int raw_len = raw.length;
		int itm_bgn = -1;
		Gfo_pattern_itm itm = null;
		int pos = 0;
		while (true) {
			boolean last = pos == raw_len;
			byte b = last ? Byte_ascii.Null : raw[pos];
			switch (b) {
				case Byte_ascii.Null:
					if (itm != null) {itm.Compile(raw, itm_bgn, pos); itm = null; itm_bgn = -1;}
					break;
				case Byte_ascii.Star:
					if (itm != null) {itm.Compile(raw, itm_bgn, pos); itm = null; itm_bgn = -1;}
					rv.Add(Gfo_pattern_itm_wild.Instance);
					break;
				default:
					if (itm_bgn == -1) {
						itm_bgn = pos;
						itm = new Gfo_pattern_itm_text();
						rv.Add(itm);
					}
					break;
			}
			++pos;
			if (last) break;
		}
		return (Gfo_pattern_itm[])rv.To_ary_and_clear(Gfo_pattern_itm.class);
	}
}
