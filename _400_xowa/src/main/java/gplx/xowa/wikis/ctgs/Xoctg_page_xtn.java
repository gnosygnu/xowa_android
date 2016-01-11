package gplx.xowa.wikis.ctgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
public class Xoctg_page_xtn {
	public Xoctg_page_xtn(byte tid, byte[] sortkey) {this.tid = tid; this.sortkey = sortkey;}
	public byte[] Sortkey() {return sortkey;} public void Sortkey_(byte[] v) {this.sortkey = v;} private byte[] sortkey;
	public byte Tid() {return tid;} public void Tid_(byte v) {this.tid = v;} private byte tid;
	public byte Hidden() {return hidden;} public void Hidden_(byte v) {hidden = v;} private byte hidden = Bool_.__byte;
}
