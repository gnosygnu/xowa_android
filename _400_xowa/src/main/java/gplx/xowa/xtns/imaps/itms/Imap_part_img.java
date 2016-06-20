package gplx.xowa.xtns.imaps.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.imaps.*;
import gplx.xowa.parsers.lnkis.*;
public class Imap_part_img implements Imap_part {
	public Imap_part_img(Xop_lnki_tkn img_link) {this.img_link = img_link;}
	public byte				Part_tid() {return Imap_part_.Tid_img;}
	public Xop_lnki_tkn		Img_link() {return img_link;} private final    Xop_lnki_tkn img_link;
}
