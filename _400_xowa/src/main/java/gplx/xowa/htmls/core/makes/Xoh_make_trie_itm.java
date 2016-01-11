package gplx.xowa.htmls.core.makes; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*;
class Xoh_make_trie_itm {
	public Xoh_make_trie_itm(byte tid, boolean elem_is_xnde, byte subst_end_byte, byte[] key) {this.tid = tid; this.key = key; this.elem_is_xnde = elem_is_xnde; this.subst_end_byte = subst_end_byte;}
	public byte Tid() {return tid;} private final byte tid;
	public byte[] Key() {return key;} private final byte[] key;
	public boolean Elem_is_xnde() {return elem_is_xnde;} private final boolean elem_is_xnde;
	public byte Subst_end_byte() {return subst_end_byte;} private final byte subst_end_byte;
}
