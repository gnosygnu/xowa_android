package gplx.xowa.wikis.tdbs.stats; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.tdbs.*;
import gplx.core.strings.*;
public class Xob_stat_itm {
	public String Ns() {return ns;} private String ns;
	public int  Fils;
	public long Size, SizeMax = Int_.Min_value, SizeMin = Int_.Max_value;
	public int SizeMaxIdx, SizeMinIdx;
	public void Tally(long size, int idx) {
		Fils++;
		Size += size;
		if (size > SizeMax) {SizeMax = size; SizeMaxIdx = idx;}
		if (size < SizeMin) {SizeMin = size; SizeMinIdx = idx;}
	}
	public void To_str(String_bldr sb) {
		XtoStr_fld(sb, ns).XtoStr_fld(sb, Fils).XtoStr_fld(sb, Size).XtoStr_fld(sb, SizeMax).XtoStr_fld(sb, SizeMaxIdx).XtoStr_fld(sb, SizeMin);
		sb.Add(Int_.To_str(SizeMinIdx));
	}
	Xob_stat_itm XtoStr_fld(String_bldr sb, long v) {sb.Add(Long_.To_str(v)).Add(Xob_stat_itm.Dlm); return this;}
	Xob_stat_itm XtoStr_fld(String_bldr sb, int v) {sb.Add(Int_.To_str(v)).Add(Xob_stat_itm.Dlm); return this;}
	Xob_stat_itm XtoStr_fld(String_bldr sb, String v) {sb.Add(v).Add(Xob_stat_itm.Dlm); return this;}
	public Xob_stat_itm(String ns) {
		this.ns = ns;
	}
	public Object NewByKey(Object o) {return new Xob_stat_itm((String)o);} public static final Xob_stat_itm Instance = new Xob_stat_itm(); Xob_stat_itm() {}
	public static final char Dlm = '|';
}
