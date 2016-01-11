package gplx.xowa.files.caches; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
import gplx.dbs.*;
public class Xofc_dir_itm {
	public Xofc_dir_itm(int id, byte[] name, byte cmd_mode) {
		this.id = id;
		this.name = name;
		this.cmd_mode = cmd_mode;
	}
	public int Id() {return id;} public void Id_(int v) {id = v;} private int id;
	public byte[] Name() {return name;} private final byte[] name;
	public byte Cmd_mode() {return cmd_mode;} public Xofc_dir_itm Cmd_mode_(byte v) {cmd_mode = v; return this;} private byte cmd_mode;
	public static final Xofc_dir_itm Null = null;
}
