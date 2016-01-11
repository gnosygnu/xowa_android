package gplx.xowa.parsers.htmls; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
class Mwh_doc_itm {
	public Mwh_doc_itm(int itm_tid, int nde_tid, byte[] itm_bry) {this.itm_tid = itm_tid; this.itm_bry = itm_bry; this.nde_tid = nde_tid;}
	public int Itm_tid() {return itm_tid;} private final int itm_tid;
	public byte[] Itm_bry() {return itm_bry;} private final byte[] itm_bry;
	public int Nde_tid() {return nde_tid;} private final int nde_tid;
	public static final int Itm_tid__txt = 0, Itm_tid__nde_head = 1, Itm_tid__nde_tail = 2, Itm_tid__comment = 3, Itm_tid__entity = 4;
}
