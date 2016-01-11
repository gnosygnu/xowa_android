package gplx.xowa.htmls.bridges.dbuis.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.bridges.*; import gplx.xowa.htmls.bridges.dbuis.*;
public interface Dbui_tbl_itm {
	byte[] Key();
	Dbui_col_itm[] Cols();
	Dbui_btn_itm[] View_btns();
	Dbui_btn_itm[] Edit_btns();
	String Del (byte[] row_id, byte[] row_pkey);
	String Edit(byte[] row_id, byte[] row_pkey);
	String Save(byte[] row_id, byte[] row_pkey, Dbui_val_hash vals);
	String Reorder(byte[][] pkeys, int owner);
}
