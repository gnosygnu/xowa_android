package gplx.xowa.htmls.bridges.dbuis.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.bridges.*; import gplx.xowa.htmls.bridges.dbuis.*;
public class Dbui_val_itm {
	public Dbui_val_itm(byte[] data, byte[] html) {this.data = data; this.html = html;}
	public byte[] Data() {return data;} private byte[] data;
	public byte[] Html() {return html;} private final byte[] html;
	public void Data_(byte[] v) {this.data = v;}
}
