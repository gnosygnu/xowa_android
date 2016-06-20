package gplx.xowa.addons.bldrs.exports.splits.metas; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*; import gplx.xowa.addons.bldrs.exports.splits.*;
import gplx.core.lists.hashs.*;
public class Split_page_mgr {
	private final    Hash_adp__int hash = new Hash_adp__int();
	public void Clear() {hash.Clear();}
	public Split_page_itm Get_by_or_null(int page_id) {return (Split_page_itm)hash.Get_by_or_null(page_id);}
	public void Add(Split_page_itm page) {
		hash.Add(page.Page_id(), page);
	}
}
