package gplx.xowa.xtns.imaps.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.imaps.*;
import gplx.xowa.parsers.*;
public class Imap_part_dflt implements Imap_part, Imap_link_owner {
	public byte				Part_tid() {return Imap_part_.Tid_dflt;}
	public Xop_tkn_itm		Link_tkn() {return link_tkn;}	private Xop_tkn_itm link_tkn;
	public int				Link_tid() {return link_tid;}	private int link_tid;		public void Link_tid_(int tid, Xop_tkn_itm tkn) {link_tid = tid; link_tkn = tkn;} 
	public byte[]			Link_href() {return link_href;} private byte[] link_href;	public void Link_href_(byte[] v) {this.link_href = v;} 
	public byte[]			Link_text() {return link_text;} private byte[] link_text;	public void Link_text_(byte[] v) {this.link_text = v;}
}
