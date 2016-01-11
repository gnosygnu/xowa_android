package gplx.xowa.htmls.skins; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
class Xoh_skin_regy {
	private final Ordered_hash hash = Ordered_hash_.New();
	public int Len() {return hash.Count();}
	public Xoh_skin_itm Get_at(int i) {return (Xoh_skin_itm)hash.Get_at(i);}
	public Xoh_skin_itm Get_by_key(String key) {return (Xoh_skin_itm)hash.Get_by(key);}
	public void Set(String key, String fmt) {
		Xoh_skin_itm itm = Get_by_key(key);
		if (itm == null) {
			itm = new Xoh_skin_itm(key, fmt);
			Add(itm);
		}
		else
			itm.Fmt_(fmt);
	}
	public void Add(Xoh_skin_itm itm)		{hash.Add(itm.Key(), itm);}
}
