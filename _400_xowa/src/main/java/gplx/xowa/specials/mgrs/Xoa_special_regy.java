package gplx.xowa.specials.mgrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*;
import gplx.xowa.specials.*;
public class Xoa_special_regy {
	private final    Ordered_hash hash = Ordered_hash_.New_bry();	// NOTE: case-sensitive; case-insensitive requires lang, but regy is at app level
	public int Len() {return hash.Len();}
	public Xow_special_page Get_at(int i)					{return (Xow_special_page)hash.Get_at(i);}
	public Xow_special_page Get_by_or_null(byte[] key)		{return (Xow_special_page)hash.Get_by(key);}
	public void Add(Xow_special_page page)	{
		hash.Add(page.Special__meta().Key_bry(), page);
		byte[][] aliases = page.Special__meta().Aliases();
		for (byte[] alias : aliases)
			hash.Add(alias, page);
	}
	public void Add_many(Xow_special_page... ary)	{for (Xow_special_page itm : ary) Add(itm);}
}
