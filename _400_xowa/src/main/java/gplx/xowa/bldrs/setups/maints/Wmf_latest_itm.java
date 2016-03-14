package gplx.xowa.bldrs.setups.maints; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.setups.*;
public class Wmf_latest_itm {
	public Wmf_latest_itm(byte[] name, DateAdp date, long size) {
		this.name = name; this.date = date; this.size = size;
	}
	public byte[] Name() {return name;} private final byte[] name;
	public DateAdp Date() {return date;} private final DateAdp date;
	public long Size() {return size;} private final long size;
}
