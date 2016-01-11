package gplx.xowa.xtns.imaps; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.lnkis.*;
interface Imap_itm {
	byte Itm_tid();
}
class Imap_itm_ {
	public static final byte Tid_invalid = 0, Tid_img = 1, Tid_desc = 2, Tid_comment = 3, Tid_dflt = 4, Tid_shape_rect = 5, Tid_shape_circle = 6, Tid_shape_poly = 7;
	public static final byte[] 
	  Key_dflt				= Bry_.new_a7("default")
	, Key_shape_rect		= Bry_.new_a7("rect")
	, Key_shape_circle		= Bry_.new_a7("circle")
	, Key_shape_poly		= Bry_.new_a7("poly")
	;
	public static byte[] Xto_key(byte v) {
		switch (v) {
			case Tid_shape_rect		: return Key_shape_rect;
			case Tid_shape_circle	: return Key_shape_circle;
			case Tid_shape_poly		: return Key_shape_poly;
			default					: throw Err_.new_unhandled(v);
		}
	}
}
class Imap_itm_img implements Imap_itm {
	public Imap_itm_img(Xop_lnki_tkn img_link) {this.img_link = img_link;}
	public byte Itm_tid() {return Imap_itm_.Tid_img;}
	public Xop_lnki_tkn Img_link() {return img_link;} private Xop_lnki_tkn img_link;
}
class Imap_itm_desc implements Imap_itm {
	public Imap_itm_desc(byte desc_tid) {this.desc_tid = desc_tid;}
	public byte Itm_tid() {return Imap_itm_.Tid_desc;}
	public byte Desc_tid() {return desc_tid;} private byte desc_tid;
}
class Imap_itm_dflt implements Imap_itm, Imap_link_owner {
	public byte Itm_tid() {return Imap_itm_.Tid_dflt;}
	public int Link_tid() {return link_tid;} private int link_tid;
	public Xop_tkn_itm Link_tkn() {return link_tkn;} private Xop_tkn_itm link_tkn;
	public void Link_tid_(int tid, Xop_tkn_itm tkn) {link_tid = tid; link_tkn = tkn;} 
	public byte[] Link_href() {return link_href;} public void Link_href_(byte[] v) {this.link_href = v;} private byte[] link_href;
	public byte[] Link_text() {return link_text;} public void Link_text_(byte[] v) {this.link_text = v;} private byte[] link_text;
}
class Imap_err {
	public Imap_err(int itm_idx, String err_key) {this.itm_idx = itm_idx; this.err_key = err_key;}
	public int Itm_idx() {return itm_idx;} private int itm_idx;
	public String Err_key() {return err_key;} private String err_key;
}
