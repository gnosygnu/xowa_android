package gplx.xowa.htmls.bridges.dbuis.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.bridges.*; import gplx.xowa.htmls.bridges.dbuis.*;
public class Dbui_val_hash {
	private final Ordered_hash hash = Ordered_hash_.New_bry();
	public void Add(byte[] key, Dbui_val_itm itm) {hash.Add(key, itm);}
	public byte[] Get_val_as_bry(String key) {return Get_val_as_bry(Bry_.new_u8(key));}
	public byte[] Get_val_as_bry(byte[] key) {
		Dbui_val_itm itm = (Dbui_val_itm)hash.Get_by(key); if (itm == null) throw Err_.new_wo_type("dbui.val_hash; unknown key", "key", key);
		return itm.Data();
	}
}
