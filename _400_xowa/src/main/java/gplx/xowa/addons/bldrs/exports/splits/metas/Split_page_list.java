package gplx.xowa.addons.bldrs.exports.splits.metas; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*; import gplx.xowa.addons.bldrs.exports.splits.*;
public class Split_page_list {
	private final    List_adp list = List_adp_.New();
	public Split_page_list(byte type) {this.type = type;}
	public byte Type()			{return type;} private final    byte type;
	public int Len()			{return list.Count();}
	public Object Get_at(int i) {return list.Get_at(i);}
	public void Add(Object o)	{list.Add(o);}
}
