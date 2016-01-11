package gplx.xowa.users.bmks; import gplx.*; import gplx.xowa.*; import gplx.xowa.users.*;
public class Xoud_bmk_itm_row {
	public Xoud_bmk_itm_row(int id, int owner, int sort, byte[] name, byte[] wiki, byte[] url, byte[] comment) {
		this.id = id; this.owner = owner; this.sort = sort; this.name = name; this.wiki = wiki; this.url = url; this.comment = comment;
	}
	public int		Id() {return id;} private final int id;
	public int		Owner() {return owner;} private final int owner;
	public int		Sort() {return sort;} private final int sort;
	public byte[]	Name() {return name;} private final byte[] name;
	public byte[]	Wiki() {return wiki;} private final byte[] wiki;
	public byte[]	Url() {return url;} private final byte[] url;
	public byte[]	Comment() {return comment;} private final byte[] comment;
}
