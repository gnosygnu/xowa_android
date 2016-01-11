package gplx.xowa.langs.durations; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*;
public class Xol_interval_itm {
	public Xol_interval_itm(Xol_duration_itm duration_itm, long val) {this.duration_itm = duration_itm; this.val = val;}
	public Xol_duration_itm Duration_itm() {return duration_itm;} private Xol_duration_itm duration_itm;
	public long Val() {return val;} private long val;
	public static KeyVal[] Xto_kv_ary(Xol_interval_itm[] ary) {
		int len = ary.length;
		KeyVal[] rv = new KeyVal[len];
		for (int i = 0; i < len; i++) {
			Xol_interval_itm itm = ary[i];
			rv[i] = KeyVal_.new_(itm.Duration_itm().Name_str(), (int)itm.Val());	// double for scribunto
		}
		return rv;
	}
}
