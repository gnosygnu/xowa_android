package gplx.xowa.guis.cmds; import gplx.*; import gplx.xowa.*; import gplx.xowa.guis.*;
public class Xog_cmd_itm {
	public Xog_cmd_itm(String key, Xog_cmd_ctg ctg, String cmd) {
		this.key = key; this.ctg = ctg; this.cmd = cmd;
		this.key_bry = Bry_.new_u8(key);
		this.uid = ++Uid_next;
	}
	public int Uid() {return uid;} private int uid;
	public String Key() {return key;} private String key;
	public byte[] Key_bry() {return key_bry;} private byte[] key_bry;
	public Xog_cmd_ctg Ctg() {return ctg;} private Xog_cmd_ctg ctg;
	public String Cmd() {return cmd;} public Xog_cmd_itm Cmd_(String v) {cmd = v; return this;} private String cmd;
	public String Name() {return name;} public Xog_cmd_itm Name_(String v) {name = v; return this;} private String name;
	public String Name_or_missing() {return name == null ? "<" + name + ">" : name;}
	public String Tip() {return tip;} public Xog_cmd_itm Tip_(String v) {tip = v; return this;} private String tip;
	public String Tip_or_missing() {return tip == null ? "<" + tip + ">" : tip;}
	private static int Uid_next = 0;
}
