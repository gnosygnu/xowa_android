package gplx.xowa.specials.search; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*;
class Xows_ui_rslt {
	private final List_adp rslt_list = List_adp_.new_();
	public int			Len()					{return rslt_list.Count();}
	public void			Add(Xows_db_row rslt)	{rslt_list.Add(rslt);}
	public Xows_db_row	Get_at(int i)			{return (Xows_db_row)rslt_list.Get_at(i);}
}
