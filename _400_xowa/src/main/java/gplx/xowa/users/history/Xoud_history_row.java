package gplx.xowa.users.history; import gplx.*; import gplx.xowa.*; import gplx.xowa.users.*;
public class Xoud_history_row {
	public Xoud_history_row(int id, byte[] wiki, byte[] url, DateAdp time, int count) {
		this.id = id;
		this.wiki = wiki; this.url = url;
		this.time = time; this.count = count;
	}
	public int Id() {return id;} private final int id;
	public byte[] Wiki() {return wiki;} private final byte[] wiki;
	public byte[] Url()  {return url;} private final byte[] url;
	public DateAdp Time() {return time;} private final DateAdp time;
	public int Count() {return count;} private final int count;
}
