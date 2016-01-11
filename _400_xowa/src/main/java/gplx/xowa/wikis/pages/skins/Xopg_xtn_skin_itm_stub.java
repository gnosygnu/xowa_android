package gplx.xowa.wikis.pages.skins; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.pages.*;
public class Xopg_xtn_skin_itm_stub implements Xopg_xtn_skin_itm {
	private final byte[] val;
	public Xopg_xtn_skin_itm_stub(byte[] val) {this.val = val;}
	public byte Tid()		{return Xopg_xtn_skin_itm_tid.Tid_sidebar;}
	public byte[] Key()		{return Bry_.Empty;}
	public void Write(Bry_bfr bfr, Xoae_page page) {bfr.Add(val);}
}
