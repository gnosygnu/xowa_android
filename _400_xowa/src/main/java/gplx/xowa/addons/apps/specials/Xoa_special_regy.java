package gplx.xowa.addons.apps.specials; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*;
import gplx.xowa.specials.*;
public class Xoa_special_regy {
	private final    Ordered_hash hash = Ordered_hash_.New_bry();	// NOTE: case-sensitive; case-insensitive requires lang
	public void Add(Xows_page page)					{hash.Add(page.Special__meta().Key_bry(), page);}
	public void Add_many(Xows_page... ary)	{for (Xows_page itm : ary) Add(itm);}
	public Xows_page Get_by_or_null(byte[] key)		{return (Xows_page)hash.Get_by(key);}
}
