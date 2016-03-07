package gplx.xowa.addons; import gplx.*; import gplx.xowa.*;
public class Xoax_addon_mgr {
	private final Ordered_hash hash = Ordered_hash_.New_bry();
	public Xoax_addon_itm	Itms__get_or_null(byte[] key) {return (Xoax_addon_itm)hash.Get_by(key);}
	public void				Itms__add(Xoax_addon_itm itm) {hash.Add(itm.Addon__key(), itm);}
}
