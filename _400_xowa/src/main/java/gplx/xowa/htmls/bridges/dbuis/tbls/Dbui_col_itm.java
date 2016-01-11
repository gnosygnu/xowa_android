package gplx.xowa.htmls.bridges.dbuis.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.bridges.*; import gplx.xowa.htmls.bridges.dbuis.*;
public class Dbui_col_itm {
	public Dbui_col_itm(int type, int width, String key, String display) {this.type = type; this.width = width; this.key = key; this.display = display;}
	public int Type() {return type;} private final int type;
	public String Key() {return key;} private final String key;
	public String Display() {return display;} private final String display;
	public int Width() {return width;} private final int width;
	public static final int Type_id_str = 1, Type_id_text = 2, Type_id_int = 3, Type_id_datetime = 4;
}
