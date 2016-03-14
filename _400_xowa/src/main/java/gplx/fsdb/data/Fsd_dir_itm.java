package gplx.fsdb.data; import gplx.*; import gplx.fsdb.*;
public class Fsd_dir_itm {
	public Fsd_dir_itm(int dir_id, int owner, byte[] name) {this.dir_id = dir_id; this.owner = owner; this.name = name;}
	public int		Dir_id()	{return dir_id;} private final int dir_id;
	public int		Owner()		{return owner;} private final int owner;
	public byte[]	Name()		{return name;} private final byte[] name;
	public static final int Owner_root = 0;
	public static final Fsd_dir_itm Null = null;
}
