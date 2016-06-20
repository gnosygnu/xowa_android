package gplx.xowa.xtns.imaps.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.imaps.*;
public class Imap_part_desc implements Imap_part {
	public Imap_part_desc(byte desc_tid) {this.desc_tid = desc_tid;}
	public byte Part_tid() {return Imap_part_.Tid_desc;}
	public byte Desc_tid() {return desc_tid;} private final    byte desc_tid;
}
