package gplx.xowa.langs.durations; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*;
public class Xol_duration_itm {
	public Xol_duration_itm(byte tid, String name_str, long seconds) {
		this.tid = tid; this.seconds = seconds;
		this.name_str = name_str; this.name_bry = Bry_.new_a7(name_str);
	}
	public byte Tid() {return tid;} private byte tid;
	public byte[] Name_bry() {return name_bry;} private byte[] name_bry;
	public String Name_str() {return name_str;} private String name_str;
	public long Seconds() {return seconds;} private long seconds;
}
