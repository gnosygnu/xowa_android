package gplx.xowa.addons.bldrs.exports.packs.files; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*; import gplx.xowa.addons.bldrs.exports.packs.*;
class Pack_list {
	private final    List_adp list = List_adp_.New();
	public Pack_list(int tid) {this.tid = tid;}
	public int			Tid()				{return tid;}		private final    int tid;
	public int			Len()				{return list.Len();}
	public Pack_itm		Get_at(int i)		{return (Pack_itm)list.Get_at(i);}
	public void			Add(Pack_itm itm)	{list.Add(itm);}
	public void			Clear()				{list.Clear();}
}
