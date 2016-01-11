package gplx.xowa.xtns.lst; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
public class Lst_section_nde_mgr {
	public int Count() {return list.Count();} private List_adp list = List_adp_.new_();
	public Lst_section_nde Get_at(int i) {return (Lst_section_nde)list.Get_at(i);}
	public void Add(Lst_section_nde xnde) {list.Add(xnde);}
	public void Clear() {list.Clear();}
}
