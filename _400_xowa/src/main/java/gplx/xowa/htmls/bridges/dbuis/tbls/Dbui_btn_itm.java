package gplx.xowa.htmls.bridges.dbuis.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.bridges.*; import gplx.xowa.htmls.bridges.dbuis.*;
public class Dbui_btn_itm {
	public Dbui_btn_itm(String cmd, String img, String text) {this.cmd = cmd; this.img = img; this.text = text;}
	public String Key() {return text;}
	public String Cmd() {return cmd;} private final String cmd;
	public String Img() {return img;} private final String img;
	public String Text() {return text;} private final String text;
	public static final Dbui_btn_itm[] Ary_empty = new Dbui_btn_itm[0];
}
