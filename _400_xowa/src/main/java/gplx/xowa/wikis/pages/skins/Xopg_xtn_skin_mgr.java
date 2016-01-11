package gplx.xowa.wikis.pages.skins; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.pages.*;
public class Xopg_xtn_skin_mgr {
	private Ordered_hash hash = Ordered_hash_.New_bry();
	public int Count() {return hash.Count();}
	public void Add(Xopg_xtn_skin_itm itm) {hash.Add_if_dupe_use_1st(itm.Key(), itm);}
	public Xopg_xtn_skin_itm Get_at(int i) {return (Xopg_xtn_skin_itm)hash.Get_at(i);}
	public Xopg_xtn_skin_itm Get_or_null(byte[] key) {return (Xopg_xtn_skin_itm)hash.Get_by(key);}
	public void Clear() {hash.Clear();}
}
