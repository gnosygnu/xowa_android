package gplx.xowa.users.bmks; import gplx.*; import gplx.xowa.*; import gplx.xowa.users.*;
public class Xoud_bmk_dir_row {
	public Xoud_bmk_dir_row(int id, int owner, int sort, byte[] name) {
		this.id = id; this.owner = owner; this.sort = sort; this.name = name;
	}
	public int		Id() {return id;} private final int id;
	public int		Owner() {return owner;} private final int owner;
	public int		Sort() {return sort;} private final int sort;
	public byte[]	Name() {return name;} private final byte[] name;
}
