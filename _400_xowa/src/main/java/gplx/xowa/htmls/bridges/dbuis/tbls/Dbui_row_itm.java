package gplx.xowa.htmls.bridges.dbuis.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.bridges.*; import gplx.xowa.htmls.bridges.dbuis.*;
public class Dbui_row_itm {
	public Dbui_row_itm(Dbui_tbl_itm tbl, byte[] pkey, Dbui_val_itm[] vals) {
		this.tbl = tbl; this.pkey = pkey; this.vals = vals;
	}
	public Dbui_tbl_itm Tbl() {return tbl;} private final Dbui_tbl_itm tbl;
	public byte[] Pkey() {return pkey;} private final byte[] pkey;
	public Dbui_val_itm[] Vals() {return vals;} private Dbui_val_itm[] vals;
}
