package gplx.xowa.addons.searchs.v1s; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
public class Srch_rslt_list {
	private final List_adp list = List_adp_.new_();
	public int				Len()					{return list.Count();}
	public void				Add(Srch_rslt_itm rslt)	{list.Add(rslt);}
	public Srch_rslt_itm	Get_at(int i)			{return (Srch_rslt_itm)list.Get_at(i);}
}
