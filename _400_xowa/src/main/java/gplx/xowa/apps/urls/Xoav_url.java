package gplx.xowa.apps.urls; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*;
public class Xoav_url {
	public byte[] Wiki_bry() {return wiki_bry;} public void Wiki_bry_(byte[] v) {wiki_bry = v;} private byte[] wiki_bry;
	public byte[] Page_bry() {return page_bry;} public void Page_bry_(byte[] v) {page_bry = v;} private byte[] page_bry;
	public byte[] Anch_bry() {return anch_bry;} public void Anch_bry_(byte[] v) {anch_bry = v;} private byte[] anch_bry;
	public byte[] Qarg_bry() {return qarg_bry;} public void Qarg_bry_(byte[] v) {qarg_bry = v;} private byte[] qarg_bry;
	public void Clear() {wiki_bry = page_bry = null;}
}
