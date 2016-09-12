package gplx.xowa.addons.wikis.ctgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*;
public class Xoctg_page_xtn {
	public Xoctg_page_xtn(byte tid, byte[] sortkey) {this.tid = tid; this.sortkey = sortkey;}
	public byte Tid() {return tid;} private final    byte tid;
	public byte[] Sortkey() {return sortkey;} private final    byte[] sortkey;
}
