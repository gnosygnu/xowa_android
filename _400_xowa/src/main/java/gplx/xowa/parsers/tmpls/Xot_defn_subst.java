package gplx.xowa.parsers.tmpls; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
public class Xot_defn_subst implements Xot_defn {
	public Xot_defn_subst(byte tid, byte[] name) {this.tid = tid; this.name = name;}
	public byte Defn_tid() {return tid;} private byte tid;
	public byte[] Name() {return name;} private byte[] name;
	public Xot_defn Clone(int id, byte[] name) {return new Xot_defn_subst(tid, name);}
	public boolean Defn_require_colon_arg() {return true;}
	public void Rls() {name = null;}
	public int Cache_size() {return 1024;}	// arbitrary size
}
