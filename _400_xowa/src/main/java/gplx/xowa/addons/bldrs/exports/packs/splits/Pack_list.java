package gplx.xowa.addons.bldrs.exports.packs.splits; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*; import gplx.xowa.addons.bldrs.exports.packs.*;
import gplx.xowa.wikis.data.*;
import gplx.xowa.addons.bldrs.centrals.dbs.datas.imports.*;
import gplx.xowa.addons.bldrs.exports.splits.*; import gplx.xowa.addons.bldrs.exports.splits.mgrs.*;
class Pack_list {
	private final    List_adp list = List_adp_.New();
	public int Len() {return list.Len();}
	public Pack_itm Get_at(int i) {return (Pack_itm)list.Get_at(i);}
	public void Add(Pack_itm itm) {list.Add(itm);}
}
